package fr.istic.evc.object3D.base.presentation;

import java.awt.Color;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;

import fr.istic.evc.object3D.base.controller.CElasticObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.presentation.interfaces.IPObject;

public class PElasticObject extends TransformGroup implements IPObject {
	
	protected QuadArray form;
	protected Point3d[] tabPoints = new Point3d[8];
	protected CElasticObject controller; 
	
	public PElasticObject (CElasticObject controller, Vector3d v1, Vector3d v2) {
		this.controller = controller;
		
		setCapability(ALLOW_CHILDREN_WRITE);
		setCapability(ALLOW_CHILDREN_EXTEND);
		
		BranchGroup branchGroup = new BranchGroup();
		branchGroup.setCapability(BranchGroup.ALLOW_DETACH);
		branchGroup.addChild(buildForm(v1, v2));
		addChild(branchGroup);

	}
	
	private Shape3D buildForm(Vector3d v1, Vector3d v2) {
		form = new QuadArray(24, QuadArray.COORDINATES | QuadArray.COLOR_3);
		tabPoints[0] = new Point3d(v1.x, v1.y + 0.5, v1.z + 0.5);
		tabPoints[1] = new Point3d(v1.x, v1.y + 0.5, v1.z - 0.5);
		tabPoints[2] = new Point3d(v1.x, v1.y - 0.5, v1.z - 0.5);
		tabPoints[3] = new Point3d(v1.x, v1.y - 0.5, v1.z + 0.5);
		
		tabPoints[4] = new Point3d(v2.x, v2.y + 0.5, v2.z + 0.5);
		tabPoints[5] = new Point3d(v2.x, v2.y + 0.5, v2.z - 0.5);
		tabPoints[6] = new Point3d(v2.x, v2.y - 0.5, v2.z - 0.5);
		tabPoints[7] = new Point3d(v2.x, v2.y - 0.5, v2.z + 0.5);
		
		Point3d[] faces = {
			tabPoints[0], tabPoints[3], tabPoints[2], tabPoints[1],
			tabPoints[4], tabPoints[5], tabPoints[6], tabPoints[7],
			tabPoints[3], tabPoints[7], tabPoints[6], tabPoints[2],
			tabPoints[0], tabPoints[4], tabPoints[7], tabPoints[3],
			tabPoints[0], tabPoints[1], tabPoints[5], tabPoints[4],
			tabPoints[1], tabPoints[2], tabPoints[6], tabPoints[5],
		};
		
		Color3f[] colors = {
			new Color3f(Color.red), new Color3f(Color.red), new Color3f(Color.red), new Color3f(Color.red),
			new Color3f(Color.red), new Color3f(Color.red), new Color3f(Color.red), new Color3f(Color.red),
			new Color3f(Color.red), new Color3f(Color.red), new Color3f(Color.red), new Color3f(Color.red),
			new Color3f(Color.red), new Color3f(Color.red), new Color3f(Color.red), new Color3f(Color.red),
			new Color3f(Color.red), new Color3f(Color.red), new Color3f(Color.red), new Color3f(Color.red),
			new Color3f(Color.red), new Color3f(Color.red), new Color3f(Color.red), new Color3f(Color.red)
		};
		form.setCoordinates(0, faces);
		form.setColors(0, colors);
		return new Shape3D(form);
	}
	



	public void update(Vector3d v1, Vector3d v2) {
		Shape3D shape = buildForm(v1, v2);
		BranchGroup branchGroup = new BranchGroup();
		branchGroup.addChild(shape);
		branchGroup.setCapability(BranchGroup.ALLOW_DETACH);
		removeAllChildren();
		addChild(branchGroup);
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
		return controller;
	}

	@Override
	public Transform3D getTransform() {
		// TODO Auto-generated method stub
		return null;
	}

}
