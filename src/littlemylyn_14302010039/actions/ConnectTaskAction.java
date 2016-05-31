package littlemylyn_14302010039.actions;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.core.resources.*;
import littlemylyn_14302010039.entity.Task;
import littlemylyn_14302010039.entity.TreeNode;
public class ConnectTaskAction implements IObjectActionDelegate{
	IStructuredSelection selection;
	
	@Override
	public void run(IAction arg0) {
		// TODO 自动生成的方法存根
		if(selection != null){
			Object o =  selection.getFirstElement();
			if(o instanceof IFile){
				IFile ifile = (IFile)o;
				//InputStream ifile.getContents()
			}
		}
	}

	@Override
	public void selectionChanged(IAction arg0, ISelection arg1) {
		// TODO 自动生成的方法存根
		selection = (IStructuredSelection)arg1;
	}

	@Override
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {
		// TODO 自动生成的方法存根
		
	}
}
