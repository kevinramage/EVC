package command.update;

import object3D.controller.interfaces.ICObject;
import project.IEntity;

import command.I_Command;



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
