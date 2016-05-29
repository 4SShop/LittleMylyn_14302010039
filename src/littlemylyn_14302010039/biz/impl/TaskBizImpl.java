package littlemylyn_14302010039.biz.impl;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import littlemylyn_14302010039.biz.TaskBiz;
import littlemylyn_14302010039.entity.Task;

public class TaskBizImpl implements TaskBiz{

	@Override
	public Task newTask(String name, String type, String state) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Task getTask(String name, ArrayList<Task> allTask) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void deleteTask(String name, ArrayList<Task> allTask) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void changeType(String name, String type) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void changeState(String name, String state, ArrayList<Task> allTask) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void addRelatedFile(Task task, IFile file) {
		// TODO 自动生成的方法存根
		
	}

}
