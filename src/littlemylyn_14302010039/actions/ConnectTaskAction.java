package littlemylyn_14302010039.actions;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
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
public class ConnectTaskAction implements IObjectActionDelegate{
	IStructuredSelection selection;
	ArrayList<Task> allTasks;
	@Override
	public void run(IAction arg0) {
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		String id = "org.eclipse.ui.navigator.ProjectExplorer";//"org.eclipse.jdt.ui.ProjectsView";
		IViewPart viewPart = page.findView(id);
		IViewSite viewSite = viewPart.getViewSite();
		ISelectionProvider selProvider = viewPart.getSite().getSelectionProvider();
		IEditorPart[] ieditorpars = (IEditorPart[]) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences();
		final IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
		        .getActiveEditor();
		if (!(editor instanceof ITextEditor)) {
			
		}
		ITextEditor ite = (ITextEditor)editor;
	    IDocument doc = ite.getDocumentProvider().getDocument(ite.getEditorInput());
	    doc.addDocumentListener(new IDocumentListener(){

			@Override
			public void documentAboutToBeChanged(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void documentChanged(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				Task task = findActivatedTask();
			}
	    	
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