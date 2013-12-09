package fr.istic.evc.factory;

import java.io.File;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import fr.istic.evc.gui.MainFrame;
import fr.istic.evc.object.common.controller.CObject;
import fr.istic.evc.object.common.controller.CWorld;

public class WorldFactory {
	
	private static WorldFactory instance;
	public static final String PATH_OBJECT = "fr.istic.evc.object.common.controller.";
	
	
	private WorldFactory(){
		
	}
	
	public CWorld createWorld(String url) {
		
		// World
		CWorld world = new CWorld();
		
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
		
		// Objects
		Element childObjects = root.getChild("Objects");
		for ( Element elt : childObjects.getChildren()) {
			
			try {
				CObject object = (CObject)Class.forName(  PATH_OBJECT + "C" + elt.getName()).newInstance();
				object.load(elt);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return world;
	}
	
	
	public static WorldFactory getInstance() {
		if (instance == null) {
			instance = new WorldFactory();
		}
		return instance;
	}
}
