package littlemylyn_14302010039.entity;

import java.util.ArrayList;

import org.eclipse.core.resources.IFile;

public class TreeNode {
	private String name;
	private TreeNode parent;
	private ArrayList<TreeNode> children;
	private IFile file;
	//TODO: document
	public TreeNode() {
		
	}
	public TreeNode(String name, TreeNode parent, ArrayList<TreeNode> list) {
		this.name = name;
		this.parent = parent;
		this.children = list;
	}
	public TreeNode(String name, TreeNode parent, ArrayList<TreeNode> list, IFile file) {
		this.name = name;
		this.parent = parent;
		this.children = list;
		this.file = file;
	}
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name 要设置的 name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return parent
	 */
	public TreeNode getParent() {
		return parent;
	}
	/**
	 * @param parent 要设置的 parent
	 */
	public void setParent(TreeNode parent) {
		this.parent = parent;
	}
	/**
	 * @return children
	 */
	public ArrayList<TreeNode> getChildren() {
		return children;
	}
	
	public void addChild(TreeNode child) {
		if(this.children == null) 
			children = new ArrayList<TreeNode>();
		this.children.add(child);
		child.setParent(this);
	}
	
	public void removeChildren(TreeNode child) {
		if(this.children != null && children.size() > 0) 
			this.children.remove(child);
	}
	/**
	 * @return file
	 */
	public IFile getFile() {
		return file;
	}
	/**
	 * @param file 要设置的 file
	 */
	public void setFile(IFile file) {
		this.file = file;
	}

}
