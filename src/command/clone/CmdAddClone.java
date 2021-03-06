package command.clone;

import javax.vecmath.Color3f;

import object3D.controller.CClone;
import object3D.controller.interfaces.ICObject;
import project.Client;
import project.IEntity;

import command.I_Command;

import device.Mouse;


public class CmdAddClone implements I_Command {

	private static final long serialVersionUID = 1L;
	String id;

	public CmdAddClone(String id) {
		this.id = id;
	}

	@Override
	public void execute(IEntity entity) {

		ICObject obj = entity.getWorld().getObjectById(id);
		
		obj.updateAmbientColor(obj.getAbstraction().getBackupColor());
//		obj.setPickable(false);
		if (!entity.isServer()) {
			boolean picked = ((Mouse)entity.getWorld().getDevices().get(0)).picked(obj);
			if (picked) {
				((Mouse)entity.getWorld().getDevices().get(0)).forceUnpick(obj);
				CClone clone = new CClone(entity.getId(), obj.getId(), new Color3f(0.5f, 0.5f, 0.5f), obj.getOrientation(), obj.getPosition());
				((Client)entity).createObject(clone);
			}
		}
	}

}
