package command.create;

import command.I_Command;

import object3D.abstraction.I_AObject;
import object3D.controller.CElasticObject;
import object3D.controller.CSubject;
import object3D.controller.interfaces.ICObject;
import project.IEntity;


public class CmdCreateCElastic implements I_Command {

	/* ---------- Attributes ---------- */
	
	private static final long serialVersionUID = 1L;
	protected I_AObject abstraction;
	protected I_AObject sphere1, sphere2;
	
	public CmdCreateCElastic(I_AObject abstraction, I_AObject abs1, I_AObject abs2) {
		this.abstraction = abstraction;
		sphere1 = abs1;
		sphere2 = abs2;
	}
	
	@Override
	public void execute(IEntity entity) {
		CSubject s1 = (CSubject) entity.getWorld().getObjectById(sphere1.getId());
		CSubject s2 = (CSubject) entity.getWorld().getObjectById(sphere2.getId());
		
		ICObject elastic = new CElasticObject(abstraction, s1, s2);
		elastic.setEntity(entity);
		entity.getWorld().add(elastic);
	}

}
