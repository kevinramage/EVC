/**
 * Interface which contains the abstraction of a 3D object
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 * 
 */

package fr.istic.evc.object3D.base.abstraction;

import java.io.Serializable;

import javax.vecmath.Color3f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3d;

public interface I_AObject extends Serializable {
	
	/**
	 * Set the 3D position of the object
	 * @param position a vector3 which contains the position x, y, z of the object
	 */
	void setPosition(Vector3d position);
	
	/**
	 * Set the orientation of the object
	 * @param orientation a quaternion which define the orientation of the object
	 */
	void setOrientation(Quat4f orientation);
	
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
	void setPickable(boolean b);
	
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
	 * Set the select color of the object
	 * @param diffuseColor a float vector3 which contains the diffuse color r, g, b of the object
	 */
	void setSelectColor(Color3f selectColor);
	
	/**
	 * Set the backup color of the object
	 * @param backupColor a float vector3 which contains the diffuse color r, g, b of the object
	 */
	void setBackupColor(Color3f backupColor);

	
	/**
	 * Set the object's id
	 * @param id object's id
	 */
	void setId(String id);
	
	
	
	

	/**
	 * Get the geometry of the object
	 * @return the name of the primitve geometry or the url of the object
	 */
	String getGeometry();

	/**
	 * Get the object id
	 * @return the object id
	 */
	String getId();

	Color3f getAmbientColor();
	Color3f getDiffuseColor();
	Color3f getSelectColor();

	Vector3d getPosition();

	Color3f getBackupColor();

	Quat4f getOrientation();

	boolean isPickable();


}
