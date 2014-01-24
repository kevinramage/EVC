package fr.istic.evc.Clone;

import java.rmi.RemoteException;

import fr.istic.evc.Command.CmdUpdateOrientation;
import fr.istic.evc.Command.CmdUpdatePosition;
import fr.istic.evc.Command.I_Command;
import fr.istic.evc.device.Mouse;
import fr.istic.evc.project.IEntity;
import fr.istic.evc.project.Server;

public class CmdDeleteClone implements I_Command {

	private String id;
	
	@Override
	public void execute(IEntity entity) {
		CClone clone = (CClone)entity.getWorld().getObjectById(id);
		CClonable clonable = ((CClonable)entity.getWorld().getObjectById(clone.getIdClonable()));
		
		if (entity.getId() == clone.getIdClient()) {
			System.out.println("Le client "+entity.getId()+" peut repiquer le clonable");
			entity.getWorld().getDevices().get(0).removeFromBlackList(clonable);
			((Mouse)entity.getWorld().getDevices().get(0)).clearBlackList();
		}
		clonable.removeClone(clone);
		entity.getWorld().removeObject(clone);
		if (entity.isServer()) {
			if (clonable.getNbElm() == 1) {
				((Server)entity).update(new CmdUpdateOrientation(clonable.getId(), clonable.getLast().getOrientation()));
				((Server)entity).update(new CmdUpdatePosition(clonable.getId(), clonable.getLast().getPosition(), false));
				CmdDeleteClone cmd = new CmdDeleteClone();
				cmd.setId(clonable.getLast().getId());
				try {
					((Server)entity).removeObjects(cmd);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (clonable.getNbElm() == 0) {
				System.out.println("Prout merde");
				clonable.updatePickable(true);
//				clonable.updateSelected(false);
			}
		}
		
	}
	
	public void setId(String id) {
		this.id = id;
	}

}
