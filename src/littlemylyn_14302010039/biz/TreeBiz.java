package littlemylyn_14302010039.biz;

import java.util.ArrayList;

import littlemylyn_14302010039.entity.Task;
import littlemylyn_14302010039.entity.Tree;
import littlemylyn_14302010039.entity.TreeNode;

public interface TreeBiz {
	/*
	 * level 1:root
	 * level 2:nodes for tasks
	 * level 3:children of level2:get(0):type;get(1):state;get(2):related classes
	 * level 4:class
	 */
	
	//a new tree
	public Tree newTree();
	public Tree newTree(ArrayList<Task> allTask);
	//get a task from the given tree node
	public Task TNtoT(TreeNode node,ArrayList<Task> allTask);
	//get the type of a task
	public String getType(TreeNode node);
	//get the state of a task
	public String getState(TreeNode node);
	//get the name of a task
	public String getName(TreeNode node);
	//add new tasks
	public void addTask(Task task,Tree tree);
	//add new classes
	public void addClasses(TreeNode node,IFile ifile);
	//change state
	public void changeState(TreeNode node,String state);
	//change type
	public void changeType(TreeNode node,String type);
	//give a task and return the tree node 
	//if there is not a tree node ,return null
	public TreeNode TtoTN(Task task,Tree tree);
	//delete a task
	public void deleteTask(Task task,Tree tree);
}
