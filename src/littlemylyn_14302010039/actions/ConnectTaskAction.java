package littlemylyn_14302010039.actions;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import java.util.ArrayList;

import org.eclipse.core.resources.*;
import littlemylyn_14302010039.entity.Task;
public class ConnectTaskAction {
	IStructuredSelection selection;
	ArrayList<Task> allTasks;
	public ConnectTaskAction(ArrayList<Task> allTask){
		this.allTasks = allTask;
		

		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		/*
		 * add listener to listen the event that open a new editor
		 */
		page.addPartListener(new IPartListener() {
			@Override
			public void partOpened(IWorkbenchPart part) {
				//detect(sampleView, part);
				connect(part);
			}
			
			@Override
			public void partDeactivated(IWorkbenchPart part) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void partClosed(IWorkbenchPart part) {
				/*
				 * when the sample view is closed, save the task to a file
				 */
			}
			
			@Override
			public void partBroughtToTop(IWorkbenchPart part) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void partActivated(IWorkbenchPart part) {
				connect(part);
			}
		});
	}
	/*
	 * when open a new editor add this editor to the activated task
	 */
	public void connect(IWorkbenchPart part){
		if(part instanceof IEditorPart){
			Task task = findActivatedTask();
			IEditorInput input = ((IEditorPart) part).getEditorInput();
			IFile original= (input instanceof IFileEditorInput) ?
					((IFileEditorInput) input).getFile() : null;
            if(original == null){
                //System.out.println("no file");
                 return;
            }
            if(task.getRelatedFiles().contains(original)){
            	System.out.println("success c"+original.getName());
            	return;
            }
            task.addFile(original);
            System.out.println("success"+original.getName());
            /*
             * if modified the class and add it use this
             */
            /*
            if(((IEditorPart) part).isDirty()){
            	Task task = findActivatedTask();
    			IEditorInput input = ((IEditorPart) part).getEditorInput();
    			IFile original= (input instanceof IFileEditorInput) ?
    					((IFileEditorInput) input).getFile() : null;
                if(original == null){
                    //System.out.println("no file");
                     return;
                }
                if(task.getRelatedFiles().contains(original)){
                	System.out.println("success c"+original.getName());
                	return;
                }
                task.addFile(original);
                System.out.println("success"+original.getName());
            }*/
		}
	}
	
	public void setAllTask(ArrayList<Task> allTask){
		this.allTasks = allTask;
	}
	/*
	 * find the activated task now
	 */
	public Task findActivatedTask(){
		if(allTasks != null){
			for (Task tmp : allTasks){
				if(tmp.getState().equals(Task.ACTIVATED))
					return tmp;
			}
		}
		return null;
	}
	
}