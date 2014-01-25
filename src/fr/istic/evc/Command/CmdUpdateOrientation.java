package fr.istic.evc.Command;

import javax.vecmath.Quat4d;

import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
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
		try {
			obj.updateOrientation(orientation);
		}
		catch(Exception e) {
			System.err.println("Object recherché "+id+" not found");
			//entity.showAllObjects();
		}
	}
}
