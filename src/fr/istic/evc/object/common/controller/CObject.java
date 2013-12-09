/**
 * Controller of a virtual object
 * Bridge between the presentation and abstraction
 * Responsible to communicate through the network
 * 
 * @author Thomas CARO
 * @author Kevin RAMAGE
 * @version 1.0
 */
package fr.istic.evc.object.common.controller;

import javax.vecmath.Quat4d;
import javax.vecmath.Vector3d;

import org.jdom2.Element;

import fr.istic.evc.object.common.abstraction.AObject;
import fr.istic.evc.object.common.presentation.PObject;

public class CObject implements ICObject {

	// --------------------------------------------------------------------------------------------------------------------------
	// 														ATTRIBUTES
	// --------------------------------------------------------------------------------------------------------------------------
	
	
	protected AObject abstraction;
	protected PObject presentation;
	
	
	
	
	
	// --------------------------------------------------------------------------------------------------------------------------
	// 														METHODS
	// --------------------------------------------------------------------------------------------------------------------------
	
	
	/**
	 * Constructor
	 */
	public CObject() {
		abstraction = new AObject();
		presentation = new PObject(this);
	}
	
	/**
	 * Load the virtual object from a xml file
	 * @param elt xml element
	 */
	public void load(Element elt) {
		
		// ID
		Element childId = elt.getChild("ID");
		if ( childId != null) {
			abstraction.setId(childId.getText());
		} else {
			System.err.println("CObject - Load - Impossible to find virtual object ID");
		}
		
		// Position
		Element childPosition = elt.getChild("Position");
		if ( childPosition != null) {
			String [] data = childPosition.getText().split(" ");
			abstraction.setPosition(new Vector3d(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
		} else {
			System.err.println("CObject - Load - Impossible to find virtual object position");
		}
		
		// Orientation
		Element childOrientation = elt.getChild("Orientation");
		if ( childOrientation != null) {
			String [] data = childOrientation.getText().split(" ");
			abstraction.setOrientation(new Quat4d(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3])));
		} else {
			System.err.println("CObject - Load - Impossible to find virtual object orientation");
		}		
		
		// Geometry
		Element childGeometry = elt.getChild("Geometry");
		if ( childGeometry != null) {
			abstraction.setGeometry(childGeometry.getText());
		} else {
			System.err.println("CObject - Load - Impossible to find virtual object geometry");
		}
	}

	
	// --------------------------------------------------------------------------------------------------------------------------
	// 														GETTERS
	// --------------------------------------------------------------------------------------------------------------------------
	
	


	/**
	 * Get the id of the virtual object
	 * @return the unique id
	 */
	@Override
	public String getId() {
		return abstraction.getId();
	}

	/**
	 * Get the 3D position of the virtual object
	 * @return 3D position
	 */
	@Override
	public Vector3d getPosition() {
		return abstraction.getPosition();
	}

	/**
	 * Get the orientation of the virtual object
	 * @return orientation
	 */
	@Override
	public Quat4d getOrientation() {
		return abstraction.getOrientation();
	}
	
	/**
	 * Get geometry of the virtual object
	 * @return the vrml filename of a geometry
	 */
	@Override
	public String getGeometry () { 
		return abstraction.getGeometry(); 
	}

	// --------------------------------------------------------------------------------------------------------------------------
	// 														SETTERS
	// --------------------------------------------------------------------------------------------------------------------------
	
	
	/**
	 * Set the id of the virtual object
	 * @param id unique id of the virtual object 
	 */
	@Override
	public void setId(String id) {
		abstraction.setId(id);
	}

	/**
	 * Set the 3D position of the virtual object
	 * @param position position of the virtual object
	 */
	@Override
	public void setPosition(Vector3d position) {
		abstraction.setPosition(position);
	}

	/**
	 * Set the orientation of the virtual object
	 * @param orientation orientation of the virtual object
	 */
	@Override
	public void setOrientation(Quat4d orientation) {
		abstraction.setOrientation(orientation);
	}

	/**
	 * Set the geometry of the virtual object
	 * @param geometry geometry of the virtual object
	 */
	@Override
	public void setGeometry(String geometry) {
		abstraction.setGeometry(geometry);
	}
}
