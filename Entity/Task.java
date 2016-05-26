package littlemylyn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;
/*
 * Entity£ºTask
 * Description£º
 * 
 * 0¡¢Overview: 
 * 		a.we can operate all tasks by a public static ArrayList of tasks
 * 			(most of time we use task's index)
 * 		b.we can operate one task by getTask() and the task's unique methods
 * 
 * 1¡¢Property£ºevery task has name, category, state,relatedCode and index.
 * (for us to pick one of the tasks easily)
 * 
 * 2¡¢Initialization£ºwe use text I\O to save and load the information of the tasks.
 * (we separate the tasks with "#TASK" and "END#")
 * 
 * 3¡¢Methods about one task: 
 *		a.changeState(int):we can change the state of a task.
 *		b.getSize():we can get the numbers of a task's related code.
 *		c.addCode(String):we can add the new member to the list of related code in a task.
 *		d.deactivatedOtherTask:(private)we only use it when the state's changing target
 *			 is STATE_ACTIVATED so that we can make sure only one task is activated.
 *
 * 4¡¢Methods about all tasks:
 * 		a.newTask(String,int,int):we can init a task and save it into the allTask list.
 * 		b.getTask(int):we can get a task by its index.
 * 		c.deleteTask(int):we can remove a task by its index.
 */
public class Task {
	public static final int TASK_DEBUG=0;
	public static final int TASK_NEWFEATURE=1;
	public static final int TASK_REFACTOR=2;
	public static final int STATE_NEW=0;
	public static final int STATE_ACTIVATED=1;
	public static final int STATE_FINISHED=2;
	private static ArrayList<Task> allTask=new ArrayList<Task>();
	private int category;
	private int state;
	private String name;
	private int index;
	private ArrayList<String> relatedCode=new ArrayList<String>();
	private Task(String n,int c,int s) {
		name=n;
		category=c;
		state=s;
		index=allTask.size();
	}
	
	//methods about I\O
	public void loadTasks(){
		try{
			File f=new File("tasks.txt");
			InputStreamReader read = new InputStreamReader(new FileInputStream(f),"utf-8");
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt;
			int loaderState=1;
			Task t=new Task("temp",0,0);;
			while((lineTxt = bufferedReader.readLine()) != null){
				if(lineTxt.equals("#TASK")){
					t=new Task("temp",0,0);
					continue;
				}
				if(lineTxt.equals("END#")){
					allTask.add(t);
					loaderState=1;
					continue;
				}
				if(loaderState==1){
					t.name=lineTxt;
				}else if(loaderState==2){
					t.category=Integer.parseInt(lineTxt);
				}else if(loaderState==3){
					t.state=Integer.parseInt(lineTxt);
				}else{
					t.relatedCode.add(lineTxt);
				}loaderState++;

			}
		}catch(Exception ex){
			System.out.println("load error!");
		}

	}
	public void saveTasks(){
		//this part change system.out into a file
		try{
			File f=new File("tasks.txt");
			f.createNewFile();
			FileOutputStream fileOutputStream = new FileOutputStream(f);
			PrintStream printStream = new PrintStream(fileOutputStream);
			System.setOut(printStream);
		}
		catch(IOException e){
			System.out.println("save error!!!");
		}
		//this part will output all tasks' information
		for(int i=0;i<allTask.size();i++){
			Task out=allTask.get(i);
			System.out.println("#TASK");
			System.out.println(out.name);
			System.out.println(out.category);
			System.out.println(out.state);
			for(int j=0;j<out.relatedCode.size();j++){
				System.out.println(out.relatedCode.get(j));
			}
			System.out.println("END#");
		}
	}


	//methods about one task
	public void changeState(int target){
		if(target==STATE_ACTIVATED){//if we want a task to be activated, the others should be deactivated
			deactivateOtherTask();
		}
		if(target==STATE_NEW){//no task can be change into new state
			JOptionPane.showMessageDialog(null, "Can't change any task's state into new state!");
		}else{
			state=target;
		}
	}
	public int getSize(){
		return relatedCode.size();
	}
	public void addCode(String s){
		relatedCode.add(s);
	}
	private void deactivateOtherTask(){
		int act=this.index;
		for(int i=0;i<allTask.size();i++){
			if(allTask.get(i).index!=act&&allTask.get(i).state==STATE_ACTIVATED){
				allTask.get(i).changeState(STATE_FINISHED);
			}	
		}
	}


	//methods about all tasks
	
	public static Task newTask(String n,int c,int s){
		Task t=new Task(n,c,s);
		allTask.add(t);
		return t;
	}
	public static Task getTask(int index){
		return allTask.get(index);
	}
	public static void deleteTask(int index){
		allTask.remove(index);
	}
}
