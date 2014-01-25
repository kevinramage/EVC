package fr.istic.evc.Clone;

import java.rmi.RemoteException;

import fr.istic.evc.Command.CmdUpdateOrientation;
import fr.istic.evc.Command.CmdUpdatePosition;
import fr.istic.evc.Command.I_Command;
import fr.istic.evc.device.Mouse;
import fr.istic.evc.project.IEntity;
import fr.istic.evc.project.Server;

public class CmdDeleteClone implements I_Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	
	@Override
	public void execute(IEntity entity) {
		// On récupère le clone et le clonable depuis le monde
		CClone clone = (CClone)entity.getWorld().getObjectById(id);
		CClonable clonable = ((CClonable)entity.getWorld().getObjectById(clone.getIdClonable()));
		
		// Si le client est en train de supprimer son propre clone, on
		// lui réattribue le picking du clonable
		if (entity.getId() == clone.getIdClient()) {
			System.out.println(entity.getTitle()+" "+entity.getId()+" peut repiquer le clonable");
			entity.getWorld().getDevices().get(0).removeFromBlackList(clonable);
			((Mouse)entity.getWorld().getDevices().get(0)).clearBlackList();
		}
		
		// On enleve le clone de la liste des clones du clonable
		clonable.removeClone(clone);
		
		// On enleve le clone de l'entité
		entity.getWorld().removeObject(clone);
		
		// Si on est sur le serveur
		if (entity.isServer()) {
			
			// Si le clonable n'a plus qu'un clone -> Le clonable prend la place du dernier clone
			if (clonable.getNbElm() == 1) {
				CClone lastClone = clonable.getLast();
				clonable.updateOrientation(lastClone.getOrientation());
				clonable.updatePosition(lastClone.getPosition());
				((Server)entity).update(new CmdUpdateOrientation(clonable.getId(), lastClone.getOrientation()));
				((Server)entity).update(new CmdUpdatePosition(clonable.getId(), lastClone.getPosition(), false));
				CmdDeleteClone cmd = new CmdDeleteClone();
				cmd.setId(clonable.getLast().getId());
				try {
					((Server)entity).removeObjects(cmd);
				} catch (RemoteException e) {}
			}
		}
		
		// S'il n'y a plus de clone
		if (clonable.getNbElm() == 0) {
			clonable.updatePickable(true);
			clonable.updateSelected(false);
		}
		
	}
	
	public void setId(String id) {
		this.id = id;
	}

}
