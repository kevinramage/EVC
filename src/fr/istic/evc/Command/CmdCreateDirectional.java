package fr.istic.evc.Command;

import fr.istic.evc.object3D.base.abstraction.I_AObject;
import fr.istic.evc.object3D.base.controller.CDirectionalLight;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.project.IEntity;

public class CmdCreateDirectional implements I_CreateCommand {
	


	/* ---------- Attributes ---------- */

	private static final long serialVersionUID = 1L;
	
	I_AObject abstraction;
	


	/* ---------- Constructors ---------- */
	
	public CmdCreateDirectional(I_AObject abstraction) {
		this.abstraction = abstraction;
	}
	


	/* ---------- Methods ---------- */

	@Override
	public void execute(IEntity entity) {
		ICObject controller;
		controller = new CDirectionalLight(abstraction);
		controller.reload();
		controller.setEntity(entity);
		entity.getWorld().add(controller);

	}

}
