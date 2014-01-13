package fr.istic.evc.test;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

public class WalkMML implements MouseMotionListener{

	private TransformGroup transformGroup;
	private int x, y;
	
	public WalkMML(TransformGroup transformGroup) {
		this.transformGroup = transformGroup;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		// Calculate delta
		int dx = e.getX() - x;
		int dy = e.getY() - y;
		
		// Modify orientation
		Transform3D oldTransform3D = new Transform3D();
		transformGroup.getTransform(oldTransform3D);
		Transform3D transform3d = new Transform3D();
		transform3d.setEuler(new Vector3d(Math.PI / 500 * dy, Math.PI / 500 * dx, 0));
		Transform3D newTransform3d = new Transform3D();
		newTransform3d.mul(oldTransform3D, transform3d);
		transformGroup.setTransform(newTransform3d);

		// Save the last position
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		// Save the last position
		x = e.getX();
		y = e.getY();
	}

}
