package command.create;

import object3D.abstraction.I_AObject;
import object3D.controller.CObject;
import object3D.controller.interfaces.ICObject;
import project.IEntity;



public class CmdCreateCObject implements I_CreateCommand {

	/* ---------- Attributes ---------- */
	
	private static final long serialVersionUID = 1L;
	
	I_AObject abstraction;
	


	/* ---------- Constructors ---------- */
	
	public CmdCreateCObject(I_AObject abstraction) {
		this.abstraction = abstraction;
	}
	


	/* ---------- Methods ---------- */

	@Override
	public void execute(IEntity entity) {
		ICObject controller;
		controller = new CObject(abstraction);
		controller.reload();
		controller.setEntity(entity);
		entity.getWorld().add(controller);
	}
}
