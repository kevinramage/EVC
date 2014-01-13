package fr.istic.evc.graphic2D.swing;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import fr.istic.evc.graphic2D.base.MenuItem;
import fr.istic.evc.graphic2D.command.Command;

public class SwingMenuItem extends MenuItem {

	protected Object menu;
	
	public SwingMenuItem() {
	}
	
	@Override
	public Object getComponent() {
		return menu;
	}
	
	@Override
	public void setText(String text) {
		super.setText(text);
		if ( getIsSubMenu()) {
			((JMenu) menu).setText(text);
		} else {
			((JMenuItem) menu).setText(text);
		}
	}
	
	@Override
	public void setIsSubMenu(boolean value) {
		super.setIsSubMenu(value);
		if (value) {
			menu = new JMenu();
		} else {
			menu = new JMenuItem();
		}
	}

	@Override
	public void addItem(MenuItem item) {
		super.addItem(item);
		if (item.getIsSubMenu()) {
			((JMenu)menu).add((JMenu) item.getComponent());
		} else {
			((JMenu)menu).add((JMenuItem)item.getComponent());
		}
	}
	
	@Override
	public void addCommand(Command command) {
		if (isSubMenu) {
			((JMenu)getComponent()).addActionListener(new CmdActionListener(command));
		} else {
			((JMenuItem)getComponent()).addActionListener(new CmdActionListener(command));
		}
	}
}
