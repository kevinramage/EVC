package fr.istic.evc.graphic2D.swing;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import fr.istic.evc.graphic2D.base.Menu;
import fr.istic.evc.graphic2D.base.MenuItem;
import fr.istic.evc.graphic2D.base.Window;

public class SwingMenu extends Menu {

	private JMenuBar jmenu;

	public SwingMenu() {
		jmenu = new JMenuBar();
	}
	
	@Override
	public Object getComponent() {
		return jmenu;
	}
	
	@Override
	public void updateSize() {
		jmenu.setPreferredSize(new Dimension(realWidth, realHeight));
		jmenu.setSize(realWidth, realHeight);
	}
	
	// Add JMenuBar to JFrame
	@Override
	public void linkToWindow(Window window) {
		((JFrame)window.getComponent()).setJMenuBar(jmenu);
	}
	
	// Add a component to JFrame
	@Override
	public void addItem(MenuItem item) {
		super.addItem(item);
		jmenu.add((JMenu) item.getComponent());
	}
}
