package fr.istic.evc.object3D.base.controller;

import fr.istic.evc.Command.CmdCreateCElastic;
import fr.istic.evc.Command.CmdCreateCObject;
import fr.istic.evc.Command.I_CreateCommand;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.presentation.PElasticObject;
import fr.istic.evc.pattern.Observer;

/**
 * Object manipulated by 2 users
 *
 */
public class CElasticObject extends CObject implements Observer {

	CSubject sphere1, sphere2;

	
	public CElasticObject(CSubject s1, CSubject s2) {
		sphere1 = s1;
		sphere2 = s2;
		presentation = new PElasticObject(this, s1.getPosition(), s2.getPosition());
		sphere1.attach(this);
		sphere2.attach(this);
	}


	@Override
	public void update() {
		((PElasticObject)presentation).update(sphere1.getPosition(), sphere2.getPosition());
	}


	@Override
	public I_CreateCommand getCreateCommand() {
		return new CmdCreateCElastic(sphere1.getAbstraction(), sphere2.getAbstraction());
	}
	
	
	
}
