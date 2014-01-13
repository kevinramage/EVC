package fr.istic.evc.graphic2D;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

class CameraManager {
	private Camera camera;
	private NavigationMode navigationMode;
	private TransformGroup transformGroup;
	
	public CameraManager(TransformGroup transformGroup) {
		this.transformGroup = transformGroup;
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
}
