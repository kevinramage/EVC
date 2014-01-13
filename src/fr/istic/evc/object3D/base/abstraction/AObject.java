/**
 * Class which contains the abstraction of a 3D object
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 * 
 */

package fr.istic.evc.object3D.base.abstraction;

import javax.vecmath.Color3f;
import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

public class AObject implements IAObject{

	// ---------------------------------------------------------
	// 						Attributes
	// ---------------------------------------------------------
	private static final long serialVersionUID = 1L;
	protected String id;
	protected Vector3d position;
	protected Quat4d orientation;
	protected String geometry;
	protected Vector3d scale;
	protected boolean isPickable;
	protected Color3f ambientColor, diffuseColor;
	
	
	
	// ---------------------------------------------------------
	//						Setters
	// ---------------------------------------------------------
	
	/**System.out.println("geometry: " );
	 * Set the object id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
	/**
	 * Set the 3D position of the object
	 * @param position a vector3 which contains the position x, y, z of the object
	 */
	public void setPosition(Vector3d position) {
		this.position = position;
	}
	
	/**
	 * Set the orientation of the object
	 * @param orientation a quaternion which define the orientation of the object
	 */
	public void setOrientation(Quat4d orientation) {
		this.orientation = orientation;
	}
	
	/**
	 * Set the geometry of the object
	 * @param geometry the geometry primitive name or the geometry url of the object
	 */
	public void setGeometry(String geometry) {
		this.geometry = geometry;
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
		isPickable = b;
	}
	
	/**
	 * Set the ambient color of the object
	 * @param ambientColor a float vector3 which contains the ambient color r, g, b of the object
	 */
	public void setAmbientColor(Color3f ambientColor) {
		this.ambientColor = ambientColor;
	}
	
	/**
	 * Set the diffuse color of the object
	 * @param diffuseColor a float vector3 which contains the diffuse color r, g, b of the object
	 */
	public void setDiffuseColor(Color3f diffuseColor) {
		this.diffuseColor = diffuseColor;
	}

	// ---------------------------------------------------------
	//						Getters
	// ---------------------------------------------------------
	
	/**
	 * Get the geometry of the object
	 * @return the name of the primitve geometry or the url of the object
	 */
	public String getGeometry() {
		return geometry;
	}


	@Override
	public String getId() {
		return id;
	}


	@Override
	public Color3f getAmbientColor() {
		return ambientColor;
	}


	@Override
	public Color3f getDiffuseColor() {
		return diffuseColor;
	}


	@Override
	public Vector3d getPosition() {
		return position;
	}
	
	
}