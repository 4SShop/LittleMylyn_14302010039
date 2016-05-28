package View;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import littlemylyn_14302010039.entity.Task;
import littlemylyn_14302010039.entity.TreeNode;

public class MyLableProvider implements ILabelProvider {

	@Override
	public void addListener(ILabelProviderListener arg0) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void dispose() {
		// TODO 自动生成的方法存根

	}

	@Override
	public boolean isLabelProperty(Object arg0, String arg1) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener arg0) {
		// TODO 自动生成的方法存根

	}

	@Override
	public Image getImage(Object arg0) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String getText(Object arg0) {
		// TODO 自动生成的方法存根
		String text = "";
		TreeNode node = (TreeNode) arg0;
		if(node.getParent() == null) {
			text = "root";
		}
		else {
			text = ((TreeNode) arg0).getName();
		}
		return text;
	}

}
