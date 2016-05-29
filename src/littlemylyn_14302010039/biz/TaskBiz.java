package littlemylyn_14302010039.biz;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;

import littlemylyn_14302010039.entity.Task;

public interface TaskBiz {
	//new a task
	public Task newTask(String name,String type, String state);
	//return a target task based on name
	public Task getTask(String name, ArrayList<Task> allTask);
	//delete a target task
	public void deleteTask(String name, ArrayList<Task> allTask);
	//change task to a appointed type
	public void changeType(String name, String type);
	//change task to a appointed state
	public void changeState(String name, String state, ArrayList<Task> allTask);
	//add related file to the task
	public void addRelatedFile(Task task, IFile file);
}
