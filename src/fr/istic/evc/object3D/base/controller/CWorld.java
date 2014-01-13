package fr.istic.evc.object3D.base.controller;

import java.util.ArrayList;
import java.util.List;

import fr.istic.evc.Command.Command;
import fr.istic.evc.device.IDevice;
import fr.istic.evc.network.MulticastSender;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;
import fr.istic.evc.object3D.base.presentation.PWorld;
import fr.istic.evc.object3D.base.presentation.interfaces.IPWorld;
import fr.istic.evc.project.Server;

public class CWorld implements ICWorld{

	protected IPWorld presentation;
	protected List<ICObject> objects;
	protected List<IDevice> device;
//	private Server server;
	
	
	public CWorld() {
		presentation = new PWorld(this);
		objects = new ArrayList<ICObject>();
	}

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

//	@Override
//	public void setServer(Server server) {
//		this.server = server;
//		
//	}
}
