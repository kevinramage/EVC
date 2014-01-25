package command.update;

import javax.vecmath.Color3f;

import object3D.controller.interfaces.ICObject;
import project.IEntity;
import command.I_Command;

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
	public void execute(IEntity entity) {
		
		// Get object
		ICObject obj = entity.getWorld().getObjectById(id);
		obj.updateDiffuseColor(color);
	}

}
