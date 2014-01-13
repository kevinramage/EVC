/**
 * Allow to create a universe from a xml file
 * Proceed in 4 steps: create gui, load devices, load users and load objects
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 *
 */

package fr.istic.evc.builder;

import java.io.File;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import fr.istic.evc.business.Univers;
import fr.istic.evc.graphic2D.factory.Graphic2DFactory;
import fr.istic.evc.graphic3D.factory.Graphic3DFactory;
import fr.istic.evc.utils.ErrorManager;


public class Builder {

	// ------------------------------------------------------------------------------------------------------------------------------------
	//																ATTRIBUTES
	// ------------------------------------------------------------------------------------------------------------------------------------
	private ErrorManager errorManager;
	private String universName;
	
	
	
	
	// ------------------------------------------------------------------------------------------------------------------------------------
	//																METHODS
	// ------------------------------------------------------------------------------------------------------------------------------------
	
	// Constructor
	public Builder(String universName) {
	
		this.universName = universName;
		
		// Instanciate an error manager
		errorManager = new ErrorManager();
		
		// Show start of conversion
		System.out.println(" - Start EVC");
		System.out.println(" - Load univers: " + universName);
	}

	// Create univers from a xml file
	public Univers createUnivers(Graphic2DFactory graphic2dFactory, Graphic3DFactory graphic3dFactory) {
		
		// Univers
		Univers univers = new Univers();
		
		// Load XML file
		Document document = null;
		SAXBuilder sxb = new SAXBuilder();
		try {
			document = sxb.build(new File(universName));
		} catch ( Exception ex) {
			errorManager.addError("Impossible to load univers " + universName);
		}
		
		if ( document != null ) {
			
			// Root
			Element root = document.getRootElement();
			
			// World
			String worldName = "";
			Element childWorld = root.getChild("World");
			if ( childWorld != null) 
				worldName = childWorld.getText();
			else
				errorManager.addError("Impossible to find the world file");
			
			// Device
			String deviceName = "";
			Element childDevice = root.getChild("Device");
			if ( childDevice != null) 
				deviceName = childDevice.getText();
			else
				deviceName = "resources/device/default.xml";
			
			// Interface
			String interfaceName = "";
			Element childInterface = root.getChild("Interface");
			if ( childInterface != null) 
				interfaceName = childInterface.getText();
			else
				interfaceName = "resources/interface/default.xml";
		
		
			// Create gui
			BuilderGUI builderGUI = new BuilderGUI(interfaceName);
			builderGUI.createGUI(graphic2dFactory, errorManager);
		}
		
		// Show end of conversion
		if ( errorManager.isInError()) {
			errorManager.showError();
			System.err.println(" - Impossible to load " + universName);
		} else {
			System.out.println(" - world successfull loaded");
		}
		
		
		return univers;
	}

}
