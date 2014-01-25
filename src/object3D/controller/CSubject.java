package object3D.controller;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Color3f;
import javax.vecmath.Quat4d;

import object3D.abstraction.I_AObject;
import pattern.Observer;
import pattern.Subject;

import command.I_Command;
import command.create.CmdCreateCSubject;



public class CSubject extends CObject implements Subject {
	
	/* ---------- Attributes ---------- */
	protected List<Observer> lo;


	
	
	
	/* ---------- Constructors ---------- */
	public CSubject() {
		super();
		this.lo = new ArrayList<>();
	}

	public CSubject(I_AObject abstraction) {
		super(abstraction);
		this.lo = new ArrayList<>();
	}

	
	
	/* ---------- Methods ---------- */
	@Override
	public I_Command getCreateCommand() {
		return new CmdCreateCSubject(getAbstraction());
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
		for (Observer o:lo) {
			o.update();
		}
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
