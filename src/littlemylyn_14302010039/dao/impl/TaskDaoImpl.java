package littlemylyn_14302010039.dao.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import littlemylyn_14302010039.dao.TaskDao;
import littlemylyn_14302010039.entity.Task;

public class TaskDaoImpl implements TaskDao{
	public Task newTask(String n,int c,int s,ArrayList<Task> allTask){
		Task t=new Task(n,c,s,allTask);
		allTask.add(t);
		return t;
	}
	public Task getTask(int index,ArrayList<Task> allTask){
		return allTask.get(index);
	}
	public void deleteTask(int index,ArrayList<Task> allTask){
		allTask.remove(index);
	}
	public ArrayList<Task> loadTasks(){
		ArrayList<Task> allTask=new ArrayList<Task>();
		try{
			File f=new File("tasks.txt");
			InputStreamReader read = new InputStreamReader(new FileInputStream(f),"utf-8");
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt;
			int loaderState=1;
			Task t=new Task("temp",0,0,allTask);;
			while((lineTxt = bufferedReader.readLine()) != null){
				if(lineTxt.equals("#TASK")){
					t=new Task("temp",0,0,allTask);
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
		return allTask;

	}
	public void saveTasks(ArrayList<Task> allTask){
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

}
