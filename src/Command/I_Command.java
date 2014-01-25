package fr.istic.evc.Command;

import java.io.Serializable;
import java.util.List;

import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;
import fr.istic.evc.project.IEntity;

/**
 * Command for the update of an ICObject (client side)
 * @author Kevin Ramage - Thomas Caro
 */
public interface I_Command extends Serializable {
	
	/**
	 * Execute the operation
	 * @param the world
	 */

	void execute(IEntity client);
}
