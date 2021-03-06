package littlemylyn_14302010039.biz;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;

import littlemylyn_14302010039.entity.Task;
import littlemylyn_14302010039.entity.Tree;
import littlemylyn_14302010039.entity.TreeNode;

public interface TaskBiz {
	//new a task
	public Task newTask(String name,String type, String state, Tree tree, ArrayList<Task> allTask);
	//return a target task based on name
	public Task getTask(String name, ArrayList<Task> allTask);
	//delete a target task
	public void deleteTask(Task task, ArrayList<Task> allTask, Tree tree);
	//change task to a appointed type
	public void changeType(Task task, ArrayList<Task> allTask, String type, Tree tree);
	//change task to a appointed state
	public void changeState(Task task, ArrayList<Task> allTask, String state, Tree tree);
	//add related file to the task
	public void addRelatedFile(Task task, IFile file, ArrayList<Task> allTask);
	//delete a related file
	public void deleteRelatedFile(Task task, IFile file, ArrayList<Task> allTask);
	//get All task from dao
	public ArrayList<Task> getAllTask();
	//refresh the display
	public void refresh();
}