package littlemylyn_14302010039.biz.impl;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.swt.widgets.Display;

import littlemylyn_14302010039.actions.DisplayTasksAction;
import littlemylyn_14302010039.biz.TaskBiz;
import littlemylyn_14302010039.dao.impl.TaskDaoImpl;
import littlemylyn_14302010039.entity.Task;
import littlemylyn_14302010039.entity.Tree;
import littlemylyn_14302010039.entity.TreeNode;

public class TaskBizImpl implements TaskBiz{

	@Override
	public Task newTask(String name, String type, String state, Tree tree, ArrayList<Task> allTask) {
		// TODO 自动生成的方法存根
		Task task=new Task(name,type,state);
		new TreeBizImpl().addTask(task, tree);
		allTask.add(task);
		new TaskDaoImpl().saveTasks(allTask);
		refresh();
		return task;
	}

	@Override
	public Task getTask(String name, ArrayList<Task> allTask) {
		// TODO 自动生成的方法存根
		return allTask.stream().filter((p)->(p.getName().equals(name))).findFirst().orElse(null);
	}

	@Override
	public void deleteTask(Task task, ArrayList<Task> allTask, Tree tree) {
		// TODO 自动生成的方法存根
		new TreeBizImpl().deleteTask(task, tree);
		allTask.remove(task);
		new TaskDaoImpl().saveTasks(allTask);
		refresh();
	}

	@Override
	public void changeType(Task task, ArrayList<Task> allTask, String type, Tree tree) {
		// TODO 自动生成的方法存根
		TreeNode node=new TreeBizImpl().TtoTN(task, tree);
		task.setType(type);
		new TreeBizImpl().changeType(node, type);
		new TaskDaoImpl().saveTasks(allTask);
		refresh();
	}

	@Override
	public void changeState(Task task, ArrayList<Task> allTask, String state,Tree tree) {
		// TODO 自动生成的方法存根
		if(state.equals("Activated")){
			allTask.stream().
			filter((p)->(p.getState().equals("Activated"))).
			forEach((p)->p.setState("Finished"));
		}
		else if(state.equals("New")){
			return;
		}
		TreeNode node=new TreeBizImpl().TtoTN(task, tree);
		task.setState(state);
		new TreeBizImpl().changeState(node, state);
		new TaskDaoImpl().saveTasks(allTask);
		refresh();
	}

	@Override
	public void addRelatedFile(Task task, IFile file, ArrayList<Task> allTask) {
		// TODO 自动生成的方法存根
		TreeNode parent = new TreeBizImpl().TtoTN(task, DisplayTasksAction.tree);
		task.addFile(file);
		new TreeBizImpl().addClasses(parent, file);
		//new TaskDaoImpl().saveTasks(allTask);
		refresh();
	}

	@Override
	public ArrayList<Task> getAllTask() {
		// TODO 自动生成的方法存根
		return new TaskDaoImpl().loadTasks();
	}

	@Override
	public void refresh() {
		// TODO 自动生成的方法存根
		
		Display.getDefault().asyncExec(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				DisplayTasksAction.getTreeViewer().setInput(DisplayTasksAction.tree.getRoot());
			}
			
		});
		
	}
	@Override
	public void deleteRelatedFile(Task task, IFile file, ArrayList<Task> allTask) {
		// TODO 自动生成的方法存根
		TreeBizImpl tbl = new TreeBizImpl();
		TreeNode treenode = tbl.TtoTN(task, DisplayTasksAction.tree);
		task.getRelatedFiles().remove(file);
		tbl.deleteFileNode(treenode,file);
		//tbl.deleteNode(treenode);
		//allTask.remove(task);
		//new TaskDaoImpl().saveTasks(allTask);
		refresh();
	}

}