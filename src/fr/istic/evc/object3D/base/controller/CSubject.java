package fr.istic.evc.object3D.base.controller;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Color3f;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

import fr.istic.evc.Command.CmdCreateCSubject;
import fr.istic.evc.Command.I_CreateCommand;
import fr.istic.evc.object3D.base.abstraction.I_AObject;
import fr.istic.evc.pattern.Observer;
import fr.istic.evc.pattern.Subject;

public class CSubject extends CObject implements Subject {
	protected List<Observer> lo;


	public CSubject() {
		super();
		this.lo = new ArrayList<>();
	}

	public CSubject(I_AObject abstraction) {
		super(abstraction);
		this.lo = new ArrayList<>();
	}

	@Override
	public I_CreateCommand getCreateCommand() {
		// TODO Auto-generated method stub
		return new CmdCreateCSubject(abstraction);
	}
	
	
	@Override
	public void attach(Observer o) {
		lo.add(o);
	}

	@Override
	public void detach(Observer o) {
		lo.remove(o);
	}

	@Override
	public void myNotify() {
		for (Observer o:lo)
			o.update();
		
	}

	@Override
	public void updatePosition(Vector3d position) {
		super.updatePosition(position);
		myNotify();
	}

	@Override
	public void updateOrientation(Quat4d orientation) {
		super.updateOrientation(orientation);
		myNotify();
	}

	@Override
	public void updateAmbientColor(Color3f ambientColor) {
		super.updateAmbientColor(ambientColor);
		myNotify();
	}

	@Override
	public void updateDiffuseColor(Color3f diffuseColor) {
		super.updateDiffuseColor(diffuseColor);
		myNotify();
	}

}
