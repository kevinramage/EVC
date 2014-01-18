package fr.istic.evc.Command;

import fr.istic.evc.object3D.base.abstraction.I_AObject;
import fr.istic.evc.object3D.base.controller.CAmbientLight;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;
import fr.istic.evc.project.IEntity;

public class CmdCreateAmbient implements I_CreateCommand {

	/* ---------- Attributes ---------- */

	private static final long serialVersionUID = 1L;
	
	I_AObject abstraction;
	


	/* ---------- Constructors ---------- */

	public CmdCreateAmbient(I_AObject abstraction) {
		this.abstraction = abstraction;
	}
	


	/* ---------- Methods ---------- */
	
	@Override
	public void execute(ICWorld world, IEntity entity) {
		ICObject controller;
		controller = new CAmbientLight(abstraction);
		controller.reload();
		controller.setEntity(entity);
		world.add(controller);

	}

}
