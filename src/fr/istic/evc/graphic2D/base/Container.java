/**
 * A component which contains multiple component
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 */

package fr.istic.evc.graphic2D.base;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Element;

import fr.istic.evc.utils.Alignment;
import fr.istic.evc.utils.ErrorManager;
import fr.istic.evc.utils.Alignment.ALIGNMENT;
import fr.istic.evc.utils.size.Size;

public abstract class Container extends Component{

	// ------------------------------------------------------------------------------------------------------------------------------------
	//																ATTRIBUTES
	// ------------------------------------------------------------------------------------------------------------------------------------	
	
	// Components
	protected Alignment alignment;
	protected List<Component> components;
	
	
	
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	//																METHODS
	// ------------------------------------------------------------------------------------------------------------------------------------
	
	// Constructor
	public Container() {
		components = new ArrayList<Component>();
	}
	
	// Load a container from a xml file
	public void load(Element elt, ErrorManager errorManager) {
		
		// Id
		Attribute att = elt.getAttribute("id");
		if ( att != null ) {
			setId(att.getValue());
		}
		
		// Size
		Size [] sizes = Size.readFromXML(elt, errorManager);
		setSize(sizes[0], sizes[1]);
		
		// Components
		Element componentsElt = elt.getChild("Components");
		if ( componentsElt != null) {
			for ( Element componentElt : componentsElt.getChildren()) {
				
				Component component = null;
				switch ( componentElt.getName()) {
					case "CameraPanel":
						CameraPanel cameraPanel = factory.createCameraPanel();
						cameraPanel.load(componentElt, errorManager);
						component = cameraPanel;
					break;
				}
				
				components.add(component);
			}
		}
		
		
		// Show construction
		System.out.println(" -   " + toString() + "' created");
	}
	
	// Load command
	public void loadCommand(ErrorManager errorManager) {
		
	}
	
	// Classic to string
	public String toString() {
		if (getId() != null) {
			return "Container " + getId();
		} else {
			return "Container";
		}
	}
	
	
	
	// Calculate the allocable width
	protected int calculateWidth() {
		
		// Window width
		int allocableWidth = realWidth;
		
		// Components
		for (Component c : components) {
			allocableWidth = c.getWidth().reduceAllocableSize(allocableWidth);
		}
		
		return allocableWidth;
	}
	
	// Calculate the allocable height
	protected int calculateHeight() {
		
		// Window height
		int allocableHeight = realHeight;
		
		// Components
		for (Component c : components) {
			allocableHeight = c.getHeight().reduceAllocableSize(allocableHeight);
		}
		
		return allocableHeight;
	}

	// Assign width to components
	protected void assignComponentsWidth(int totalSize) {
		
		for ( Component c : components) {
			
			// Get exact width
			int width = c.getWidth().getExactSize(totalSize);
			
			// Assign width
			c.setRealWidth(width);
		}
	}
	
	// Assign height to components
	protected void assignComponentsHeight(int totalSize) {
		
		for ( Component c : components) {
			
			// Get exact height
			int height = c.getHeight().getExactSize(totalSize);

			// Assign height
			c.setRealHeight(height);
		}
	}
	
	// Assign x position to components
	protected void assignComponentsPositionX() {
		int positionY = 0;
		
		for ( Component c : components) {
			c.setRealPositionY(positionY);
			if ( alignment.getAlignment() == ALIGNMENT.HORIZONTAL) {
				positionY += c.getHeight().getExactSize();
			}
		}
	}
	
	// Assign y position to components
	protected void assignComponentsPositionY() {
		int positionY = 0;
		
		for ( Component c : components) {
			c.setRealPositionY(positionY);
			if ( alignment.getAlignment() == ALIGNMENT.VERTICAL) {
				positionY += c.getHeight().getExactSize();
			}
		}
	}	
	
	// Update size
	public void updateSize() {
		// Width
		int allocableWidth = calculateWidth();
		assignComponentsWidth(allocableWidth);
		
		// Height
		int allocableHeight = calculateHeight();
		assignComponentsHeight(allocableHeight);
		
		// Position X
		assignComponentsPositionX();
		
		// Position Y
		assignComponentsPositionY();
		
		for ( Component c : components) {
			c.updateSize();
		}
	}
}
