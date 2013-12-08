package fr.istic.evc.object.common.controller;

import java.util.ArrayList;
import java.util.List;

import fr.istic.evc.gui.InterfaceItem;

public class Univers {

	protected List<Object> objects;
	protected List<IDevice> devices;
	protected List<InterfaceItem> items;
	
	
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

}
