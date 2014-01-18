package fr.istic.evc.Command;

import java.util.List;

import javax.vecmath.Vector3d;

import fr.istic.evc.object3D.base.controller.CObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;

public class CmdUpdatePosition implements I_Command {
	


	/* ---------- Attributes ---------- */
	
	private static final long serialVersionUID = 1L;
	protected String id;
	protected Vector3d position;
	


	/* ---------- Constructors ---------- */
	
	public CmdUpdatePosition(String id, Vector3d position) {
		this.id = id;
		this.position = position;
	}
	


	/* ---------- Methods ---------- */
	
	@Override
	public void execute(List<ICObject> objs) {
		
		// Get object
		ICObject obj = CObject.getObjectById(objs, id);
		obj.updatePosition(position);
	}
}
