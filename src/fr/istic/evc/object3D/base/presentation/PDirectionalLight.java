package fr.istic.evc.object3D.base.presentation;

import javax.media.j3d.DirectionalLight;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;

import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.presentation.interfaces.IPDirectionalLight;

public class PDirectionalLight extends TransformGroup implements IPDirectionalLight {

	protected DirectionalLight directionLight;
	protected ICObject controller;
	
	public PDirectionalLight(ICObject controller) {
		this.controller = controller;
		directionLight = new DirectionalLight();
		addChild(directionLight);
	}
	
	@Override
	public void setPosition(Vector3d position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOrientation(Quat4f orientation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setGeometry(String geometry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setScale(Vector3d scale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void IsPickable(boolean b) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAmbientColor(Color3f ambientColor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDiffuseColor(Color3f diffuseColor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ICObject getController() {
		return this.controller;
	}

}
