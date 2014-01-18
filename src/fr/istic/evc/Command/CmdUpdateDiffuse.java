package fr.istic.evc.Command;

import java.util.List;

import javax.vecmath.Color3f;

import fr.istic.evc.object3D.base.controller.CObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;

public class CmdUpdateDiffuse implements I_Command {
	


	/* ---------- Attributs ---------- */
	
	private static final long serialVersionUID = 1L;
	protected String id;
	protected Color3f color;
	
	
	
	/* ---------- Constructors ---------- */
	
	public CmdUpdateDiffuse(String id, Color3f color) {
		this.id = id;
		this.color = color;
	}
	
	

	/* ---------- Methods ---------- */
	
	@Override
	public void execute(List<ICObject> objs) {
		
		// Get object
		ICObject obj = CObject.getObjectById(objs, id);
		obj.updateDiffuseColor(color);
	}

}
