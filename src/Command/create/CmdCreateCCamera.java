package command.create;

import object3D.abstraction.I_AObject;
import object3D.controller.CCamera;
import object3D.controller.interfaces.ICWorld;
import project.IEntity;

public class CmdCreateCCamera implements I_CreateCommand {
	
	private static final long serialVersionUID = 1L;
	protected I_AObject abstraction;
	
	
	
	/* ---------- Constructors ---------- */
	
	public CmdCreateCCamera(I_AObject abstraction) {
		this.abstraction = abstraction;
	}
	


	/* ---------- Methods ---------- */
	@Override
	public void execute(ICWorld world, IEntity entity) {
		CCamera controller = new CCamera(abstraction);
		controller.setEntity(entity);
		controller.setManager(world.getCameraManager());
		controller.reload();
		world.add(controller);
	}
	
	

}
