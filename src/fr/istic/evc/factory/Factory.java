package fr.istic.evc.factory;

import fr.istic.evc.object.common.controller.Univers;
import fr.istic.evc.object.common.controller.World;

/**
 * Singleton - Factory permettant de créer le monde et l'univers
 * @author Kevin Ramage - Thomas Caro - M2GL
 *
 */
public class Factory {
	
	private static Factory instance;
	
	private Factory() {
		
	}
	
	public World createWorld() {
		return new World();
	}
	public Univers createUnivers() {
		return new Univers();
	}
	
	
	public static Factory getInstance() {
		if ( instance != null ) {
			instance = new Factory();
		}
		return instance;
	}
}
