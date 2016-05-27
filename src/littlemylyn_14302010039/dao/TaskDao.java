package littlemylyn_14302010039.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import littlemylyn_14302010039.entity.Task;

public interface TaskDao {

	public abstract Task newTask(String n,int c,int s,ArrayList<Task> allTask);
	public abstract Task getTask(int index,ArrayList<Task> allTask);
	public abstract void deleteTask(int index,ArrayList<Task> allTask);
	public abstract ArrayList<Task> loadTasks();
	public abstract void saveTasks(ArrayList<Task> allTask);

}
