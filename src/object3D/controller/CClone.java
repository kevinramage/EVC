package object3D.controller;

import javax.vecmath.Color3f;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

import object3D.abstraction.I_AObject;
import pattern.Observer;
import pattern.Subject;
import project.Client;

import command.I_Command;
import command.clone.CmdCreateClone;
import command.clone.CmdDeleteClone;


public class CClone extends CObject implements Subject {
	
	private String idClonable;
	private int idClient;
	

	public CClone(int idClient, String idClonable, Color3f color, Quat4d orientation, Vector3d position) {
		super();
		this.idClient = idClient;
		this.idClonable = idClonable;
		abstraction.setAmbientColor(new Color3f(1f, 0f, 0f));
		abstraction.setDiffuseColor(new Color3f(1f, 0f, 0f));
		abstraction.setOrientation(orientation);
		abstraction.setPosition(position);
		abstraction.setPickable(true);
		abstraction.setTransparency(0.8f);
	}

	
	
	public CClone(I_AObject abstraction, int idClient, String idClonable) {
		super(abstraction);
		this.idClient = idClient;
		this.idClonable = idClonable;
	}

	
	@Override
	public void setSelected(boolean selected) {
		if (!selected && !entity.isServer()) {
			CmdDeleteClone cmd = new CmdDeleteClone();
			cmd.setId(this.getId());
			((Client)entity).removeClone(cmd);
		}
	}


	@Override
	public I_Command getCreateCommand() {
		CmdCreateClone cmd = new CmdCreateClone(getAbstraction());
		cmd.setIdClient(idClient);
		cmd.setIdClonable(idClonable);
		return cmd;
	}



	@Override
	public void attach(Observer o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void detach(Observer o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void myNotify() {
		// TODO Auto-generated method stub

	}
	
	public String getIdClonable() {
		return this.idClonable;
	}

	public int getIdClient() {
		return this.idClient;
	}
}
