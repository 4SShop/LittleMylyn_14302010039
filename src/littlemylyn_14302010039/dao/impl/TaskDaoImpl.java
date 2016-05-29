package littlemylyn_14302010039.dao.impl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import littlemylyn_14302010039.dao.TaskDao;
import littlemylyn_14302010039.entity.Task;

public class TaskDaoImpl implements TaskDao{
	
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
			
		}
	}

	@Override
	public ArrayList<Task> loadTasks() {
		// TODO 自动生成的方法存根
		return null;
	}

}
