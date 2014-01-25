package command.clone;

import object3D.abstraction.I_AObject;
import object3D.controller.CClonable;
import object3D.controller.interfaces.ICObject;
import project.IEntity;

import command.I_Command;

public class CmdCreateCClonable implements I_Command {
	
	/* ---------- Attributes ---------- */
	
	private static final long serialVersionUID = 1L;
	
	I_AObject abstraction;
	


	/* ---------- Constructors ---------- */
	
	public CmdCreateCClonable(I_AObject abstraction) {
		this.abstraction = abstraction;
	}

	@Override
	public void execute(IEntity entity) {
		ICObject controller;
		controller = new CClonable(abstraction);
		controller.reload();
		controller.setEntity(entity);
		entity.getWorld().add(controller);

	}

}
