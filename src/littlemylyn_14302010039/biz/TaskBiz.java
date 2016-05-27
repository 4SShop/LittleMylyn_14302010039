package littlemylyn_14302010039.biz;

import java.util.ArrayList;

import littlemylyn_14302010039.entity.Task;

public interface TaskBiz {
	public abstract void changeState(Task t,int target,ArrayList<Task> allTask);
	public abstract int getSize(Task t);
	public abstract void addCode(String s,Task t);
	public abstract void deactivateOtherTask(Task t,ArrayList<Task> allTask);
}
