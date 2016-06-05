package littlemylyn_14302010039.actions;

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


import littlemylyn_14302010039.biz.impl.TaskBizImpl;
import littlemylyn_14302010039.entity.Task;
public class ConnectTaskAction {

	public ConnectTaskAction(){
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = window.getActivePage();
		/*
		 * add listener to listen the event that open a new editor
		 */
		page.addPartListener(new IPartListener() {
			@Override
			public void partOpened(IWorkbenchPart part) {
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
				//System.out.println("close");
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
						IResourceDeltaVisitor visitor = new IResourceDeltaVisitor() 
						{ 
							//boolean flag;
							ArrayList<Task> tasks;
							IFile oldFile;
							IFile newFile;
							public boolean visit(IResourceDelta delta) 
							{ 
								
								switch(delta.getKind()) 
								{ 
								case IResourceDelta.ADDED: 
									if(delta.getResource() instanceof IFile ){
										String name =( (IFile)delta.getResource()).getName();
										if(name.contains(".class")){
											return true;
										}
										if((delta.getFlags()&IResourceDelta.MOVED_FROM) !=0){
											if(oldFile != null){
												tasks.forEach(e->{
													connect((IFile)delta.getResource(),e);
												});
												oldFile = null;
												newFile = null;
												tasks = null;
											}else{
												newFile = (IFile)delta.getResource();
												tasks = null;
											}
										}else{
										
											connect((IFile)delta.getResource());
										}
									} 
									break; 
								case IResourceDelta.REMOVED: 
									if(delta.getResource() instanceof IFile ){
										if((delta.getFlags()&IResourceDelta.MOVED_TO)!= 0){
											if(newFile == null){
												tasks = new ArrayList<>();
												DisplayTasksAction.allTask.stream().forEach(e->{
													if(e.getRelatedFiles().contains((IFile)delta.getResource())){
														tasks.add(e);
													}
												});
												oldFile = (IFile)delta.getResource();
											}else{
												DisplayTasksAction.allTask.stream().forEach(e->{
													if(e.getRelatedFiles().contains((IFile)delta.getResource())){
														connect(newFile,e);
													}
												});
												oldFile = null;
												newFile = null;
											}
										}
										delete((IFile)delta.getResource());
										
									} 
									break; 
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
		if(DisplayTasksAction.allTask != null) {
			DisplayTasksAction.allTask.stream()
			.forEach(p ->  new TaskBizImpl().deleteRelatedFile(p, file, DisplayTasksAction.allTask) );
		}
	}
	public void connect(IFile original,Task task){
		
		if(task == null){
			return;
		}
		if(original == null){
			return;
		}
		if(task.getRelatedFiles().contains(original)){
			return;
		}
		new TaskBizImpl().addRelatedFile(task, original, DisplayTasksAction.allTask);
	}
	public void connect(IFile original){
		Task task = findActivatedTask();
		if(task == null){
			return;
		}
		if(original == null){
			return;
		}
		if(task.getRelatedFiles().contains(original)){
			return;
		}
		new TaskBizImpl().addRelatedFile(task, original, DisplayTasksAction.allTask);
	}
	public void connect(IWorkbenchPart part){
		if(part instanceof IEditorPart){
			Task task = findActivatedTask();
			if(task == null){
				return;
			}
			IEditorInput input = ((IEditorPart) part).getEditorInput();
			IFile original= (input instanceof IFileEditorInput) ?
					((IFileEditorInput) input).getFile() : null;
					if(original == null){
						return;
					}
					if(task.getRelatedFiles().contains(original)){
						return;
					}
					new TaskBizImpl().addRelatedFile(task, original, DisplayTasksAction.allTask);
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
				if(tmp.getState().equals(Task.ACTIVATED)){
					return tmp;
				}
			}
		}
		return null;
	}
}