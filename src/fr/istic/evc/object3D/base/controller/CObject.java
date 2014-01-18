/**
 * Class which contains the controller of a 3D object
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 * 
 */

package fr.istic.evc.object3D.base.controller;

import java.util.List;

import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;

import fr.istic.evc.Command.CmdUpdateColor;
import fr.istic.evc.Command.CmdUpdateDiffuse;
import fr.istic.evc.Command.CmdUpdateOrientation;
import fr.istic.evc.Command.CmdUpdatePosition;
import fr.istic.evc.Command.CmdCreateCObject;
import fr.istic.evc.Command.I_Command;
import fr.istic.evc.Command.I_CreateCommand;
import fr.istic.evc.object3D.base.abstraction.AObject;
import fr.istic.evc.object3D.base.abstraction.I_AObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.presentation.PObject;
import fr.istic.evc.object3D.base.presentation.interfaces.IPObject;
import fr.istic.evc.project.Client;
import fr.istic.evc.project.IEntity;


public class CObject implements ICObject{

	/* ---------- Attributes ---------- */
	
	protected I_AObject abstraction;
	protected IPObject presentation;
	protected IEntity entity;
	


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
		presentation.setPosition(getPosition());
		presentation.setAmbientColor(getAmbientColor());
		presentation.setDiffuseColor(getDiffuseColor());
	}
	
	public static ICObject getObjectById(List<ICObject> objs, String id) {
		for ( ICObject obj : objs) {
			if (obj.getId().equals(id)) {
				return obj;
			}
		}
		return null;
	}
	
	public void select() {
		abstraction.setBackupColor(getAmbientColor());
		this.setAmbientColor(getSelectColor());
	}
	
	public void unselect() {
		this.setAmbientColor(getBackupColor());
	}


	/* ---------- Updaters ---------- */
	

	public void updatePosition(Vector3d position) {
		abstraction.setPosition(position);
		presentation.setPosition(position);		
	}
	
	public void updateOrientation(Quat4f orientation) {
		abstraction.setOrientation(orientation);
		presentation.setOrientation(orientation);
	}
	
	/**
	 * Update the ambient color of the object
	 * @param ambientColor a float vector3 which contains the ambient color r, g, b of the object
	 */
	public void updateAmbientColor(Color3f ambientColor) {
		abstraction.setAmbientColor(ambientColor);
		presentation.setAmbientColor(ambientColor);
	}
	
	/**
	 * Update the diffuse color of the object
	 * @param ambientColor a float vector3 which contains the ambient color r, g, b of the object
	 */
	@Override
	public void updateDiffuseColor(Color3f diffuseColor) {
		abstraction.setDiffuseColor(diffuseColor);
		presentation.setDiffuseColor(diffuseColor);
	}

	

	/* ---------- Setters ---------- */

	/**
	 * Set the 3D position of the object
	 * @param position a vector3 which contains the position x, y, z of the object
	 */
	public void setPosition(Vector3d position) {
		if ( !entity.isServer() ) {
			I_Command cmd = new CmdUpdatePosition(this.getId(), position);
			((Client)entity).changed(cmd);
		} else {
			updatePosition(position);
		}
	}
	
	/**
	 * Set the orientation of th
	 * e object
	 * @param orientation a quaternion which define the orientation of the object
	 */
	public void setOrientation(Quat4f orientation) {
		if(!entity.isServer()) {
			I_Command cmd = new CmdUpdateOrientation(this.getId(), orientation);
			((Client)entity).changed(cmd);
		}
		else  {
			updateOrientation(orientation);
		}
	}
	
	/**
	 * Set the geometry of the object
	 * @param geometry the geometry primitive name or the geometry url of the object
	 */
	public void setGeometry(String geometry) {
		abstraction.setGeometry(geometry);
		presentation.setGeometry(geometry);
	}

	/**
	 * Set the scale of the object
	 * @param scale a vector3 which contains the scale x, y, z of the object
	 */
	public void setScale(Vector3d scale) {

	}
	
	/**
	 * Define if the object if pickable or not
	 * @param b boolean which determine if object is pickable or not
	 */
	public void IsPickable(boolean b) {
		abstraction.IsPickable(b);
		presentation.IsPickable(b);
	}
	
	/**
	 * Set the ambient color of the object
	 * @param ambientColor a float vector3 which contains the ambient color r, g, b of the object
	 */
	public void setAmbientColor(Color3f ambientColor) {
		if ( !entity.isServer() ) {
			I_Command cmd = new CmdUpdateColor(this.getId(), ambientColor);
			((Client)entity).changed(cmd);
		} else {
			updateAmbientColor(ambientColor);
		}
	}

	/**
	 * Set the diffuse color of the object
	 * @param diffuseColor a float vector3 which contains the diffuse color r, g, b of the object
	 */
	public void setDiffuseColor(Color3f diffuseColor) {
		if ( !entity.isServer() ) {
			I_Command cmd = new CmdUpdateDiffuse(this.getId(), diffuseColor);
			((Client)entity).changed(cmd);
		} else {
			updateAmbientColor(diffuseColor);
		}
	}
	
	@Override
	public void setId(String id) {
		abstraction.setId(id);
	}

	public void setEntity(IEntity entity) {
		this.entity = entity;
	}
	


	/* ---------- Getters ---------- */
	
	
	/**
	 * Get the presentation layer of the object
	 * @return the presentation layer
	 */
	public IPObject getPresentation() {
		return presentation;
	}

	/**
	 * Get the geometry of the object
	 * @return the name of the primitve geometry or the url of the object
	 */
	public String getGeometry() {
		return abstraction.getGeometry();
	}

	@Override
	public String getId() {
		return abstraction.getId();
	}

	@Override
	public I_AObject getAbstraction() {
		return abstraction;
	}

	public Vector3d getPosition() {
		return abstraction.getPosition();
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

	@Override
	public Transform3D getTransform() {
		return presentation.getTransform();
	}



}
