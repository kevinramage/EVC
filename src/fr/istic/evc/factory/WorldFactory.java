package fr.istic.evc.factory;

import java.io.File;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import fr.istic.evc.gui.MainFrame;
import fr.istic.evc.object.common.controller.World;

public class WorldFactory {
	
	private static WorldFactory instance;
	
	
	private WorldFactory(){
		
	}
	
	public World createWorld(String url) {
		
		// World
		World world = new World();
		
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
		
		return world;
	}
	
	
	public static WorldFactory getInstance() {
		if (instance == null) {
			instance = new WorldFactory();
		}
		return instance;
	}
}
