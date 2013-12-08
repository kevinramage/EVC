package fr.istic.evc.factory;

import java.io.File;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import fr.istic.evc.gui.MainFrame;
import fr.istic.evc.object.common.controller.Univers;
import fr.istic.evc.object.common.controller.World;

public class UniversFactory {

	private static UniversFactory instance;
	
	
	private UniversFactory() {
		
	}
	
	
	public Univers createUnivers(String url) {
		
		Univers univers = new Univers();
		
		// Open file
		Document document = null;
		SAXBuilder sxb = new SAXBuilder();
		try {
			document = sxb.build(new File(url));
		} catch ( Exception ex) {
			System.err.println("Impossible to load world: " + url);
		}
		// Root
		Element root = document.getRootElement();
		
		// World
		Element childWorld = root.getChild("World");
		World world = WorldFactory.getInstance().createWorld(childWorld.getText());
		
		// Device
		Element childDevice = root.getChild("Device");

		
		// Interface
		Element childInterface = root.getChild("Interface");
		MainFrame mainFrame = MainFrameFactory.getInstance().createMainFrame(childInterface.getText());
		univers.setMainFrame(mainFrame);
		
		return univers;
	}
	
	
	public static UniversFactory getInstance() {
		if ( instance == null ) {
			instance = new UniversFactory();
		}
		return instance;
	}
}
