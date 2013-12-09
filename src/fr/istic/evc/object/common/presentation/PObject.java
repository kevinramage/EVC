package fr.istic.evc.object.common.presentation;

import javax.media.j3d.TransformGroup;

import fr.istic.evc.object.common.controller.CObject;

public class PObject extends TransformGroup{

	protected CObject controller;
	
	public PObject(CObject controller) {
		this.controller = controller;
	}
	
	public void loadGeometry() {
	
	}
}
