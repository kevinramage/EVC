/**
 * Component which supply an interface in order to access to all commande available in this application
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 */

package fr.istic.evc.graphic2D.base;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Element;

import fr.istic.evc.utils.ErrorManager;
import fr.istic.evc.utils.size.Size;

public abstract class Menu extends Component {

	// ------------------------------------------------------------------------------------------------------------------------------------
	//																ATTRIBUTES
	// ------------------------------------------------------------------------------------------------------------------------------------	
	
	// Items of the menu
	protected List<MenuItem> items;
	
	
	
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	//																METHODS
	// ------------------------------------------------------------------------------------------------------------------------------------
	
	// Constructor
	public Menu() {
		items = new ArrayList<MenuItem>();
	}
	
	// Load a menu from xml file
	public void load(Element elt, ErrorManager errorManager) {
		
		// Id
		Attribute att = elt.getAttribute("id");
		if ( att != null ) {
			setId(att.getValue());
		}
		
		// Size
		Size [] sizes = Size.readFromXML(elt, errorManager);
		setSize(sizes[0], sizes[1]);
		
		// Items
		Element items = elt.getChild("Items");
		if (items != null) {
			
			// MenuItem
			for ( Element e : items.getChildren()) {
				
				MenuItem menuItem = factory.createMenuItem();
				menuItem.loadFromMenu(e, errorManager);
				addItem(menuItem);
			}
		}
		
		// Show construction
		System.out.println(" -   " + toString() + "' created");
	}
	
	// Load command
	public void loadCommand(ErrorManager errorManager) {
	
		for ( MenuItem item : items) {
			item.loadCommand(errorManager);
		}
	}
	
	// Add an item to the menu
	public void addItem(MenuItem item) {
		item.setParent(this);
		items.add(item);
	}
	
	// Classic to string
	@Override
	public String toString() {
		if (getId() != null) {
			return "Menu " + getId();
		} else {
			return "Menu";
		}
	}
}
