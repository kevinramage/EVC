package command.create;

import java.io.Serializable;

import object3D.controller.interfaces.ICWorld;
import project.IEntity;

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
