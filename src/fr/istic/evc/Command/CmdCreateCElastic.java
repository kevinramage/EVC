package fr.istic.evc.Command;

import fr.istic.evc.object3D.base.abstraction.I_AObject;
import fr.istic.evc.object3D.base.controller.CElasticObject;
import fr.istic.evc.object3D.base.controller.CSubject;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;
import fr.istic.evc.project.IEntity;

public class CmdCreateCElastic implements I_CreateCommand {

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
	public void execute(ICWorld world, IEntity entity) {
		CSubject s1 = (CSubject) world.getObjectById(sphere1.getId());
		CSubject s2 = (CSubject) world.getObjectById(sphere2.getId());
		
		ICObject elastic = new CElasticObject(abstraction, s1, s2);
		elastic.setEntity(entity);
		world.add(elastic);
	}

}
