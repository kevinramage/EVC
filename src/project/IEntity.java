package project;

import command.I_Command;
import object3D.controller.interfaces.ICObject;
import object3D.controller.interfaces.ICWorld;

public interface IEntity {
	boolean isServer();
	
	ICWorld getWorld();

	int getId();

	void addObjectInWorld(ICWorld world, ICObject obj);

	void broadCastUpdateCommand(I_Command cmdUpdate);

	boolean havePick(ICObject obj);

	//TODO a supprimer
	String getTitle();

	void showAllObjects();
}
