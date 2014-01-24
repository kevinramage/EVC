package fr.istic.evc.object3D.base.controller;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

import fr.istic.evc.Command.CmdCreateCCamera;
import fr.istic.evc.Command.CmdUpdatePosition;
import fr.istic.evc.Command.I_Command;
import fr.istic.evc.Command.I_CreateCommand;
import fr.istic.evc.graphic2D.CameraManager;
import fr.istic.evc.object3D.base.abstraction.AObject;
import fr.istic.evc.object3D.base.abstraction.I_AObject;
import fr.istic.evc.object3D.base.presentation.PObject;
import fr.istic.evc.pattern.Observer;
import fr.istic.evc.project.Client;

public class CCamera extends CSubject implements Observer{
	
	protected CameraManager manager;
	
	/* ---------- Constructors ---------- */

	public CCamera(CameraManager manager) {
		abstraction = new AObject();
		presentation = new PObject(this);
		this.manager = manager;
		manager.attach(this);
		init();
	}

	public CCamera(I_AObject abstraction) {
		this.abstraction = abstraction;
		presentation = new PObject(this);
		init();
	}
	
	private void init() {
		
		// Calculate orientation
		Transform3D t = new Transform3D();
		t.setEuler(new Vector3d(Math.PI / 2, 0, 0));
		Quat4d orientation = new Quat4d();
		t.get(orientation);
		
		// Init param
		setGeometry("cone");
		//updateDiffuseColor(new Color3f(1.0f, 0.0f, 0.0f));
		//updateAmbientColor(new Color3f(1.0f, 0.0f, 0.0f));
		updateOrientation(orientation);
	}
	
	private Transform3D getBasedOrientation()  {
		
		Transform3D t = new Transform3D();
		t.setEuler(new Vector3d(Math.PI / 2, 0, 0));
		Quat4d orientation = new Quat4d();
		t.get(orientation);
		return t;
	}
	
	

	/* ---------- Methods ---------- */
	
	@Override
	public I_CreateCommand getCreateCommand() {
		return new CmdCreateCCamera(this.getAbstraction());
	}

	@Override
	public void update() {
		
		//  Transform camera
		TransformGroup tg = manager.getTransform();
		Transform3D t3D = new Transform3D();
		tg.getTransform(t3D);
		
		// Transform based
		Transform3D tOld = getBasedOrientation();
		tOld.setTranslation(new Vector3d(0,0,5));
		//tOld.invert();
		
		// Mul
		t3D.mul(tOld);
		
		// Get result
		Vector3d position = new Vector3d();
		Quat4d orientation = new Quat4d();
		t3D.get(position);
		t3D.get(orientation);
		
		// Set the result
		setPosition(position);
		setOrientation(orientation);
	}
	
	@Override
	public void setPosition(Vector3d position) {
		if ( !entity.isServer() ) {
			I_Command cmd = new CmdUpdatePosition(this.getId(), position, Integer.parseInt(this.getId().split("-")[0].trim()) != entity.getId());
			((Client)entity).changed(cmd);
		} else {
			updatePosition(position);
			myNotify();
		}
	}
	
//	@Override
//	public void setPosition(Vector3d position) {
//		super.setPosition(position);
//		System.out.println("CCamera.setPosition()");
//	}
	
}
