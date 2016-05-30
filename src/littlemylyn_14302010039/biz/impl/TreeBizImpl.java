package littlemylyn_14302010039.biz.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.eclipse.core.resources.IFile;

import littlemylyn_14302010039.biz.TreeBiz;
import littlemylyn_14302010039.entity.Task;
import littlemylyn_14302010039.entity.Tree;
import littlemylyn_14302010039.entity.TreeNode;

public class TreeBizImpl implements TreeBiz {

	@Override
	public Tree newTree() {
		// TODO Auto-generated method stub
		TreeNode root=new TreeNode(null,null,null);
		Tree tree=new Tree(root);
		return tree;
	}
	
	@Override
	public Tree newTree(ArrayList<Task> allTask){
		TreeNode root=new TreeNode(null,null,null);
		Tree tree=new Tree(root);
		allTask.stream().forEach((p)->(addTask(p,tree)));
		return tree;
	}

	@Override
	public Task TNtoT(TreeNode node, ArrayList<Task> allTask) {
		// TODO Auto-generated method stub
		Task task=new TaskBizImpl().getTask(getName(node), allTask);
		return task;
	}

	@Override
	public String getType(TreeNode node) {
		// TODO Auto-generated method stub
		return node.getChildren().get(0).getName();
	}

	@Override
	public String getState(TreeNode node) {
		// TODO Auto-generated method stub
		return node.getChildren().get(1).getName();
	}

	@Override
	public String getName(TreeNode node) {
		// TODO Auto-generated method stub
		return node.getName();
	}

	@Override
	public void addTask(Task task, Tree tree) {
		// TODO Auto-generated method stub
		TreeNode root = tree.getRoot();
		TreeNode node=new TreeNode(task.getName(), root,null);
		root.addChild(node);
		node.addChild(new TreeNode(task.getState(), node, null));
		node.addChild(new TreeNode(task.getType(), node, null));
		TreeNode classes=new TreeNode("related class("+task.getRelatedFiles().size()+")",node,null);
		node.addChild(classes);
		for(IFile ifile:task.getRelatedFiles()){
			classes.addChild(new TreeNode(ifile.getName(),classes,null));
		}
	}

	@Override
	public void addClasses(TreeNode node,IFile ifile) {
		// TODO Auto-generated method stub
		TreeNode classes=node.getChildren().get(2);
		classes.addChild(new TreeNode(ifile.getName(),classes,null));
	}

	@Override
	public void changeState(TreeNode node, String state) {
		// TODO Auto-generated method stub
		node.getChildren().get(0).setName(state);
	}

	@Override
	public void changeType(TreeNode node, String type) {
		// TODO Auto-generated method stub
		node.getChildren().get(1).setName(type);
	}

	@Override
	public TreeNode TtoTN(Task task, Tree tree) {
		// TODO Auto-generated method stub
		String name=task.getName();
		ArrayList<TreeNode> nodes=tree.getRoot().getChildren();
		Optional<TreeNode> node=nodes.stream().filter((n)->(n.getName().equals(name))).findFirst();
		return node.orElse(null);
	}

	@Override
	public void deleteTask(Task task, Tree tree) {
		// TODO Auto-generated method stub
		TreeNode node=TtoTN(task,tree);
		tree.getRoot().removeChildren(node);
	}
	
	@Override
	public Task getTaskBasedOnNode(Tree tree, TreeNode node, ArrayList<Task> allTask) {
		// TODO 自动生成的方法存根
		Task task = null;
		if(node != null) {
			while(node.getParent() != tree.getRoot())
				node = node.getParent();
			task = TNtoT(node, allTask);
		}
		return task;
	}
}