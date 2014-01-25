package fr.istic.evc.Command;

import java.util.List;

import javax.vecmath.Quat4d;
import javax.vecmath.Quat4f;

import fr.istic.evc.object3D.base.controller.CObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;
import fr.istic.evc.project.IEntity;

public class CmdUpdateOrientation implements I_Command {
	


	/* ---------- Attributes ---------- */
	
	private static final long serialVersionUID = 1L;
	protected String id;
	protected Quat4d orientation;
	


	/* ---------- Constructors ---------- */
	
	public CmdUpdateOrientation(String id, Quat4d orientation2) {
		this.id = id;
		this.orientation = orientation2;
	}
	


	/* ---------- Methods ---------- */

	@Override
	public void execute(IEntity entity) {
		
		// Get object
		ICObject obj = entity.getWorld().getObjectById(id);
		obj.updateOrientation(orientation);
	}
}
