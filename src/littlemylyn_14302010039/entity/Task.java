package littlemylyn_14302010039.entity;


import java.io.Serializable;
import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;

public class Task implements Serializable{
	public static String ACTIVATED = "Activated";
	public static String NEW = "New";
	public static String FINISHED = "Finished";
	private String type;
	private String state;
	private String name;
	private ArrayList<String> relatedFiles = new ArrayList<>();
	public Task(String name, String type, String state) {
		this.name = name;
		this.setType(type);
		this.setState(state);
	}
	public String getName() {
		return this.name;
	}
	/**
	 * @return type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type 要设置的 type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state 要设置的 state
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return hashMap
	 */
	public void addFile(IFile file) {
		relatedFiles.add(file.getFullPath().toOSString());
	}
	
	public void deleteFile(IFile file) {
		relatedFiles.remove(file.getFullPath().toOSString());
	}
	public ArrayList<IFile> getRelatedFiles() {
		ArrayList<IFile> iFiles = new ArrayList<IFile>();
		for(int i=0;i<relatedFiles.size();i++){
			IFile temp = ResourcesPlugin.getWorkspace().getRoot().getFile(Path.fromOSString(relatedFiles.get(i)));
			iFiles.add(temp);
		}
		return iFiles;
	}
}
