package command.delete;

import java.util.ArrayList;
import java.util.List;

import object3D.controller.CObject;
import object3D.controller.interfaces.ICObject;
import project.IEntity;

import command.I_Command;

import device.IDevice;

public class CmdDeleteCObject implements I_Command {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<String> listId;
	
	public CmdDeleteCObject() {
		listId = new ArrayList<>();
	}
	public CmdDeleteCObject(CObject obj) {
		this();
		listId.add(obj.getId());
	}
	
	@Override
	public void execute(IEntity entity) {
		for (String id:listId) {
			CObject controller = (CObject) entity.getWorld().getObjectById(id);
			entity.getWorld().removeObject(controller);
		}
		for (IDevice device:entity.getWorld().getDevices()) {
			device.unselectedAll();
		}
	}
	
	public void addObjectToRemove(ICObject obj) {
		listId.add(obj.getId());
	}

}
