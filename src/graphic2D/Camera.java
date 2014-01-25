package graphic2D;

import javax.media.j3d.Transform3D;

public class Camera {
	
	private Transform3D transform3D;
	
	public Camera() {
		transform3D = new Transform3D();
	}
	
	public Transform3D getTransform3D() {
		return transform3D;
	}
	public void setTransform3D(Transform3D value) {
		transform3D = value;
	}
}
