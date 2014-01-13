package fr.istic.evc.Command;

import java.util.List;

import javax.vecmath.Color3f;

import fr.istic.evc.object3D.base.controller.CObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;

public class CmdChangeColor implements Command {

	// ---------------------------------------------------------
	// 						Attributes
	// ---------------------------------------------------------
	protected String id;
	protected Color3f color;
	
	
	
	// ---------------------------------------------------------
	//						Constructor
	// ---------------------------------------------------------
	public CmdChangeColor(String id, Color3f color) {
		this.id = id;
		this.color = color;
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
		obj.setAmbientColor(color);
		
	}
	

}
