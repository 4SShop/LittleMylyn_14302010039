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
	public abstract ArrayList<Task> loadTasks();
	public abstract void saveTasks(ArrayList<Task> allTask);

}
