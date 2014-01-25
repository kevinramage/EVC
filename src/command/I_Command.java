package command;

import java.io.Serializable;

import project.IEntity;

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
