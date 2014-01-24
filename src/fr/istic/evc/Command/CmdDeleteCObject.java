package fr.istic.evc.Command;

import java.util.ArrayList;
import java.util.List;

import fr.istic.evc.device.IDevice;
import fr.istic.evc.object3D.base.controller.CObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.project.IEntity;

public class CmdDeleteCObject implements I_Command {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<String> listId;
	
	public CmdDeleteCObject() {
		listId = new ArrayList<>();
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
