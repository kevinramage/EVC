/**
 * Class which contains the controller of a 3D object
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 * 
 */

package object3D.controller;

import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

import object3D.abstraction.AObject;
import object3D.abstraction.I_AObject;
import object3D.controller.interfaces.ICObject;
import object3D.controller.interfaces.ICWorld;
import object3D.presentation.PObject;
import object3D.presentation.interfaces.IPObject;
import project.IEntity;

import command.CmdReferent;
import command.I_Command;
import command.create.CmdCreateCObject;
import command.create.I_CreateCommand;
import command.update.CmdUpdateColor;
import command.update.CmdUpdateDiffuse;
import command.update.CmdUpdateOrientation;
import command.update.CmdUpdatePosition;


public class CObject implements ICObject {

	/* ---------- Attributes ---------- */
	
	protected I_AObject abstraction;
	protected IPObject presentation;
	protected IEntity entity;
	protected boolean referent;
	


	
	
	
	/* ---------- Constructors ---------- */
	
	public CObject() {
		abstraction = new AObject();
		presentation = new PObject(this);
	}
	
	public CObject(I_AObject abstraction) {
		this.abstraction = abstraction;
		presentation = new PObject(this);
	}
	

	
	
	
	

	/* ---------- Methods ---------- */
	
	@Override
	public I_CreateCommand getCreateCommand() {
		return new CmdCreateCObject(this.getAbstraction());
	}
	
	@Override
	public void reload() {
		presentation.setGeometry(getGeometry());
		presentation.setOrientation(getOrientation());
		presentation.setPosition(getPosition());
		presentation.setAmbientColor(getAmbientColor());
		presentation.setDiffuseColor(getDiffuseColor());
	}
	
	public void select() {
		
		// Save the current ambient color
		abstraction.setBackupColor(getAmbientColor());
		
		// Change the ambient color
		setAmbientColor(getSelectColor());
	}
	
	public void unselect() {
		this.setAmbientColor(getBackupColor());
	}

	@Override
	public void init(IEntity entity, ICWorld world) {
	}


	
	
	/* ---------- Updaters ---------- */
	

	public void updatePosition(Vector3d position) {
		abstraction.setPosition(position);
		presentation.setPosition(position);		
	}
	
	public void updateOrientation(Quat4d orientation) {
		abstraction.setOrientation(orientation);
		presentation.setOrientation(orientation);
	}
	
	public void updateAmbientColor(Color3f ambientColor) {
		abstraction.setAmbientColor(ambientColor);
		presentation.setAmbientColor(ambientColor);
	}
	
	public void updateDiffuseColor(Color3f diffuseColor) {
		abstraction.setDiffuseColor(diffuseColor);
		presentation.setDiffuseColor(diffuseColor);
	}

	public void updateGeometry(String geometry) {
		abstraction.setGeometry(geometry);
		presentation.setGeometry(geometry);
	}
	
	public void updateScale(Vector3d scale) {
		System.err.println("Unimplemented method");
	}
	
	public void updatePickable(boolean b) {
		abstraction.setPickable(b);
		presentation.setPickable(b);
	}
	

	
	
	/* ---------- Setters ---------- */

	public void setPosition(Vector3d position) {
		
		// Create update command
		I_Command cmdUpdate = new CmdUpdatePosition(getId(), position, false);
		
		// Propagate command
		if (referent) {
			entity.broadCastUpdateCommand(cmdUpdate);
		} else {
			entity.broadCastUpdateCommand(new CmdReferent(getId(), entity.getId(), cmdUpdate));
		}
	}
	
	public void setOrientation(Quat4d orientation) {

		// Create update command
		I_Command cmdUpdate = new CmdUpdateOrientation(this.getId(), orientation);
		
		// Propagate command
		if (referent) {
			entity.broadCastUpdateCommand(cmdUpdate);
		} else {
			entity.broadCastUpdateCommand(new CmdReferent(getId(), entity.getId(), cmdUpdate));
		}
	}
	
	public void setGeometry(String geometry) {
		System.err.println("Unimplemented method");
	}
	
	public void setScale(Vector3d scale) {
		System.err.println("Unimplemented method");
	}

	public void setPickable(boolean b) {
		System.err.println("Unimplemented method");
	}
	
	public void setAmbientColor(Color3f ambientColor) {

		// Create update command
		I_Command cmdUpdate = new CmdUpdateColor(this.getId(), ambientColor);
		
		// Propagate command
		if (referent) {
			entity.broadCastUpdateCommand(cmdUpdate);
		} else {
			entity.broadCastUpdateCommand(new CmdReferent(getId(), entity.getId(), cmdUpdate));
		}
	}

	public void setDiffuseColor(Color3f diffuseColor) {
		
		// Create update command
		I_Command cmdUpdate = new CmdUpdateDiffuse(this.getId(), diffuseColor);
		
		// Propagate command
		if (referent) {
			entity.broadCastUpdateCommand(cmdUpdate);
		} else {
			entity.broadCastUpdateCommand(new CmdReferent(getId(), entity.getId(), cmdUpdate));
		}
	}
	
	public void setId(String id) {
		abstraction.setId(id);
	}
	
	public void setEntity(IEntity entity) {
		this.entity = entity;
	}
	
	public void setReferent(boolean b) {
		referent = b;
	}
	
	
	
	
	
	
	


	/* ---------- Getters ---------- */
	
	
	public IPObject getPresentation() {
		return presentation;
	}

	public String getGeometry() {
		return abstraction.getGeometry();
	}

	public String getId() {
		return abstraction.getId();
	}

	public I_AObject getAbstraction() {
		return abstraction;
	}

	public Vector3d getPosition() {
		return abstraction.getPosition();
	}
	
	public Quat4d getOrientation() {
		return abstraction.getOrientation();
	}

	public Color3f getAmbientColor() {
		return abstraction.getAmbientColor();
	}

	public Color3f getDiffuseColor() {
		return abstraction.getDiffuseColor();
	}
	
	public Color3f getSelectColor() {
		return abstraction.getSelectColor();
	}
	
	public Color3f getBackupColor() {
		return abstraction.getBackupColor();
	}

	public IEntity getEntity() {
		return entity;
	}
	
	public Transform3D getTransform() {
		return presentation.getTransform();
	}

	public boolean isPickable() {
		return abstraction.isPickable();
	}


}
