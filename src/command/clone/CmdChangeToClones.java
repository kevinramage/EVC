package command.clone;

import object3D.abstraction.I_AObject;
import object3D.controller.CClonable;
import object3D.controller.interfaces.ICObject;
import object3D.controller.interfaces.ICWorld;
import project.IEntity;
import command.I_Command;
import device.IDevice;


public class CmdChangeToClones implements I_Command {
	
	private static final long serialVersionUID = 1L;
	private String idCloneable;
	private int idClient;
	
	public CmdChangeToClones(String idCloneable, int idClient) {
		this.idCloneable = idCloneable;
		this.idClient = idClient;
	}

	@Override
	public void execute(IEntity entity) {

		// Get cloneable object
		ICObject obj = entity.getWorld().getObjectById(idCloneable);
		
		// Remove the selected color
		obj.updateAmbientColor(obj.getAbstraction().getBackupColor());

		// Give special information to devices
		for ( IDevice device : entity.getWorld().getDevices()) {
			
			// Indicate to device to unpick the clonable object without change the object select state (avoid translate of the original object)
			device.forceUnpick(obj);
			
			// Indicate to device to ignore the selection of the object
			if ( entity.getId() == idClient) {
				device.addToBlackList(obj);
			}
		}
		
		
		// Create a clone
		I_AObject abstraction = obj.getAbstraction().clone();
		abstraction.setId(CClonable.findName(entity.getWorld(), idClient + obj.getId(), 1));
		abstraction.setTransparency(0.8f);
		CmdCreateClone cmd = new CmdCreateClone(abstraction);
		cmd.setIdClient(idClient);
		cmd.setIdClonable(idCloneable);
		cmd.execute(entity);
	}
	


}
