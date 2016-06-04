package littlemylyn_14302010039.actions;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.jface.text.*;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import org.eclipse.core.resources.*;
import org.eclipse.jface.action.*;
import littlemylyn_14302010039.entity.Task;
import littlemylyn_14302010039.entity.TreeNode;
public class ConnectTaskAction {
	IStructuredSelection selection;
	ArrayList<Task> allTasks;
	public ConnectTaskAction(ArrayList<Task> allTask){
		this.allTasks = allTask;
		//this.documentListeners = new ArrayList<>();

		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
//		IWorkspace space = ResourcesPlugin.getWorkspace();
		
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
		}
	}
	
	public void setAllTask(ArrayList<Task> allTask){
		this.allTasks = allTask;
	}
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