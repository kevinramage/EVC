package object3D.presentation;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

import object3D.controller.interfaces.ICObject;
import object3D.presentation.interfaces.IPAmbientLight;

public class PAmbientLight extends TransformGroup implements IPAmbientLight{

	protected AmbientLight ambientLight;
	protected ICObject controller;
	
	public PAmbientLight(ICObject controller) {
		this.controller = controller;
		ambientLight = new AmbientLight();
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
		ambientLight.setInfluencingBounds(bounds);
		addChild(ambientLight);
	}

	@Override
	public void setPosition(Vector3d position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOrientation(Quat4d orientation) {
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
	public void setPickable(boolean b) {
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
	
	@Override
	public Transform3D getTransform() {
		Transform3D t = new Transform3D();
		super.getTransform(t);
		return t;
	}
}
