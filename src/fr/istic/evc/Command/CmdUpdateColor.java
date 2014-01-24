package fr.istic.evc.Command;

import javax.vecmath.Color3f;

import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;
import fr.istic.evc.project.IEntity;

public class CmdUpdateColor implements I_Command {
	


	/* ---------- Attributs ---------- */
	
	private static final long serialVersionUID = 1L;
	protected String id;
	protected Color3f color;
	


	/* ---------- Constructors ---------- */
	
	public CmdUpdateColor(String id, Color3f color) {
		this.id = id;
		this.color = color;
	}
	


	/* ---------- Methods ---------- */
	
	@Override
	public void execute(IEntity entity) {
		System.out.println("CmdUpdateColor.execute()");
		// Get object
		ICObject obj = entity.getWorld().getObjectById(id);
		obj.updateAmbientColor(color);
	}
}
