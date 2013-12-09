package fr.istic.evc.object.common.controller;

import org.jdom2.Element;

import fr.istic.evc.object.common.presentation.PObject;

public class CObject {

	protected PObject presentation;
	protected String id;
	protected String geometry;
	
	public CObject() {
		presentation = new PObject(this);
	}
	
	public void load(Element elt) {
		
		// ID
		id = elt.getChild("ID").getText();
		
		// Position
		
		// Orientation
		
		// Geometry
		geometry = elt.getChild("Geometry").getText();
	}

	
	public String getGeometry () { return geometry; }
}
