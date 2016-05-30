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
			File f=new File("tasks/tasks.txt");
			f.createNewFile();
			FileOutputStream fout = new FileOutputStream(f);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(allTask);
			out.close();
		}
		catch(IOException e){
			System.out.println("save error!!!");
		}
	}

	@Override
	public ArrayList<Task> loadTasks(){
		ArrayList<Task> allTask=new ArrayList<Task>();
		try{
			File f=new File("tasks/tasks.txt");
			FileInputStream fin=new FileInputStream(f);
			ObjectInputStream in=new ObjectInputStream(fin);
			allTask=(ArrayList<Task>)in.readObject();
			in.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("load error!");
		}
		return allTask;
	}

}
