package command.update;

import javax.vecmath.Vector3d;

import object3D.controller.CSubject;
import object3D.controller.interfaces.ICObject;
import project.IEntity;
import command.I_Command;

public class CmdUpdatePosition implements I_Command {
	


	/* ---------- Attributes ---------- */
	
	private static final long serialVersionUID = 1L;
	protected String id;
	protected Vector3d position;
	public boolean bindWithCamera = false;
	


	/* ---------- Constructors ---------- */
	
	public CmdUpdatePosition(String id, Vector3d position, boolean bindWithCamera) {
		this.id = id;
		this.position = position;
		this.bindWithCamera = bindWithCamera;
	}
	


	/* ---------- Methods ---------- */

	@Override
	public void execute(IEntity entity) {
		
		// Get object
		ICObject obj = entity.getWorld().getObjectById(id);
		try {
			obj.updatePosition(position);
			if (bindWithCamera && Integer.parseInt(obj.getId().split("-")[0]) == entity.getId())
				entity.getWorld().getCameraManager().refresh((CSubject) obj);
		}
		catch(Exception e) {
			System.err.println("Object recherché "+id+" not found");
			entity.showAllObjects();
		}
		
	}
}
