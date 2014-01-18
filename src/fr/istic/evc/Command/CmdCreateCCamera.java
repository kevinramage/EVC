package fr.istic.evc.Command;

import fr.istic.evc.object3D.base.abstraction.I_AObject;
import fr.istic.evc.object3D.base.controller.CCamera;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;
import fr.istic.evc.project.IEntity;

public class CmdCreateCCamera implements I_CreateCommand {
	
	private static final long serialVersionUID = 1L;
	
	I_AObject abstraction;
	
	/* ---------- Constructors ---------- */
	
	public CmdCreateCCamera(I_AObject abstraction) {
		this.abstraction = abstraction;
	}
	


	/* ---------- Methods ---------- */
	@Override
	public void execute(ICWorld world, IEntity entity) {
		ICObject controller;
		controller = new CCamera(abstraction);
		controller.reload();
		controller.setEntity(entity);
		world.add(controller);
	}

}
