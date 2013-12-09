/**
 * Abstraction of a virtual object
 * Define the main basics caracteristics of a virtual object
 * 
 * @author Thomas CARO
 * @author Kevin RAMAGE
 * @version 1.0
 */

package fr.istic.evc.object.common.abstraction;

import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

public class AObject implements IAObject {
	
	// --------------------------------------------------------------------------------------------------------------------------
	// 														ATTRIBUTES
	// --------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * Unique ID of a virtual object
	 * Identify object through the network
	 */
	protected String id;
	
	/**
	 * 3D Position of this virtual object 
	 */
	protected Vector3d position;
	
	/**
	 * Orientation of this virtual object
	 */
	protected Quat4d orientation;
	
	/**
	 * VRML file name which describe the geometry of this virtual object 
	 */
	protected String geometry;
	
	
	
	
	
	// --------------------------------------------------------------------------------------------------------------------------
	// 														METHODS
	// --------------------------------------------------------------------------------------------------------------------------
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Vector3d getPosition() {
		return position;
	}
	public void setPosition(Vector3d position) {
		this.position = position;
	}
	
	public Quat4d getOrientation() {
		return orientation;
	}
	public void setOrientation(Quat4d orientation) {
		this.orientation = orientation;
	}
	
	public String getGeometry() {
		return geometry;
	}
	public void setGeometry(String geometry) {
		this.geometry = geometry;
	}
	
	
}
