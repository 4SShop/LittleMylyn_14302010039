package View;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

import Entity.Task;

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
		if(arg0 instanceof Task)
			return ((Task) arg0).getName();
		else 
			return null;
	}

}
