package command;

import object3D.controller.interfaces.ICObject;
import project.IEntity;

public class CmdReferent implements I_Command {

	private static final long serialVersionUID = 1L;
	
	private String objectId;
	private int entityId;
	private I_Command command;
	
	public CmdReferent(String objectId, int entityId, I_Command command) {
		this.objectId = objectId;
		this.entityId = entityId;
		this.command = command;
	}
	
	@Override
	public void execute(IEntity client) {
		
		// Get object
		ICObject obj = client.getWorld().getObjectById(objectId);
		if ( client.getId() == entityId) {
			obj.setReferent(true);
		} else {
			obj.setReferent(false);
		}
		
		// Execute command
		command.execute(client);
	}

}
