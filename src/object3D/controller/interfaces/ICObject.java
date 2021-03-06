/**
 * Interface which contains the controller of a 3D object
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 * 
 */

package object3D.controller.interfaces;

import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

import object3D.abstraction.I_AObject;
import object3D.presentation.interfaces.IPObject;
import project.IEntity;

import command.I_Command;


public interface ICObject{
	
	/**
	 * Set the 3D position of the object
	 * @param position a vector3 which contains the position x, y, z of the object
	 */
	void setPosition(Vector3d position);
	void updatePosition(Vector3d position);

	/**
	 * Set the orientation of the object
	 * @param orientation a quaternion which define the orientation of the object
	 */
	void setOrientation(Quat4d orientation);
	void updateOrientation(Quat4d orientation);
	
	/**
	 * Set the geometry of the object
	 * @param geometry the geometry primitive name or the geometry url of the object
	 */
	void setGeometry(String geometry);
	void updateGeometry(String geometry);

	/**
	 * Set the scale of the object
	 * @param scale a vector3 which contains the scale x, y, z of the object
	 */
	void setScale(Vector3d scale);
	void updateScale(Vector3d scale);
	
	/**
	 * Define if the object if pickable or not
	 * @param b boolean which determine if object is pickable or not
	 */
	void setPickable(boolean b);
	void updatePickable(boolean b);
	
	/**
	 * Set the ambient color of the object
	 * @param ambientColor a float vector3 which contains the ambient color r, g, b of the object
	 */
	void setAmbientColor(Color3f ambientColor);
	void updateAmbientColor(Color3f ambientColor);	

	/**
	 * Set the diffuse color of the object
	 * @param diffuseColor a float vector3 which contains the diffuse color r, g, b of the object
	 */
	void setDiffuseColor(Color3f diffuseColor);
	void updateDiffuseColor(Color3f diffuseColor);	
	
	
	void setSelected(boolean selected);
	void updateSelected(boolean selected);
	
	void setTransparency(float transparency);
	void updateTransparency(float transparency);
	

	/**
	 * Set the id of the object
	 * @param string id of the object
	 */
	void setId(String id);
	

	void reload();
	
	// L'entite ne connait pas le monde a se moment
	void init(IEntity entity, ICWorld world);


	/**
	 * Get the geometry of the object
	 * @return the name of the primitve geometry or the url of the object
	 */
	String getGeometry();

	
	/**
	 * Get the abstraction layer of the object
	 * @return the abstraction layer
	 */
	I_AObject getAbstraction();
	
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
	
	void setEntity(IEntity c);
	

	I_Command getCreateCommand();
	
	
	Transform3D getTransform();
	Vector3d getPosition();
	Quat4d getOrientation();
	Color3f getAmbientColor();
	Color3f getSelectColor();
	Color3f getDiffuseColor();
	Color3f getBackupColor();
	float getTransparency();
	boolean isPickable();
	void setReferent(boolean b);
	boolean isSelected();






}
