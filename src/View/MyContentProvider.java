package View;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import littlemylyn_14302010039.entity.Task;

public class MyContentProvider implements ITreeContentProvider{

	@Override
	public void dispose() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Object[] getChildren(Object arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Object[] getElements(Object arg0) {
		// TODO 自动生成的方法存根
		ArrayList<Task> tasks = getAllTasks();
		Task[] obj = (Task[])tasks.toArray();
		return obj;
	}

	@Override
	public Object getParent(Object arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public boolean hasChildren(Object arg0) {
		// TODO 自动生成的方法存根
		return true;
	}
	
	private ArrayList<Task> getAllTasks() {
		return null;
	}
}
