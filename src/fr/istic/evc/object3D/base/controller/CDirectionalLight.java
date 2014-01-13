package fr.istic.evc.object3D.base.controller;

import fr.istic.evc.object3D.base.abstraction.AObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICDirectionalLight;
import fr.istic.evc.object3D.base.presentation.PDirectionalLight;

public class CDirectionalLight extends CObject implements ICDirectionalLight {

	public CDirectionalLight() {
		abstraction = new AObject();
		presentation = new PDirectionalLight();
	}
	
}
