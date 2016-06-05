package littlemylyn_14302010039.test;

import java.util.ArrayList;

import littlemylyn_14302010039.dao.impl.TaskDaoImpl;
import littlemylyn_14302010039.entity.Task;

public class TaskDaoTest {

	public static void main(String[] args){
		TaskDaoImpl tDao=new TaskDaoImpl();
		ArrayList<Task> allTask=new ArrayList<Task>();
		Task t=new Task("123","refactor","new");
		allTask.add(t);
		tDao.saveTasks(allTask);
		ArrayList<Task> load=tDao.loadTasks();
		System.out.println(load.get(0).getName());
		System.out.println(load.get(0).getType());
		System.out.println(load.get(0).getState());
	}

}
