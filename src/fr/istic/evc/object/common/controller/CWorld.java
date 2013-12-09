package fr.istic.evc.object.common.controller;

import java.util.ArrayList;
import java.util.List;

import fr.istic.evc.gui.InterfaceItem;
import fr.istic.evc.gui.MainFrame;

public class CWorld {

	protected List<CObject> objects;
	protected List<IDevice> devices;
	protected List<InterfaceItem> interfaceItem;
	
	
	public CWorld() {
		objects = new ArrayList<CObject>();
		devices = new ArrayList<IDevice>();
		interfaceItem = new ArrayList<InterfaceItem>();
	}
	
	public void load(String url) {
	}

	public List<CObject> getObjects() {
		return objects;
	}

	public List<IDevice> getDevices() {
		return devices;
	}

	public List<InterfaceItem> getInterface() {
		return interfaceItem;
	}

}
