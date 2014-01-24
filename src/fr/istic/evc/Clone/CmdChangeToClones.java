package fr.istic.evc.Clone;

import javax.vecmath.Color3f;

import fr.istic.evc.Command.I_Command;
import fr.istic.evc.device.Mouse;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.project.Client;
import fr.istic.evc.project.IEntity;

public class CmdChangeToClones implements I_Command {
	
	private String id;
	
	public CmdChangeToClones(String id) {
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
				entity.getWorld().getDevices().get(0).forceUnpick(obj);
				entity.getWorld().getDevices().get(0).addToBlackList(obj);
				CClone clone = new CClone(entity.getId(), obj.getId(), new Color3f(0.5f, 0.5f, 0.5f), obj.getOrientation(), obj.getPosition());
				((Client)entity).createObject(clone);
			}
		}
	}

}
