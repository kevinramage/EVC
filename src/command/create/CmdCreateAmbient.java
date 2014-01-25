package command.create;

import object3D.abstraction.I_AObject;
import object3D.controller.CAmbientLight;
import object3D.controller.interfaces.ICObject;
import object3D.controller.interfaces.ICWorld;
import project.IEntity;

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
