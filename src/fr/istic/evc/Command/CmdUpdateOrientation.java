package fr.istic.evc.Command;

import java.util.List;

import javax.vecmath.Quat4f;

import fr.istic.evc.object3D.base.controller.CObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;

public class CmdUpdateOrientation implements I_Command {
	


	/* ---------- Attributes ---------- */
	
	private static final long serialVersionUID = 1L;
	protected String id;
	protected Quat4f orientation;
	


	/* ---------- Constructors ---------- */
	
	public CmdUpdateOrientation(String id, Quat4f orientation) {
		this.id = id;
		this.orientation = orientation;
	}
	


	/* ---------- Methods ---------- */
	
	@Override
	public void execute(ICWorld world) {
		
		// Get object
		ICObject obj = world.getObjectById(id);
		obj.updateOrientation(orientation);
	}
}
