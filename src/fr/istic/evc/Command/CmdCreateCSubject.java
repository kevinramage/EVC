package fr.istic.evc.Command;

import fr.istic.evc.object3D.base.abstraction.I_AObject;
import fr.istic.evc.object3D.base.controller.CObject;
import fr.istic.evc.object3D.base.controller.CSubject;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;
import fr.istic.evc.project.IEntity;

public class CmdCreateCSubject implements I_CreateCommand {

	/* ---------- Attributes ---------- */
	
	private static final long serialVersionUID = 1L;
	
	I_AObject abstraction;
	


	/* ---------- Constructors ---------- */
	
	public CmdCreateCSubject(I_AObject abstraction) {
		this.abstraction = abstraction;
	}
	


	/* ---------- Methods ---------- */

	@Override
	public void execute(ICWorld world, IEntity entity) {
		ICObject controller;
		controller = new CSubject(abstraction);
		controller.reload();
		controller.setEntity(entity);
		world.add(controller);
	}
}
