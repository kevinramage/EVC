package fr.istic.evc.object.common.controller;

import java.util.ArrayList;
import java.util.List;

import fr.istic.evc.gui.InterfaceItem;
import fr.istic.evc.gui.MainFrame;

public class Univers {

	protected List<Object> objects;
	protected List<IDevice> devices;
	protected List<InterfaceItem> items;
	protected MainFrame mainFrame;
	
	
	public Univers() {
		objects = new ArrayList<Object>();
		devices = new ArrayList<IDevice>();
	}
	
	public void add(Object object) {
		objects.add(object);
	}

	public void addDevice(IDevice device) {
		devices.add(device);
	}

	public void addInterfaceItem(InterfaceItem item) {
		items.add(item);
	}

	public void showInterface() {
		mainFrame.show();
	}
	
	

	public MainFrame getMainFrame() {
		return mainFrame;
	}
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

}
