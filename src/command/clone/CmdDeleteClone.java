package command.clone;

import object3D.controller.CClonable;
import object3D.controller.CClone;
import project.IEntity;

import command.I_Command;
import command.delete.CmdDeleteCObject;
import command.update.CmdUpdateOrientation;
import command.update.CmdUpdatePosition;

import device.IDevice;


public class CmdDeleteClone implements I_Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	
	@Override
	public void execute(IEntity entity) {
		
		
		// Get clone and clonable associated
		CClone clone = (CClone)entity.getWorld().getObjectById(id);
		CClonable clonable = ((CClonable)entity.getWorld().getObjectById(clone.getIdClonable()));
		
		// If actual user remove it own clone, attribute it the possibility to pick the clonable object
		if (entity.getId() == clone.getIdClient()) {
			for ( IDevice device : entity.getWorld().getDevices()) {
				device.removeFromBlackList(clonable);
			}
		}
		
		// Remove clone from clonable list and world presentation
		clonable.removeClone(clone);
		entity.getWorld().removeObject(clone);
		
		// Check if the number of clone is just one
		if (clonable.getNbElm() == 1) {
			
			// Get position and orientation of the last clone
			CClone lastClone = clonable.getLast();
			clonable.updateOrientation(lastClone.getOrientation());
			clonable.updatePosition(lastClone.getPosition());
			
			// Update the position of the clonable
			CmdUpdateOrientation cmdOrientation = new CmdUpdateOrientation(clonable.getId(), lastClone.getOrientation());
			cmdOrientation.execute(entity);
			CmdUpdatePosition cmdPosition = new CmdUpdatePosition(clonable.getId(), lastClone.getPosition(), false);
			cmdPosition.execute(entity);
			
			// Destroy the last clone
			CmdDeleteCObject cmdDestroy = new CmdDeleteCObject(lastClone);
			cmdDestroy.execute(entity);
			
			// Remove the last clone from the clonable list
			clonable.removeClone(lastClone);
		}
		
		
		// Remain no clone
		if (clonable.getNbElm() == 0) {
			clonable.updateSelected(false);
			for ( IDevice device : entity.getWorld().getDevices()) {
				device.removeFromBlackList(clonable);
			}
		}
		
	}
	
	public void setId(String id) {
		this.id = id;
	}

}
