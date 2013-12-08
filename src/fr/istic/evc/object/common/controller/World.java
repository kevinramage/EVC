package fr.istic.evc.object.common.controller;

import java.util.ArrayList;
import java.util.List;

import fr.istic.evc.gui.InterfaceItem;

public class World {

	protected List<Object> objects;
	protected List<IDevice> devices;
	protected List<InterfaceItem> interfaceItem;
	
	
	public World() {
		objects = new ArrayList<Object>();
		devices = new ArrayList<IDevice>();
		interfaceItem = new ArrayList<InterfaceItem>();
	}
	
	public void load(String url) {
	}

	public List<Object> getObjects() {
		return objects;
	}

	public List<IDevice> getDevices() {
		return devices;
	}

	public List<InterfaceItem> getInterface() {
		return interfaceItem;
	}

}
