package fr.istic.evc.object3D.base.controller;

import java.util.ArrayList;
import java.util.List;

import fr.istic.evc.device.IDevice;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;
import fr.istic.evc.object3D.base.presentation.PWorld;
import fr.istic.evc.object3D.base.presentation.interfaces.IPWorld;

public class CWorld implements ICWorld{
	
	/* ---------- Attributes ---------- */

	protected IPWorld presentation;
	protected List<ICObject> objects;
	protected List<IDevice> device;
	


	/* ---------- Constructors ---------- */
		
	public CWorld() {
		presentation = new PWorld(this);
		objects = new ArrayList<ICObject>();
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
		presentation.addDevice(device);
	}

	@Override
	public List<ICObject> getObjects() {
		return objects;
	}
}
