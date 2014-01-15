package fr.istic.evc.Command;

import java.util.List;

import javax.vecmath.Color3f;

import fr.istic.evc.object3D.base.controller.CObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;

public class CmdChangeColor implements Command {

	// ---------------------------------------------------------
	// 						Attributes
	// ---------------------------------------------------------
	private static final long serialVersionUID = 1L;
	protected String id;
	protected Color3f color;
	private boolean toPropagate;
	
	
	
	// ---------------------------------------------------------
	//						Constructor
	// ---------------------------------------------------------
	public CmdChangeColor(String id, Color3f color) {
		this.id = id;
		this.color = color;
		this.toPropagate = true;
	}
	
	
	// ---------------------------------------------------------
	//						Methods
	// ---------------------------------------------------------
	@Override
	public void execute(List<ICObject> objs) {
		
		// Get object
		System.out.println("CmdChangeColor.execute()");
		System.out.println("Etape 3");
		ICObject obj = CObject.getObjectById(objs, id);
		obj.updateAmbientColor(color);
		toPropagate = false;
	}


	public boolean isToPropagate() {
		return toPropagate;
	}
}
