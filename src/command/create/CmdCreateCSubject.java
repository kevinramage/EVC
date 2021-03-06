
package command.create;

import command.I_Command;

import object3D.abstraction.I_AObject;
import object3D.controller.CSubject;
import object3D.controller.interfaces.ICObject;
import project.IEntity;


public class CmdCreateCSubject implements I_Command {

	/* ---------- Attributes ---------- */
	
	private static final long serialVersionUID = 1L;
	
	I_AObject abstraction;
	


	/* ---------- Constructors ---------- */
	
	public CmdCreateCSubject(I_AObject abstraction) {
		this.abstraction = abstraction;
	}
	


	/* ---------- Methods ---------- */

	@Override
	public void execute(IEntity entity) {
		ICObject controller;
		controller = new CSubject(abstraction);
		controller.reload();
		controller.setEntity(entity);
		entity.getWorld().add(controller);
	}
}
