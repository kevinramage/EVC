package fr.istic.evc.project;

import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;

public interface IEntity {
	boolean isServer();
	
	ICWorld getWorld();

	int getId();

	boolean havePick(ICObject obj);
	
	void showAllObjects();

	//TODO a supprimer
	String getTitle();


}
