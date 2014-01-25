package object3D.controller;

import object3D.abstraction.I_AObject;
import object3D.controller.interfaces.ICWorld;
import object3D.presentation.PElasticObject;
import pattern.Observer;
import project.IEntity;

import command.I_Command;
import command.create.CmdCreateCElastic;

/**
 * Object manipulated by 2 users
 *
 */
public class CElasticObject extends CObject implements Observer {

	
	/* ---------- Attributes ---------- */
	protected String s1Name, s2Name;
	protected CSubject s1, s2;

	
	
	
	/* ---------- Constructors ---------- */
	public CElasticObject() {
		presentation = new PElasticObject(this);
	}
	public CElasticObject(I_AObject abstraction, CSubject s1, CSubject s2) {
		this();
		this.s1 = s1;
		this.s2 = s2;
		this.s1.attach(this);
		this.s2.attach(this);
		this.abstraction = abstraction;
		((PElasticObject)presentation).update(s1.getPosition(), s2.getPosition());
	}
	
	
	/* ---------- Methods ---------- */
	public void updateS1(String s1Id) {
		this.s1Name = s1Id;
	}
	
	public void updateS2(String s2Id) {
		this.s2Name = s2Id;
	} 
	
	@Override
	public void init(IEntity entity, ICWorld world) {
		
		// Get s1
		s1 = (CSubject) world.getObjectById(s1Name);
		s1.attach(this);
		
		// Get s1
		s2 = (CSubject) world.getObjectById(s2Name);
		s2.attach(this);
		
		// Presentation
		((PElasticObject)presentation).init(s1.getPosition(), s2.getPosition());
	}


	@Override
	public void update() {
		((PElasticObject)presentation).update(s1.getPosition(), s2.getPosition());
	}


	@Override
	public I_Command getCreateCommand() {
		return new CmdCreateCElastic(abstraction, s1.getAbstraction(), s2.getAbstraction());
	}
	
	
	
}
