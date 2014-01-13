package fr.istic.evc.project;

import fr.istic.evc.builder.Builder;
import fr.istic.evc.graphic2D.factory.Graphic2DFactory;
import fr.istic.evc.graphic2D.factory.SwingFactory;
import fr.istic.evc.graphic3D.factory.Graphic3DFactory;
import fr.istic.evc.graphic3D.factory.Java3DFactory;

public class Main {

	public static void main(String[] args) {
		
		// Instanciate factories
		Graphic2DFactory graphic2dFactory = new SwingFactory();
		Graphic3DFactory graphic3dFactory = new Java3DFactory();
		
		// Create builder
		String worldName = "resources/univers/01.xml";
		Builder builder = new Builder(worldName);
		builder.createUnivers(graphic2dFactory, graphic3dFactory);
	}

}
