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

import littlemylyn_14302010039.biz.impl.TaskBizImpl;
import littlemylyn_14302010039.entity.Task;
import littlemylyn_14302010039.entity.TreeNode;
public class ConnectTaskAction {
	IStructuredSelection selection;
	//ArrayList<Task> allTasks;
	//private ArrayList<DocumentListener> documentListeners;
	public ConnectTaskAction(){
		//this.allTasks = allTask;


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
				System.out.println("close");
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
		
		ResourcesPlugin.getWorkspace().addResourceChangeListener(
				   new  IResourceChangeListener(){

					@Override
					public void resourceChanged(IResourceChangeEvent arg0) {
						IResourceDelta delta = arg0.getDelta();
						//IResource resources = delta.getResource();
						IResourceDeltaVisitor visitor = new IResourceDeltaVisitor() 
						{ 
							public boolean visit(IResourceDelta delta) 
							{ 
								switch(delta.getKind()) 
								{ 
									case IResourceDelta.ADDED: 
										if(delta.getResource() instanceof IFile ){ 
											System.out.println("file add");
											String name =( (IFile)delta.getResource()).getName();
											if(name.contains(".class")){
												return true;
											}
											connect((IFile)delta.getResource());
										} 
										break; 
									case IResourceDelta.REMOVED: 
										if(delta.getResource() instanceof IFile ){ 
											System.out.println("file removed");
											delete((IFile)delta.getResource());
										} 
										break; 
									/*case IResourceDelta.CHANGED:
										if(delta.getResource() instanceof IFile ){ 
											System.out.println("chage");
											connect((IFile)delta.getResource());
										} 
									break;*/
									//case IResourceDelta.
								} 
								return true; 
						} 
						}; 
						try{
							delta.accept(visitor); 
						}catch(Exception ex){
							ex.printStackTrace();
						}
					}
					   
		 }, IResourceChangeEvent.POST_CHANGE);
		
		
		
		
	}
	/*
	 * when open a new editor add this editor to the activated task
	 */
	public void delete(IFile file){
		Task task = findActivatedTask();
		if(task == null){
            System.out.println("no task");
             return;
        }
		if(task.getRelatedFiles().contains(file)){
			new TaskBizImpl().deleteRelatedFile(task, file, DisplayTasksAction.allTask);
			//task.deleteFile(file);
			System.out.println("delete success" + file.getName());
		}
	}
	public void connect(IFile original){
		Task task = findActivatedTask();
		if(task == null){
            System.out.println("no task");
             return;
        }
		if(original == null){
            System.out.println("no file");
             return;
        }
        if(task.getRelatedFiles().contains(original)){
        	System.out.println("success c"+original.getName());
        	return;
        }
        new TaskBizImpl().addRelatedFile(task, original, DisplayTasksAction.allTask);
        //task.addFile(original);
        System.out.println("success1"+original.getName());
	}
	public void connect(IWorkbenchPart part){
		if(part instanceof IEditorPart){
			Task task = findActivatedTask();
			if(task == null){
	            System.out.println("no task");
	             return;
	        }
			IEditorInput input = ((IEditorPart) part).getEditorInput();
			IFile original= (input instanceof IFileEditorInput) ?
					((IFileEditorInput) input).getFile() : null;
            if(original == null){
                System.out.println("no file");
                 return;
            }
            if(task.getRelatedFiles().contains(original)){
            	System.out.println("success c"+original.getName());
            	return;
            }
            new TaskBizImpl().addRelatedFile(task, original, DisplayTasksAction.allTask);
            System.out.println("succes2s"+original.getName());
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

	
	public Task findActivatedTask(){
		if(DisplayTasksAction.allTask != null){
			for (Task tmp : DisplayTasksAction.allTask){
				System.out.println("aaaa");
				if(tmp.getState().equals(Task.ACTIVATED)){
				
					return tmp;
				}
			}
		}
		return null;
	}
	
	
}