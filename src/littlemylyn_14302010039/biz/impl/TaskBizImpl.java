package littlemylyn_14302010039.biz.impl;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import littlemylyn_14302010039.biz.TaskBiz;
import littlemylyn_14302010039.entity.Task;

public class TaskBizImpl implements TaskBiz{

	@Override
	public Task newTask(String name, String type, String state) {
		// TODO 自动生成的方法存根
		Task task=new Task(name,type,state);
		return task;
	}

	@Override
	public Task getTask(String name, ArrayList<Task> allTask) {
		// TODO 自动生成的方法存根
		for(int i=0;i<allTask.size();i++){
			if(allTask.get(i).getName().equals(name)){
				return allTask.get(i);
			}
		}
		return null;
	}

	@Override
	public void deleteTask(String name, ArrayList<Task> allTask) {
		// TODO 自动生成的方法存根
		for(int i=0;i<allTask.size();i++){
			if(allTask.get(i).getName().equals(name)){
				allTask.remove(i);
			}
		}
	}

	@Override
	public void changeType(String name, String type, ArrayList<Task> allTask) {
		// TODO 自动生成的方法存根
		for(int i=0;i<allTask.size();i++){
			if(allTask.get(i).getName().equals(name)){
				allTask.get(i).setType(type);
			}
		}
	}

	@Override
	public void changeState(String name, String state, ArrayList<Task> allTask) {
		// TODO 自动生成的方法存根
		for(int i=0;i<allTask.size();i++){
			if(allTask.get(i).getName().equals(name)){
				allTask.get(i).setState(state);
			}
		}
	}

	@Override
	public void addRelatedFile(Task task, IFile file) {
		// TODO 自动生成的方法存根
		task.addFile(file);
	}

}
