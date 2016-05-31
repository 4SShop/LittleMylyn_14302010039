package littlemylyn_14302010039.dao.impl;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import littlemylyn_14302010039.dao.TaskDao;
import littlemylyn_14302010039.entity.Task;

public class TaskDaoImpl implements TaskDao{
	
	public void saveTasks(ArrayList<Task> allTask){
		//this part change system.out into a file
		try{
			File f=new File("/tasks.txt");
			f.createNewFile();
			FileOutputStream fout = new FileOutputStream(f);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			Task[] all=new Task[allTask.size()];
			for(int i=0;i<allTask.size();i++){
				all[i]=allTask.get(i);
			}
			out.writeObject(all);
			out.close();
		}
		catch(IOException e){
			e.printStackTrace();
			System.out.println("save error!!!");
		}
	}

	@Override
	public ArrayList<Task> loadTasks(){
		ArrayList<Task> allTask=new ArrayList<Task>();
		try{
			File f=new File("/tasks.txt");
			FileInputStream fin=new FileInputStream(f);
			ObjectInputStream in=new ObjectInputStream(fin);
			Task[] all=(Task[])in.readObject();
			for(int i=0;i<all.length;i++){
				allTask.add(all[i]);
			}
			in.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("load error!");
		}
		return allTask;

	}

}
