package fr.istic.evc.factory;

import java.io.File;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import fr.istic.evc.gui.InterfaceItem;
import fr.istic.evc.gui.MainFrame;

public class MainFrameFactory {
	
	public static final String GUI_PATH = "fr.istic.evc.gui";
	private static MainFrameFactory instance;
	
	private MainFrameFactory() {
		
	}
	
	public MainFrame createMainFrame(String url) {
		
		MainFrame mainFrame = new MainFrame();
		
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
		
		// Title
		String title = root.getChild("Title").getText();
		mainFrame.setTitle(title);
		
		// Size
		String size = root.getChild("Size").getText();
		int width = Integer.parseInt(size.split(" ")[0]);
		int height = Integer.parseInt(size.split(" ")[1]);
		mainFrame.setSize(width, height);
		
		// Interfaces
		Element childInterface = root.getChild("Interfaces");
		for ( Element elt : childInterface.getChildren()) {
			String name = GUI_PATH + "." + elt.getName(); 
			try {
				InterfaceItem item = (InterfaceItem)Class.forName(name).newInstance();
				item.load(elt, width, height);
				mainFrame.addInterface(item);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return mainFrame;
	}
	
	public static MainFrameFactory getInstance() {
		if ( instance == null ) {
			instance = new MainFrameFactory();
		}
		return instance;
	}
}
