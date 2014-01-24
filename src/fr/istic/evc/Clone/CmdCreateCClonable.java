package fr.istic.evc.Clone;

import fr.istic.evc.Command.I_CreateCommand;
import fr.istic.evc.object3D.base.abstraction.I_AObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.project.IEntity;

public class CmdCreateCClonable implements I_CreateCommand {
	
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
