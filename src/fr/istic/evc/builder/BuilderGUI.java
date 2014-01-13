/**
 * Allow to create the GUI from an XML file
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 *
 */

package fr.istic.evc.builder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import fr.istic.evc.graphic2D.base.Component;
import fr.istic.evc.graphic2D.base.Container;
import fr.istic.evc.graphic2D.base.MainView;
import fr.istic.evc.graphic2D.base.Menu;
import fr.istic.evc.graphic2D.base.Window;
import fr.istic.evc.graphic2D.factory.Graphic2DFactory;
import fr.istic.evc.utils.Alignment;
import fr.istic.evc.utils.ErrorManager;
import fr.istic.evc.utils.size.Size;


public class BuilderGUI {
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	//																ATTRIBUTES
	// ------------------------------------------------------------------------------------------------------------------------------------
	private String fileName;
	
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	//																METHODS
	// ------------------------------------------------------------------------------------------------------------------------------------
	
	// Constructor
	public BuilderGUI(String fileName) {
		this.fileName = fileName;
	}

	// Create a GUI from a XML file
	public Window createGUI(Graphic2DFactory graphic2dFactory, ErrorManager errorManager) {
		
		// Show progression
		System.out.println(" -   Load GUI");
		
		// Window
		Window window = graphic2dFactory.createWindow();
		
		// Load XML file
		Document document = null;
		SAXBuilder sxb = new SAXBuilder();
		try {
			document = sxb.build(new File(fileName));
		} catch ( Exception ex) {
			errorManager.addError("Impossible to load gui " + fileName);
		}
		
		if ( document != null ) {
			
			// Root
			Element root = document.getRootElement();
			if ( !root.getName().equals("Window")) {
				errorManager.addError("GUI don't have Window root");
			}
			
			// Alignment
			window.setAlignment(Alignment.readFromXML(root, errorManager));
			
			// Size
			Size [] sizes = Size.readFromXML(root, errorManager);
			window.setSize(sizes[0].getExactSize(), sizes[1].getExactSize());
			
			// Components
			List<Component> components = new ArrayList<Component>();
			Component component = null;
			for( Element child : root.getChild("Components").getChildren()) {
				
				switch (child.getName()) {
				
					// Menu
					case "Menu":
						Menu menu = graphic2dFactory.createMenu();
						menu.load(child, errorManager);
						component = menu;
						break;
					
					// Container
					case "Container":
						Container container = graphic2dFactory.createContainer();
						container.load(child, errorManager);
						component = container;
						break;
						
					// MainView
					case "MainView":
						MainView mainView = graphic2dFactory.createMainView();
						mainView.load(child, errorManager);
						component = mainView;
						break;
						
					default:
						errorManager.addError("component '" + child.getName() + "not exists");
				}
				
				// Link to window
				component.linkToWindow(window);
				
				// Add component
				components.add(component);
				window.addComponent(component);
			}
			
			// Load command
			for ( Component c : components ) {
				c.loadCommand(errorManager);
			}
			
			// Manage component size and position
			window.updateSize();
		}
		
		// Show window
		window.show();
		
		// Show progression
		if ( !errorManager.isInError() ) {
			System.out.println(" -   GUI successfull loaded");
		}
		
		return window;
	}
}
