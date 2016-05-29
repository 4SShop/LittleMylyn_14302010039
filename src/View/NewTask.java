package View;

import java.util.ArrayList;

import littlemylyn_14302010039.actions.DisplayTasksAction;
import littlemylyn_14302010039.biz.TaskBiz;
import littlemylyn_14302010039.biz.impl.TaskBizImpl;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
public class NewTask extends Wizard {

	private static final String title = "New a task";
	static final String page1 = "PAGE_1";
	private TaskPage page;
	private ISelection selection;
	public NewTask() {
		
		this.setWindowTitle(title);
		
	}
	public void init(IWorkbench arg0, IStructuredSelection selection) {
		this.selection = selection;
	}
	@Override
	public boolean performFinish() {
		// TODO 自动生成的方法存根
		String name = page.getTaskName();
		String type = page.getType();
		String state = page.getState();
		TaskBiz taskbiz = new TaskBizImpl();
		
		//new a task
		DisplayTasksAction.allTask.add(taskbiz.newTask(name, type, state));
		//add a tree
		
		//refresh the viewPart
		DisplayTasksAction.getTreeViewer().setInput(DisplayTasksAction.root);
		return true;
	}
	//add page in the wizard
	public void addPages() {
		page = new TaskPage(selection);
		this.addPage(page);
	}


}
class TaskPage extends WizardPage {
	private Text name;
	private ArrayList<Button> type_btn = new ArrayList<Button>();
	private ArrayList<Button> state_btn = new ArrayList<Button>();
	private ISelection selection;
	TaskPage(ISelection selection) {
		super(NewTask.page1, "New a task：", ImageDescriptor.createFromFile(TaskPage.class, "q.gif"));
		this.setMessage("Create a new task for your project!");
		this.setSelection(selection);
		setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		// TODO 自动生成的方法存根
		Composite composite = new Composite(parent, SWT.NONE); 
		composite.setLayout(new GridLayout());
		
		Composite sub1 = new Composite(composite, SWT.NONE);
		sub1.setLayout(new GridLayout(2, false));
		new Label(sub1, SWT.LEFT).setText("Name:");  
		name = new Text(sub1, SWT.BORDER | SWT.SINGLE);
		name.setSize(40, 10);
        name.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent arg0) {
				// TODO 自动生成的方法存根
				if(!name.getText().equals("")) {
					setPageComplete(true);
				}
				else {
					setPageComplete(false);
				}
			}
        });
        
        
        Composite sub2 = new Composite(composite, SWT.NONE);
        sub2.setLayout(new GridLayout(1, false)); 
        Composite radio1 = new Composite(sub2, SWT.NONE);
        radio1.setLayout(new GridLayout(4, false));
		new Label(radio1, SWT.LEFT).setText("Type:");
		for (int i = 0; i < 3; i++) {
			type_btn.add(new Button(radio1, SWT.RADIO));  
		}
		type_btn.get(0).setText("debug");
		type_btn.get(0).setSelection(true);
		type_btn.get(1).setText("new feature");
		type_btn.get(2).setText("refactor");
		
        Composite radio2 = new Composite(sub2, SWT.NONE);
        radio2.setLayout(new GridLayout(4, false));
        new Label(radio2, SWT.LEFT).setText("State:"); 
        for (int i = 0; i < 3; i++) {
			state_btn.add(new Button(radio2, SWT.RADIO));  
		}
        
        state_btn.get(0).setText("New"); 
        state_btn.get(0).setSelection(true);
        state_btn.get(1).setText("Activated");
        state_btn.get(2).setText("Finished");
        setControl(composite);  
        
	}

	/**
	 * @return type
	 */
	public String getType() {
		return type_btn.stream().filter(btn -> (btn.getSelection())).findFirst().orElse(null).getText();
	}

	/**
	 * @return state
	 */
	public String getState() {
		return state_btn.stream().filter(btn -> (btn.getSelection())).findFirst().orElse(null).getText();
	}
	public Text getTaskText() {
		return this.name;
	}
	public String getTaskName() {
		return name.getText();
	}

	/**
	 * @return selection
	 */
	public ISelection getSelection() {
		return selection;
	}

	/**
	 * @param selection 要设置的 selection
	 */
	public void setSelection(ISelection selection) {
		this.selection = selection;
	} 

}
