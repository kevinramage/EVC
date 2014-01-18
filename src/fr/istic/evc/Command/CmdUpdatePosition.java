package fr.istic.evc.Command;

import java.util.List;

import javax.vecmath.Vector3d;

import fr.istic.evc.object3D.base.controller.CObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;

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
	public void execute(ICWorld world) {
		
		// Get object
		ICObject obj = world.getObjectById(id);
		obj.updatePosition(position);
	}
}
