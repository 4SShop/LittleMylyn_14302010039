package littlemylyn_14302010039.actions;

import littlemylyn_14302010039.biz.TaskBiz;
import littlemylyn_14302010039.biz.TreeBiz;
import littlemylyn_14302010039.biz.impl.TaskBizImpl;
import littlemylyn_14302010039.biz.impl.TreeBizImpl;
import littlemylyn_14302010039.entity.Task;
import littlemylyn_14302010039.entity.TreeNode;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

public class DeleteAction implements IObjectActionDelegate{
	IStructuredSelection selection;
	private TaskBiz taskbiz = new TaskBizImpl();
	private TreeBiz treebiz = new TreeBizImpl();
	@Override
	public void run(IAction arg0) {
		// TODO 自动生成的方法存根

		if(selection != null) {
			TreeNode node = (TreeNode)selection.getFirstElement();
			Task task = treebiz.getTaskBasedOnNode(DisplayTasksAction.tree, node, DisplayTasksAction.allTask);
			if(task != null) {
				try{
					taskbiz.deleteRelatedFile(task, node.getFile(), DisplayTasksAction.allTask);
				}catch(NullPointerException ex) {
					taskbiz.deleteTask(task, DisplayTasksAction.allTask, DisplayTasksAction.tree);
				}
			}
		}
	}

	@Override
	public void selectionChanged(IAction arg0, ISelection arg1) {
		// TODO 自动生成的方法存根
		this.selection = ((IStructuredSelection) arg1);
	}

	@Override
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {
		// TODO 自动生成的方法存根

	}
}

