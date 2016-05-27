package littlemylyn_14302010039.biz.impl;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import littlemylyn_14302010039.biz.TaskBiz;
import littlemylyn_14302010039.entity.Task;

public class TaskBizImpl implements TaskBiz{

	public void changeState(Task t,int target,ArrayList<Task> allTask){
		if(target==Task.STATE_ACTIVATED){//if we want a task to be activated, the others should be deactivated
			deactivateOtherTask(t,allTask);
		}
		if(target==Task.STATE_NEW){//no task can be change into new state
			JOptionPane.showMessageDialog(null, "Can't change any task's state into new state!");
		}else{
			t.state=target;
		}
	}
	public int getSize(Task t){
		return t.relatedCode.size();
	}
	public void addCode(String s,Task t){
		t.relatedCode.add(s);
	}
	public void deactivateOtherTask(Task t,ArrayList<Task> allTask){
		int act=t.index;
		for(int i=0;i<allTask.size();i++){
			if(allTask.get(i).index!=act&&allTask.get(i).state==Task.STATE_ACTIVATED){
				changeState(allTask.get(i),Task.STATE_FINISHED,allTask);
			}	
		}
	}

}
