package object3D.controller;

import object3D.abstraction.AObject;
import object3D.abstraction.I_AObject;
import object3D.controller.interfaces.ICDirectionalLight;
import object3D.presentation.PDirectionalLight;
import command.create.CmdCreateDirectional;
import command.create.I_CreateCommand;

public class CDirectionalLight extends CObject implements ICDirectionalLight {
	
	/* ---------- Constructors ---------- */

	public CDirectionalLight() {
		abstraction = new AObject();
		presentation = new PDirectionalLight(this);
	}

	public CDirectionalLight(I_AObject abstraction) {
		this.abstraction = abstraction;
		presentation = new PDirectionalLight(this);
	}
	
	

	/* ---------- Methods ---------- */
	
	@Override
	public I_CreateCommand getCreateCommand() {
		return new CmdCreateDirectional(this.getAbstraction());
	}
	
	@Override
	public void reload() {
	}
	
	
}
