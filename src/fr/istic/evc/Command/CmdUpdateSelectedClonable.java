package fr.istic.evc.Command;

import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.project.IEntity;

public class CmdUpdateSelectedClonable implements I_Command {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private boolean selected;

	public CmdUpdateSelectedClonable(String id, boolean selected) {
		this.id = id;
		this.selected = selected;
	}

	@Override
	public void execute(IEntity entity) {
		ICObject obj = entity.getWorld().getObjectById(id);
		
//		if (entity.havePicked(obj)) {
//			System.out.println("Prout");
//		}
		
		obj.updateSelected(selected);
	}

}
