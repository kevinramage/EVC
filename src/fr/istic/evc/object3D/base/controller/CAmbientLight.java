package fr.istic.evc.object3D.base.controller;

import fr.istic.evc.object3D.base.controller.interfaces.ICAmbientLight;
import fr.istic.evc.object3D.base.presentation.PAmbientLight;

public class CAmbientLight extends CObject implements ICAmbientLight {

	public CAmbientLight() {
		super();
		//abstraction = new AObject();
		presentation = new PAmbientLight();
	}
	
}
