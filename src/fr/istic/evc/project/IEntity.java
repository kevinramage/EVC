package fr.istic.evc.project;

import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;

public interface IEntity {
	boolean isServer();
	
	ICWorld getWorld();
}
