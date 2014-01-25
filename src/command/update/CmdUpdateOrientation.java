package command.update;

import javax.vecmath.Quat4d;
import object3D.controller.interfaces.ICObject;
import project.IEntity;
import command.I_Command;



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
			System.err.println("Object recherche "+id+" not found");
		}
	}
}
