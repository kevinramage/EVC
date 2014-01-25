package object3D.controller;

import graphic2D.CameraManager;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

import object3D.abstraction.AObject;
import object3D.abstraction.I_AObject;
import object3D.presentation.PObject;
import pattern.Observer;

import command.CmdReferent;
import command.I_Command;
import command.create.CmdCreateCCamera;
import command.create.I_CreateCommand;
import command.update.CmdUpdatePosition;

public class CCamera extends CSubject implements Observer{
	
	/* ---------- Attributes ---------- */
	
	protected CameraManager manager;
	
	
	
	
	
	
	/* ---------- Constructors ---------- */

	public CCamera() {
		abstraction = new AObject();
		presentation = new PObject(this);
	}

	public CCamera(I_AObject abstraction) {
		this.abstraction = abstraction;
		presentation = new PObject(this);
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
		
		// Detect if is camera owner
		boolean cameraOwner = Integer.parseInt(this.getId().split("-")[0].trim()) != entity.getId();
		
		// Create update command
		I_Command cmdUpdate = new CmdUpdatePosition(getId(), position, cameraOwner);
		
		// Propagate command
		if (referent) {
			entity.broadCastUpdateCommand(cmdUpdate);
		} else {
			entity.broadCastUpdateCommand(new CmdReferent(getId(), entity.getId(), cmdUpdate));
		}
	}

	public void setManager(CameraManager manager) {
		this.manager = manager;
		manager.attach(this);
		this.attach(manager);
	}
	
	
	private Transform3D getBasedOrientation()  {
		
		Transform3D t = new Transform3D();
		t.setEuler(new Vector3d(Math.PI / 2, 0, 0));
		Quat4d orientation = new Quat4d();
		t.get(orientation);
		return t;
	}
}
