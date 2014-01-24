package fr.istic.evc.object3D.base.controller;

import java.util.ArrayList;
import java.util.List;

import fr.istic.evc.device.IDevice;
import fr.istic.evc.graphic2D.CameraManager;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;
import fr.istic.evc.object3D.base.presentation.PWorld;
import fr.istic.evc.object3D.base.presentation.interfaces.IPWorld;

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
	public void removeObject(CObject controller) {
		objects.remove(controller);
		presentation.remove(controller.getPresentation());
		
	}
}
