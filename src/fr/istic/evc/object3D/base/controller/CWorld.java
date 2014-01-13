package fr.istic.evc.object3D.base.controller;

import java.util.ArrayList;
import java.util.List;

import fr.istic.evc.device.IDevice;
import fr.istic.evc.network.MulticastSender;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;
import fr.istic.evc.object3D.base.presentation.PWorld;
import fr.istic.evc.object3D.base.presentation.interfaces.IPWorld;

public class CWorld implements ICWorld{

	protected IPWorld presentation;
	protected List<ICObject> objects;
	protected List<IDevice> device;
	protected MulticastSender sender;
	protected boolean referent;
	
	
	public CWorld() {
		presentation = new PWorld(this);
		objects = new ArrayList<ICObject>();
	}
	
	public CWorld(boolean referent) {
		this();
		this.referent = referent;
	}

	@Override
	public IPWorld getPresentation() {
		return presentation;
	}

	@Override
	public void add(ICObject object) {
		objects.add(object);
		presentation.add(object.getPresentation());
		
		if (referent) {
			sender.createObject(object.getAbstraction());
		}
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
	public void setMulticastSender(MulticastSender sender) {
		this.sender = sender;
	}

	@Override
	public List<ICObject> getObjects() {
		return objects;
	}

	@Override
	public void reSend(ICObject o) {
		if ( referent ) {
			sender.createObject(o.getAbstraction());
		}
	}
}
