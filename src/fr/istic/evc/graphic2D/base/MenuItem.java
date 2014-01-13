/**
 * A component which represent an element of the menu.
 * This command can be associated with a command 
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 * 
 */

package fr.istic.evc.graphic2D.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

import fr.istic.evc.graphic2D.command.Command;
import fr.istic.evc.utils.ErrorManager;


public abstract class MenuItem extends Component {

	
	// ------------------------------------------------------------------------------------------------------------------------------------
	//																ATTRIBUTES
	// ------------------------------------------------------------------------------------------------------------------------------------	
	
	protected List<MenuItem> items;
	protected String text;
	protected boolean isSubMenu;
	protected String commandName;

	
	
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	//																METHODS
	// ------------------------------------------------------------------------------------------------------------------------------------
	
	// Constructor
	public MenuItem() {
		items = new ArrayList<MenuItem>();
	}

	// Load directly from menu
	public void loadFromMenu(Element e, ErrorManager errorManager) {
		setIsSubMenu(true);
		load(e, errorManager);
	}
	
	// Load menu item from xml element
	public void load(Element e, ErrorManager errorManager) {
		
		// IsSubMenu
		if ( !isSubMenu )
			setIsSubMenu(!e.getChildren().isEmpty());
		
		// Text
		if ( e.getAttribute("text") != null) {
			setText(e.getAttributeValue("text"));
		}
		
		// Command
		if ( e.getAttribute("command") != null && e.getAttributeValue("command") != "") {
			commandName = e.getAttributeValue("command");
		}

		// Items
		Element items = e.getChild("Items");
		if ( items != null) {
		
			// MenuItem
			for ( Element subElt : items.getChildren()) {
				MenuItem item = factory.createMenuItem();
				item.load(subElt, errorManager);
				addItem(item);
			}
		}
	}
	
	// Load command
	public void loadCommand (ErrorManager errorManager) {
		
		if ( commandName != null ) {
			
			// Instanciate command
			try {
				Window window = (Window)getWindow();
				Class<?> commandClass = Class.forName("fr.istic.evc.graphic2D.command." + commandName);
				Constructor<?> constructor = commandClass.getConstructor(new Class[] { Window.class });
				Command command = (Command)constructor.newInstance(new Object[] { window });
				addCommand(command);
				
			} catch (InstantiationException e1) {
				errorManager.addError("Impossible to find the command: " + commandName);
			} catch (IllegalAccessException e1) {
				errorManager.addError("Impossible to find the command: " + commandName);
			} catch (IllegalArgumentException e1) {
				errorManager.addError("Impossible to find the command: " + commandName);
			} catch (InvocationTargetException e1) {
				errorManager.addError("Impossible to find the command: " + commandName);
			} catch (NoSuchMethodException e1) {
				errorManager.addError("Impossible to find the command: " + commandName);
			} catch (SecurityException e1) {			
				errorManager.addError("Impossible to find the command: " + commandName);
			} catch (ClassNotFoundException e1) {
				errorManager.addError("Impossible to find the command: " + commandName);
			} catch (NoClassDefFoundError e1) {
				errorManager.addError("Impossible to find the command: " + commandName);
			}
		}
		
		// Load command for childrens
		for ( MenuItem item : items) {
			item.loadCommand(errorManager);
		}
	}
	
	// Add item
	public void addItem(MenuItem item) {
		item.setParent(this);
		items.add(item);
	}
	
	
	
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	//																INTERFACE
	// ------------------------------------------------------------------------------------------------------------------------------------	
		
	
	// Add command
	public abstract void addCommand(Command command);
	
	
	
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	//																ACCESSORS / MUTATORS
	// ------------------------------------------------------------------------------------------------------------------------------------	
		
	
	// IsSubMenu
	public void setIsSubMenu(boolean value) { isSubMenu = value; }
	public boolean getIsSubMenu() { return isSubMenu; } 
	
	// Text
	public void setText(String text) { this.text = text; }
	public String getText() { return this.text; }
	
}
