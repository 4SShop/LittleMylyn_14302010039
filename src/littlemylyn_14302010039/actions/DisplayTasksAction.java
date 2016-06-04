package littlemylyn_14302010039.actions;


import java.util.ArrayList;

import littlemylyn_14302010039.biz.TaskBiz;
import littlemylyn_14302010039.biz.TreeBiz;
import littlemylyn_14302010039.biz.impl.TaskBizImpl;
import littlemylyn_14302010039.biz.impl.TreeBizImpl;
import littlemylyn_14302010039.entity.Task;
import littlemylyn_14302010039.entity.Tree;
import littlemylyn_14302010039.entity.TreeNode;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.*;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.*;
import org.eclipse.jface.action.*;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;

import View.MyContentProvider;
import View.MyLableProvider;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class DisplayTasksAction extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "littlemylyn_14302010039.DisplayTasksAction";
	public static ArrayList<Task> allTask;
	public static Tree tree;
	private static TreeViewer viewer;
	private Action doubleClickAction;

	/*
	 * The content provider class is responsible for
	 * providing objects to the view. It can wrap
	 * existing objects in adapters or simply return
	 * objects as-is. These objects may be sensitive
	 * to the current input of the view, or ignore
	 * it and always show the same content 
	 * (like Task List, for example).
	 */
	static {
		TaskBiz taskbiz = new TaskBizImpl();
		TreeBiz treebiz = new TreeBizImpl();
		allTask = taskbiz.getAllTask();
		new ConnectTaskAction(allTask);
		tree = treebiz.newTree(allTask);
	}
	
	/**
	 * The constructor.
	 */
	public DisplayTasksAction() {
		TaskBiz taskbiz = new TaskBizImpl();
		TreeBiz treebiz = new TreeBizImpl();
		allTask = taskbiz.getAllTask();
		tree = treebiz.newTree(allTask);
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		TreeNode root = tree.getRoot();
		
		viewer = new TreeViewer(parent);
		viewer.setContentProvider(new MyContentProvider());
		viewer.setLabelProvider(new MyLableProvider());
		viewer.setInput(root);

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "view.viewer");
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
	}

	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				DisplayTasksAction.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
	}

	private void fillContextMenu(IMenuManager manager) {
		
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		
	}

	private void makeActions() {
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection)selection).getFirstElement();
				TreeNode node = (TreeNode)obj;
				if(node.getFile() != null) {
//					IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("1");
//					System.out.println("The full path:" + project.getFullPath());
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					IFile file = node.getFile();
					try {
						IDE.openEditor(page, file);
					} catch (PartInitException e) {
						// TODO 自动生成的 catch 块
						System.out.println("wrong");
					}
				}
				
			}
		};
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}
	
	public static TreeViewer getTreeViewer() {
		return viewer;
	}
}