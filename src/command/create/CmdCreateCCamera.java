package command.create;

import command.I_Command;

import object3D.abstraction.I_AObject;
import object3D.controller.CCamera;
import project.IEntity;


public class CmdCreateCCamera implements I_Command {
	
	private static final long serialVersionUID = 1L;
	protected I_AObject abstraction;
	
	
	
	/* ---------- Constructors ---------- */
	
	public CmdCreateCCamera(I_AObject abstraction) {
		this.abstraction = abstraction;
	}
	


	/* ---------- Methods ---------- */
	public void execute(IEntity entity) {
		CCamera controller = new CCamera(abstraction);
		controller.setManager(entity.getWorld().getCameraManager());
		controller.reload();
		controller.setEntity(entity);
		entity.getWorld().add(controller);
	}
	
	

}
