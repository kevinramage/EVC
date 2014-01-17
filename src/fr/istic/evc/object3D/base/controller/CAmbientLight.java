package fr.istic.evc.object3D.base.controller;

import fr.istic.evc.Command.CmdCreateAmbient;
import fr.istic.evc.Command.I_CreateCommand;
import fr.istic.evc.object3D.base.abstraction.AObject;
import fr.istic.evc.object3D.base.abstraction.I_AObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICAmbientLight;
import fr.istic.evc.object3D.base.presentation.PAmbientLight;

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
	public I_CreateCommand getCreateCommand() {
		return new CmdCreateAmbient(this.getAbstraction());
	}
	
	@Override
	public void reload() {
		presentation.setGeometry(getGeometry());
		presentation.setPosition(getPosition());
		presentation.setAmbientColor(getAmbientColor());
	}
}
