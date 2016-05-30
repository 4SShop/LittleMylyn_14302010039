package littlemylyn_14302010039.biz;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;

import littlemylyn_14302010039.entity.Task;
import littlemylyn_14302010039.entity.Tree;

public interface TaskBiz {
	//new a task
	public Task newTask(String name,String type, String state, Tree tree);
	//return a target task based on name
	public Task getTask(String name, ArrayList<Task> allTask);
	//delete a target task
	public void deleteTask(Task task, ArrayList<Task> allTask, Tree tree);
	//change task to a appointed type
	public void changeType(Task task, String type, Tree tree);
	//change task to a appointed state
	public void changeState(Task task, String state, ArrayList<Task> allTask, Tree tree);
	//add related file to the task
	public void addRelatedFile(Task task, IFile file);
}
