package fr.istic.evc.graphic2D;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import fr.istic.evc.object3D.base.controller.interfaces.ICObject;

public class CameraManager {
	private Camera camera;
	private NavigationMode navigationMode;
	private TransformGroup transformGroup;
	
	public CameraManager(TransformGroup transformGroup) {
		this.transformGroup = transformGroup;
		this.navigationMode = new ExamineMode();
	}
	
	public void changeCamera(Camera camera) {
		this.camera = camera;
		transformGroup.setTransform(camera.getTransform3D());
	}
	public void changeNavigationMode(NavigationMode navigationMode) {
		this.navigationMode = navigationMode;
	}
	public void translateX(int x) {
		navigationMode.translateX(transformGroup, x);
		Transform3D transform3D = new Transform3D();
		transformGroup.getTransform(transform3D);
		camera.setTransform3D(transform3D);
	}
	public void translateY(int y) {
		navigationMode.translateY(transformGroup, y);
		Transform3D transform3D = new Transform3D();
		transformGroup.getTransform(transform3D);
		camera.setTransform3D(transform3D);
	}
	public void translateZ(int z) {
		navigationMode.translateZ(transformGroup, z);
		Transform3D transform3D = new Transform3D();
		transformGroup.getTransform(transform3D);
		camera.setTransform3D(transform3D);
	}
	
	public void rotateWorld(double dh, double dp, double dr) {
		
		// World transform
		Transform3D transformWorld = new Transform3D(); 
		transformGroup.getTransform(transformWorld);
		
		// Rotate
		Transform3D transformRotation = new Transform3D();
		transformRotation.setEuler(new Vector3d(dh, dp, dr));
		
		// Result
		Transform3D result = new Transform3D();
		result.mul(transformWorld, transformRotation);
		
		// Set world rotation
		transformGroup.setTransform(result);
	}



	public void translateObject(ICObject object, double dx, double dy, double dz) {

		/*
		// Object transform
		Transform3D transformObject = object.getTransform();
		
		// Translation
		Transform3D transformTranslation = new Transform3D();
		transformTranslation.setTranslation(new Vector3d(dx, dy, dz));

		// World orientation
		Transform3D transformWorldOrientation = new Transform3D();
		transformGroup.getTransform(transformWorldOrientation);

		// World orientation inverse
		Transform3D transformWorldOrientationInv = new Transform3D();
		transformWorldOrientation.invert(transformWorldOrientationInv);
		
		// Result
		Transform3D result = new Transform3D();
		result.mul(transformWorldOrientation, transformTranslation);
		result.mul(transformWorldOrientationInv);
		result.mul(transformObject);

		// Set transform
		Vector3d position = new Vector3d();
		Quat4f orientation = new Quat4f();
		result.get(position);
		result.get(orientation);
		object.setPosition(position);
		object.setOrientation(orientation);
		*/
		
		Transform3D transformView = new Transform3D();
		transformGroup.getTransform(transformView);
		transformView.invert();
		
		Transform3D transformObj = object.getTransform();
		
		Transform3D transformDelta = new Transform3D();
		transformDelta.setTranslation(new Vector3d(dx, dy, dz));
		
		
		Transform3D result = new Transform3D();
		result.mul(transformDelta, transformObj);
		//result.mul();
		
		
		Vector3d position = new Vector3d();
		result.get(position);
		object.setPosition(position);
	}
}
