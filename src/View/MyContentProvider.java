package View;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import littlemylyn_14302010039.biz.TaskBiz;
import littlemylyn_14302010039.biz.impl.TaskBizImpl;
import littlemylyn_14302010039.entity.Task;
import littlemylyn_14302010039.entity.TreeNode;

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
		return ((TreeNode)arg0).getChildren().toArray();
	}

	@Override
	public Object[] getElements(Object arg0) {
		// TODO 自动生成的方法存根
		return ((TreeNode)arg0).getChildren().toArray();
	}

	@Override
	public Object getParent(Object arg0) {
		// TODO 自动生成的方法存根
		return ((TreeNode)arg0).getParent();
	}

	@Override
	public boolean hasChildren(Object arg0) {
		// TODO 自动生成的方法存根
		ArrayList<TreeNode> children = ((TreeNode)arg0).getChildren();
		return (children == null) ? false : (children.size() > 0);
	}
	
	private ArrayList<Task> getAllTasks() {
		TaskBiz taskbiz = new TaskBizImpl();
		return null;
	}
}
