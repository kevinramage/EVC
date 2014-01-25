package graphic2D;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

public class WalkMode implements NavigationMode {

	@Override
	public void translateX(TransformGroup transformGroup, int x) {

		// Calculate new transform
		Transform3D oldTransform3D = new Transform3D();
		transformGroup.getTransform(oldTransform3D);
		Transform3D transform3D = new Transform3D();
		transform3D.setTranslation(new Vector3d(x,0,0));
		Transform3D newTransform3D = new Transform3D();
		newTransform3D.mul(oldTransform3D, transform3D);
		transformGroup.setTransform(newTransform3D);
	}

	@Override
	public void translateY(TransformGroup transformGroup, int y) {

		// Calculate new transform
		Transform3D oldTransform3D = new Transform3D();
		transformGroup.getTransform(oldTransform3D);
		Transform3D transform3D = new Transform3D();
		transform3D.setTranslation(new Vector3d(0,y,0));
		Transform3D newTransform3D = new Transform3D();
		newTransform3D.mul(oldTransform3D, transform3D);
		transformGroup.setTransform(newTransform3D);
	}

	@Override
	public void translateZ(TransformGroup transformGroup, int z) {
		
		// Calculate new transform
		Transform3D oldTransform3D = new Transform3D();
		transformGroup.getTransform(oldTransform3D);
		Transform3D transform3D = new Transform3D();
		transform3D.setTranslation(new Vector3d(0,0,z));
		Transform3D newTransform3D = new Transform3D();
		newTransform3D.mul(oldTransform3D, transform3D);
		transformGroup.setTransform(newTransform3D);
	}	
}