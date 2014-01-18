package fr.istic.evc.Command;

import java.io.Serializable;

import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;
import fr.istic.evc.project.IEntity;

/**
 * Command for the creation of an ICObject (client side)
 * @author Kevin Ramage - Thomas Caro
 */
public interface I_CreateCommand extends Serializable{

	/**
	 * Execute the operation
	 * @param objs List of ICObject contained in the world
	 */
	void execute(ICWorld world, IEntity entity);
}
