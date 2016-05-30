package littlemylyn_14302010039.dao;

import java.util.ArrayList;

import littlemylyn_14302010039.entity.Task;

public interface TaskDao {
	public abstract ArrayList<Task> loadTasks();
	public abstract void saveTasks(ArrayList<Task> allTask);

}