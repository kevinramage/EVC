package fr.istic.evc.Command;

import javax.vecmath.Vector3d;

import fr.istic.evc.object3D.base.controller.CSubject;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;
import fr.istic.evc.project.IEntity;

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
		obj.updatePosition(position);
		if (bindWithCamera && Integer.parseInt(obj.getId().split("-")[0]) == entity.getId())
			entity.getWorld().getCameraManager().refresh((CSubject) obj);
	}
}
