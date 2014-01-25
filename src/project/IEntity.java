package project;

import object3D.controller.interfaces.ICObject;
import object3D.controller.interfaces.ICWorld;

import command.I_Command;

public interface IEntity {
	boolean isServer();
	
	ICWorld getWorld();

	int getId();

	void addObjectInWorld(ICWorld world, ICObject obj);
	
	// Broadcast
	void broadCastAddCommand(I_Command cmdAdd);
	void broadCastUpdateCommand(I_Command cmdUpdate);
	void broadCastDeleteCommand(I_Command cmdDelete);

	boolean havePick(ICObject obj);

	//TODO a supprimer
	String getTitle();

	void showAllObjects();

	
	
	void removeSeletedObjects();
}
