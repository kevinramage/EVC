/**
 * Interface which contains the presentation of a 3D object
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 * 
 */

package object3D.presentation.interfaces;

import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

import object3D.controller.interfaces.ICObject;

public interface IPObject {

	/**
	 * Set the 3D position of the object
	 * @param position a vector3 which contains the position x, y, z of the object
	 */
	void setPosition(Vector3d position);
	
	/**
	 * Set the orientation of the object
	 * @param quat4f a quaternion which define the orientation of the object
	 */
	void setOrientation(Quat4d quat4f);
	
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

	ICObject getController();

	Transform3D getTransform();

	void setTransparency(float transparency);
}
