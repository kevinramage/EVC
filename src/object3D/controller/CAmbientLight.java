package object3D.controller;

import object3D.abstraction.AObject;
import object3D.abstraction.I_AObject;
import object3D.controller.interfaces.ICAmbientLight;
import object3D.presentation.PAmbientLight;

import command.I_Command;
import command.create.CmdCreateAmbient;

public class CAmbientLight extends CObject implements ICAmbientLight {
	
	/* ---------- Constructors ---------- */

	public CAmbientLight() {
		abstraction = new AObject();
		presentation = new PAmbientLight(this);
	}

	public CAmbientLight(I_AObject abstraction) {
		this.abstraction = abstraction;
		presentation = new PAmbientLight(this);
	}
	


	/* ---------- Methods ---------- */
	
	@Override
	public I_Command getCreateCommand() {
		return new CmdCreateAmbient(this.getAbstraction());
	}
	
	@Override
	public void reload() {
		presentation.setGeometry(getGeometry());
		presentation.setPosition(getPosition());
		presentation.setAmbientColor(getAmbientColor());
	}
}
