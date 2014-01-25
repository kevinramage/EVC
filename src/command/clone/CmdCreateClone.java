package command.clone;

import object3D.abstraction.I_AObject;
import object3D.controller.CClonable;
import object3D.controller.CClone;
import object3D.controller.interfaces.ICObject;
import project.IEntity;
import command.I_Command;
import device.IDevice;


public class CmdCreateClone implements I_Command {
	
	/* ---------- Attributes ---------- */
	
	private static final long serialVersionUID = 1L;
	
	private I_AObject abstraction;
	private String idClonable;
	private int idClient;
	


	/* ---------- Constructors ---------- */
	
	public CmdCreateClone(I_AObject abstraction) {
		this.abstraction = abstraction;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	
	public void setIdClonable(String idClonable) {
		this.idClonable = idClonable;
	}
	
	@Override
	public void execute(IEntity entity) {
		
		
		// Create object
		ICObject controller = new CClone(abstraction, idClient, idClonable);
		controller.reload();
		controller.setEntity(entity);
		controller.setSelected(true);
		entity.getWorld().add(controller);
		
		// Get cloneable
		ICObject clonable = entity.getWorld().getObjectById(((CClone)controller).getIdClonable());
		
		// Configure device
		for ( IDevice device : entity.getWorld().getDevices()) {
			
			// Check if entity is the clone owner (Attribute the right to pick clone) + Lock the clonable object
			if ( idClient == entity.getId()) {
				device.forcePick(controller);
				device.addToBlackList(clonable);
			} else {
				device.addToBlackList(controller);
			}
		}
		
		// Add clone to the list of the cloneable object
		((CClonable)clonable).addClone(((CClone)controller));

	}


}
