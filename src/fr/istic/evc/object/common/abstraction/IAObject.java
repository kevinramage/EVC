/**
 * Interface of the abstraction of a virtual object
 * Define the main basics caracteristics of a virtual object
 * 
 * @author Thomas CARO
 * @author Kevin RAMAGE
 * @version 1.0
 */

package fr.istic.evc.object.common.abstraction;

import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

public interface IAObject {
	
	// Getters
	public String getId();
	public Vector3d getPosition();
	public Quat4d getOrientation();
	public String getGeometry();
	
	// Setters
	public void setId(String value);
	public void setPosition(Vector3d value);
	public void setOrientation(Quat4d value);
	public void setGeometry(String value);
}
