package fr.istic.evc.project;

import fr.istic.evc.factory.UniversFactory;
import fr.istic.evc.object.common.controller.Univers;
import fr.istic.evc.object.common.controller.World;

public class Environment {

	private World world;
	private Univers univers;
	
	
	
	// Init devices and objects ...
	public void init() {
	}

	// Load world, prepare object, detect devices, load GUI
	public void loadWorld(String url) {
		
		// Univers
		univers = UniversFactory.getInstance().createUnivers(url);

		/*
		// Objects
		for ( Object object : world.getObjects() ) {
			univers.add(object);
		}
		
		// Devices
		for ( IDevice device : world.getDevices() ) {
			device.detect();
			univers.addDevice(device);
		}
		
		// Interface
		for ( InterfaceItem item : world.getInterface() ) {
			univers.addInterfaceItem(item);
		}*/
	}

	// Call web service to add world
	public void createWorld() {

	}

	// Show window and start interaction
	public void start(User user) {
		univers.showInterface();
	}

}
