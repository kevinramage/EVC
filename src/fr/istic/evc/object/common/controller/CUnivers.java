package fr.istic.evc.object.common.controller;

import java.util.ArrayList;
import java.util.List;

import fr.istic.evc.gui.InterfaceItem;
import fr.istic.evc.gui.MainFrame;

public class CUnivers {

	private static CUnivers instance;
	protected List<CObject> objects;
	protected List<IDevice> devices;
	protected List<InterfaceItem> items;
	protected MainFrame mainFrame;
	protected CCamera camera;
	
	
	private CUnivers() {
		objects = new ArrayList<CObject>();
		devices = new ArrayList<IDevice>();
		camera = new CCamera();
	}
	
	public void add(CObject object) {
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
	
	
	public static CUnivers getInstance() {
		if ( instance == null)
			instance = new CUnivers();
		
		return instance;
	}

	public CCamera getCamera() {
		return camera;
	}

}
