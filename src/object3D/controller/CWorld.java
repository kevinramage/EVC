package object3D.controller;

import graphic2D.CameraManager;

import java.util.ArrayList;
import java.util.List;

import object3D.controller.interfaces.ICObject;
import object3D.controller.interfaces.ICWorld;
import object3D.presentation.PWorld;
import object3D.presentation.interfaces.IPWorld;
import device.IDevice;

public class CWorld implements ICWorld{
	
	/* ---------- Attributes ---------- */

	protected IPWorld presentation;
	protected List<ICObject> objects;
	protected List<IDevice> devices;
	protected CameraManager cameraManager;
	


	/* ---------- Constructors ---------- */
		
	public CWorld() {
		presentation = new PWorld(this);
		objects = new ArrayList<ICObject>();
		devices = new ArrayList<>();
	}
	


	/* ---------- Methods ---------- */
	
	@Override
	public ICObject getObjectById(String id) {
		for ( ICObject obj : objects) {
			if (obj.getId().equals(id)) {
				return obj;
			}
		}
		return null;
	}
	


	/* ---------- Getters ---------- */

	@Override
	public IPWorld getPresentation() {
		return presentation;
	}
	
	@Override
	public CameraManager getCameraManager() {
		return cameraManager;
	}

	@Override
	public void add(ICObject object) {
		objects.add(object);
		presentation.add(object.getPresentation());
	}

	@Override
	public void show() {
		presentation.show();
	}

	@Override
	public void addDevice(IDevice device) {
		devices.add(device);
		presentation.addDevice(device);
	}

	@Override
	public List<ICObject> getObjects() {
		return objects;
	}
	
	@Override
	public List<IDevice> getDevices() {
		return devices;
	}

	@Override
	public void setCameraManager(CameraManager cameraManager) {
		this.cameraManager = cameraManager;
	}

	@Override
	public void removeObject(ICObject controller) {
		objects.remove(controller);
		presentation.remove(controller.getPresentation());
	}
}
