package fr.istic.evc.utils;

import java.util.HashMap;

import org.jdom2.Element;

public class Alignment {
	
	private static HashMap<String, ALIGNMENT> possibleValues;
	
	public enum ALIGNMENT { HORIZONTAL, VERTICAL }
	private ALIGNMENT alignment;
	
	public ALIGNMENT getAlignment () { return alignment; }
	
	public static Alignment readFromXML(Element elt, ErrorManager errorManager) {
		
		Element alignmentChild = elt.getChild("Alignment");
		if (alignmentChild != null) {
			
			if (possibleValues.containsKey(alignmentChild.getText())) {
				Alignment alignment = new Alignment();
				alignment.alignment = possibleValues.get(alignmentChild.getText());
				return alignment;
			} else {
				errorManager.addError("Wrong format for alignment type");
				return null;
			}
			
		} else {
			errorManager.addError("No alignment found for this component");
			return null;
		}
	}
	
	static {
		possibleValues = new HashMap<String, Alignment.ALIGNMENT>();
		possibleValues.put("horizontal", ALIGNMENT.HORIZONTAL);
		possibleValues.put("Horizontal", ALIGNMENT.HORIZONTAL);
		possibleValues.put("HORIZONTAL", ALIGNMENT.HORIZONTAL);
		possibleValues.put("vertical", ALIGNMENT.VERTICAL);
		possibleValues.put("Vertical", ALIGNMENT.VERTICAL);
		possibleValues.put("VERTICAL", ALIGNMENT.VERTICAL);
	}
}
