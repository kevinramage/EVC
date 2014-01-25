package fr.istic.evc.object3D.base.controller;

import fr.istic.evc.Command.CmdCreateDirectional;
import fr.istic.evc.Command.I_CreateCommand;
import fr.istic.evc.object3D.base.abstraction.AObject;
import fr.istic.evc.object3D.base.abstraction.I_AObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICDirectionalLight;
import fr.istic.evc.object3D.base.presentation.PDirectionalLight;

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
