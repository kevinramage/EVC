/**
 * Size of a component, size can be express in pixel or in percentage
 * This class specify methods that sub class must implemented and provide method to load size from xml element
 *
 * @author Kevin RAMAGE
 * @author Thomas CARO
 *
 */

package fr.istic.evc.utils.size;

import org.jdom2.Element;

import fr.istic.evc.utils.ErrorManager;

public abstract class Size {
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	//																INTERFACE
	// ------------------------------------------------------------------------------------------------------------------------------------	
	
	// Reduce allocable size
	public abstract int reduceAllocableSize(int size);
	
	// Get the exact size from a total allocable height
	public abstract int getExactSize(int totalSize);
	
	// Get exact size previously calculated
	public abstract int getExactSize();
	
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	//																METHODS
	// ------------------------------------------------------------------------------------------------------------------------------------
	
	// Load a size from a xml element
	public static Size[] readFromXML(Element elt, ErrorManager errorManager) {
		
		Element sizeChild = elt.getChild("Size");
		if ( sizeChild != null) {
			
			String [] data = sizeChild.getText().split(" ");
			if ( data.length == 2) {
				
				// Width
				Size width = null;
				if ( data[0].endsWith("%")) {
					try {
						width = new PercentageSize(Integer.parseInt(data[0].substring(0, data[0].length() - 1)));
					} catch (NumberFormatException ex) {
						errorManager.addError("Size wrong format");
						return null;
					}
				} else if (data[0].endsWith("px")) {
					try {
						width = new PixelSize(Integer.parseInt(data[0].substring(0, data[0].length() - 2)));
					} catch (NumberFormatException ex) {
						errorManager.addError("Size wrong format");
						return null;
					}
				} else {
					try {
						width = new PixelSize(Integer.parseInt(data[0]));
					} catch (NumberFormatException ex) {
						errorManager.addError("Size wrong format");
						return null;
					}
				}
				
				// Height
				Size height = null;
				if ( data[1].endsWith("%")) {
					try {
						height = new PercentageSize(Integer.parseInt(data[1].substring(0, data[1].length() - 1)));
					} catch (NumberFormatException ex) {
						errorManager.addError("Size wrong format");
						return null;
					}
				} else if (data[1].endsWith("px")) {
					try {
						height = new PixelSize(Integer.parseInt(data[1].substring(0, data[1].length() - 2)));
					} catch (NumberFormatException ex) {
						errorManager.addError("Size wrong format");
						return null;
					}
				} else {
					try {
						height = new PixelSize(Integer.parseInt(data[1]));
					} catch (NumberFormatException ex) {
						errorManager.addError("Size wrong format");
						return null;
					}
				}
				
				return new Size [] { width, height };
			} else {
				errorManager.addError("Size wrong format");
				return null;
			}
			
		} else {
			errorManager.addError("Impossible to find the size of a component");
			return null;
		}
		
	}
}
