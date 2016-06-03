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
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.jface.text.*;
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
		IEditorPart[] ieditorpars = (IEditorPart[]) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
		for(IEditorPart editor : ieditorpars){
			if(!(editor instanceof ITextEditor)){
				System.out.println("not texteditor");
				continue;
			}
			ITextEditor ite = (ITextEditor)editor;
			IDocument doc = ite.getDocumentProvider().getDocument(ite.getEditorInput());
	    //doc.removeDocumentListener();
	    
			doc.addDocumentListener(new IDocumentListener(){

				@Override
				public void documentAboutToBeChanged(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
				}
				@Override
				public void documentChanged(DocumentEvent arg0) {
					System.out.println("file change no");
					System.out.println("no task 2");
					Task task = findActivatedTask();
					if(task == null){
						System.out.println("no task");
						return;
					}
					System.out.println("task");
					IEditorInput input = editor.getEditorInput();
					IFile original= (input instanceof IFileEditorInput) ?
							((IFileEditorInput) input).getFile() : null;
		            if(original == null){
		                System.out.println("no file");
		                 return;
		            }
		            if(task.getRelatedFiles().contains(original)){
		            	System.out.println("success");
		            	return;
		            }
		                  
		                  task.addFile(original);
		                  System.out.println("success");
				}
	    	
			});
		}
		
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		String id = "org.eclipse.ui.navigator.ProjectExplorer";//"org.eclipse.jdt.ui.ProjectsView";//"org.eclipse.jdt.ui.PackageExplorer"
		IViewPart viewPart = page.findView(id);
		ISelectionProvider provider = viewPart.getSite().getSelectionProvider();
		provider.addSelectionChangedListener(new ConnectListener());
		/*id = "org.eclipse.jdt.ui.PackageExplorer";//"org.eclipse.jdt.ui.ProjectsView";//"org.eclipse.jdt.ui.PackageExplorer"
		viewPart = page.findView(id);
		provider = viewPart.getSite().getSelectionProvider();
		provider.addSelectionChangedListener(new ConnectListener());
		*/
	}
	class ConnectListener implements ISelectionChangedListener{
		@Override
		public void selectionChanged(SelectionChangedEvent arg0) {
			// TODO Auto-generated method stub
			final IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
			        .getActiveEditor();
			if (!(editor instanceof ITextEditor)) {
				return;
			}
			System.out.println("selection changed");
			if(!editor.isDirty()){
				ITextEditor ite = (ITextEditor)editor;
				IDocument doc = ite.getDocumentProvider().getDocument(ite.getEditorInput());
		    //doc.removeDocumentListener();
		    
				doc.addDocumentListener(new IDocumentListener(){

					@Override
					public void documentAboutToBeChanged(DocumentEvent arg0) {
					// TODO Auto-generated method stub
					
					}
					@Override
					public void documentChanged(DocumentEvent arg0) {
						System.out.println("file change no");
						System.out.println("no task 2");
						Task task = findActivatedTask();
						if(task == null){
							System.out.println("no task");
							return;
						}
						System.out.println("task");
						IEditorInput input = editor.getEditorInput();
						IFile original= (input instanceof IFileEditorInput) ?
								((IFileEditorInput) input).getFile() : null;
			            if(original == null){
			                System.out.println("no file");
			                 return;
			            }
			            if(task.getRelatedFiles().contains(original)){
			            	System.out.println("success");
			            	return;
			            }
			                  
			                  task.addFile(original);
			                  System.out.println("success");
					}
		    	
				});
			}
		}
		
	}
	/*@Override
	public void run(IAction arg0) {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		String id = "org.eclipse.ui.navigator.ProjectExplorer";//"org.eclipse.jdt.ui.ProjectsView";
		IViewPart viewPart = page.findView(id);
		ISelectionProvider provider = viewPart.getSite().getSelectionProvider();
		provider.addSelectionChangedListener(new ConnectListener());
		IViewSite viewSite = viewPart.getViewSite();
		ISelectionProvider selProvider = viewPart.getSite().getSelectionProvider();
		IEditorPart[] ieditorpars = (IEditorPart[]) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
		final IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
		        .getActiveEditor();
		if (!(editor instanceof ITextEditor)) {
			
		}
		page.addPartListener(new IPartListener() {
		    partOpened(IWorkbenchPart part) {
		        ...
		    }

		    ...
		});
		
		// TODO 自动生成的方法存根
		if(selection != null){
			Object o =  selection.getFirstElement();
			if(o instanceof IFile){
				IFile ifile = (IFile)o;
				for(IEditorPart tmp : ieditorpars){
					if(tmp.getTitle().equals(ifile.getName())){
						Task activated = findActivatedTask();
						activated.addFile(ifile);
					}
				}
				//InputStream ifile.getContents()
			}
		}
	}*/
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
	/*
	@Override
	public void selectionChanged(IAction arg0, ISelection arg1) {
		// TODO 自动生成的方法存根
		selection = (IStructuredSelection)arg1;
	}

	@Override
	public void setActivePart(IAction arg0, IWorkbenchPart arg1) {
		// TODO 自动生成的方法存根
		
	}*/
}