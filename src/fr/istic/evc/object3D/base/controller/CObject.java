/**
 * Class which contains the controller of a 3D object
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 * 
 */

package fr.istic.evc.object3D.base.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.vecmath.Color3f;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

import fr.istic.evc.Command.CmdChangeColor;
import fr.istic.evc.Command.CmdChangePosition;
import fr.istic.evc.Command.Command;
import fr.istic.evc.object3D.base.abstraction.AObject;
import fr.istic.evc.object3D.base.abstraction.IAObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.presentation.PObject;
import fr.istic.evc.object3D.base.presentation.interfaces.IPObject;
import fr.istic.evc.project.Client;
import fr.istic.evc.project.IEntity;


public class CObject implements ICObject{
	
	// ---------------------------------------------------------
	// 						Attributes
	// ---------------------------------------------------------	
	protected IAObject abstraction;
	protected IPObject presentation;
	protected IEntity entity;
	protected Map<String, Object> propertiesChanged;
	
	
	// ---------------------------------------------------------
	//						Methods
	// ---------------------------------------------------------
	
	public CObject() {
		abstraction = new AObject();
		presentation = new PObject(this);
		propertiesChanged = new HashMap<String, Object>();
	}
	
	
	
	
	// ---------------------------------------------------------
	//						Setters
	// ---------------------------------------------------------

	
	/**
	 * Set the 3D position of the object
	 * @param position a vector3 which contains the position x, y, z of the object
	 */
	public void setPosition(Vector3d position) {
		if ( !entity.isServer() ) {
			Command cmd = new CmdChangePosition(this.getId(), position);
			((Client)entity).changed(cmd);
		} else {
			updatePosition(position);
		}
	}
	public void updatePosition(Vector3d position) {
		abstraction.setPosition(position);
		presentation.setPosition(position);		
	}
	
	/**
	 * Set the orientation of the object
	 * @param orientation a quaternion which define the orientation of the object
	 */
	public void setOrientation(Quat4d orientation) {

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

		System.out.println("CObject.setClient()");
		System.out.println("Creation client "+ entity);
		if ( !entity.isServer() ) {
			System.out.println("CObject.setAmbientColor()");
			System.out.println("Etape 1\n");
			Command cmd = new CmdChangeColor(this.getId(), ambientColor);
			((Client)entity).changed(cmd);
		} else {
			updateAmbientColor(ambientColor);
		}
	}
	
	/**
	 * Update the ambient color of the object
	 * @param ambientColor a float vector3 which contains the ambient color r, g, b of the object
	 */
	public void updateAmbientColor(Color3f ambientColor) {
		

		abstraction.setAmbientColor(ambientColor);
		presentation.setAmbientColor(ambientColor);
		System.out.println("CObject.updateAmbientColor()");
		if ( !entity.isServer() )
			System.out.println("Etape 4: " + ((Client)entity).title + " " + abstraction.getAmbientColor() + "\n");
	}

	/**
	 * Set the diffuse color of the object
	 * @param diffuseColor a float vector3 which contains the diffuse color r, g, b of the object
	 */
	public void setDiffuseColor(Color3f diffuseColor) {
		abstraction.setDiffuseColor(diffuseColor);
		presentation.setDiffuseColor(diffuseColor);
	}
	

	@Override
	public void setId(String id) {
		abstraction.setId(id);
	}

	
	
	// ---------------------------------------------------------
	//						Getters
	// ---------------------------------------------------------
	
	
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
	public IAObject getAbstraction() {
		return abstraction;
	}




	@Override
	public void setAbstraction(IAObject object) {
		abstraction = object;
	}




	@Override
	public void reload() {
		setGeometry(getGeometry());
		//updateAmbientColor(getAmbientColor());
		setDiffuseColor(getDiffuseColor());
		
		presentation.setPosition(getPosition());
		presentation.setAmbientColor(getAmbientColor());
	}





	private Vector3d getPosition() {
		return abstraction.getPosition();
	}


	private Color3f getAmbientColor() {
		return abstraction.getAmbientColor();
	}

	private Color3f getDiffuseColor() {
		return abstraction.getDiffuseColor();
	}


	
	
	public static ICObject getObjectById(List<ICObject> objs, String id) {
		for ( ICObject obj : objs) {
			if (obj.getId().equals(id)) {
				return obj;
			}
		}
		return null;
	}

	public IEntity getEntity() {
		return entity;
	}

	public void setEntity(IEntity entity) {
		this.entity = entity;
	}
}
