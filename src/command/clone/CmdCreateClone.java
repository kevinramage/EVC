package command.clone;

import object3D.abstraction.I_AObject;
import object3D.controller.CClonable;
import object3D.controller.CClone;
import object3D.controller.interfaces.ICObject;
import project.IEntity;

import command.I_Command;


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
		ICObject controller;
		controller = new CClone(abstraction, idClient, idClonable);
		controller.reload();
		controller.setEntity(entity);
		controller.setSelected(true);
		entity.getWorld().add(controller);
		

		ICObject clonable = entity.getWorld().getObjectById(((CClone)controller).getIdClonable());
		if (idClient == entity.getId()) {
			entity.getWorld().getDevices().get(0).forcePick(controller);
			entity.getWorld().getDevices().get(0).addToBlackList(clonable);
		}
		else {
			entity.getWorld().getDevices().get(0).addToBlackList(controller);
		}
		((CClonable)clonable).addClone(((CClone)controller));

	}


}
