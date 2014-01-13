/**
 * Interface which contains the controller of a 3D object
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 * 
 */

package fr.istic.evc.object3D.base.controller.interfaces;

import javax.vecmath.Color3f;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

import fr.istic.evc.object3D.base.abstraction.IAObject;
import fr.istic.evc.object3D.base.presentation.interfaces.IPObject;


public interface ICObject {
	
	/**
	 * Set the 3D position of the object
	 * @param position a vector3 which contains the position x, y, z of the object
	 */
	void setPosition(Vector3d position);
	
	/**
	 * Set the orientation of the object
	 * @param orientation a quaternion which define the orientation of the object
	 */
	void setOrientation(Quat4d orientation);
	
	/**
	 * Set the geometry of the object
	 * @param geometry the geometry primitive name or the geometry url of the object
	 */
	void setGeometry(String geometry);

	/**
	 * Set the scale of the object
	 * @param scale a vector3 which contains the scale x, y, z of the object
	 */
	void setScale(Vector3d scale);
	
	/**
	 * Define if the object if pickable or not
	 * @param b boolean which determine if object is pickable or not
	 */
	void IsPickable(boolean b);
	
	/**
	 * Set the ambient color of the object
	 * @param ambientColor a float vector3 which contains the ambient color r, g, b of the object
	 */
	void setAmbientColor(Color3f ambientColor);

	/**
	 * Set the diffuse color of the object
	 * @param diffuseColor a float vector3 which contains the diffuse color r, g, b of the object
	 */
	void setDiffuseColor(Color3f diffuseColor);

	/**
	 * Set the id of the object
	 * @param string id of the object
	 */
	void setId(String id);
	
	/**
	 * Set the abstractoion layer of the object
	 * @param object abstraction layer
	 */
	void setAbstraction(IAObject object);
	

	void reload();


	/**
	 * Get the geometry of the object
	 * @return the name of the primitve geometry or the url of the object
	 */
	String getGeometry();

	
	/**
	 * Get the abstraction layer of the object
	 * @return the abstraction layer
	 */
	IAObject getAbstraction();
	
	/**
	 * Get the presentation layer of the object
	 * @return the presentation layer
	 */
	IPObject getPresentation();

	
	
	/**
	 * Get the object id
	 * @return the object id
	 */
	String getId();






}
