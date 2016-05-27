package littlemylyn_14302010039.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Task {
	public static final int TASK_DEBUG=0;
	public static final int TASK_NEWFEATURE=1;
	public static final int TASK_REFACTOR=2;
	public static final int STATE_NEW=0;
	public static final int STATE_ACTIVATED=1;
	public static final int STATE_FINISHED=2;
	public int category;
	public int state;
	public String name;
	public int index;
	public ArrayList<String> relatedCode=new ArrayList<String>();
	public Task(String n,int c,int s,ArrayList<Task> allTask) {
		name=n;
		category=c;
		state=s;
		index=allTask.size();
	}
	public String getName() {
		return this.name;
	}
}
