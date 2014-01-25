package object3D.controller;

import java.util.ArrayList;
import java.util.List;

import object3D.abstraction.I_AObject;
import pattern.Observer;
import project.Client;
import command.CmdReferent;
import command.I_Command;
import command.clone.CmdAddClone;
import command.clone.CmdChangeToClones;
import command.clone.CmdCreateCClonable;
import command.create.I_CreateCommand;
import command.update.CmdUpdateSelected;


public class CClonable extends CObject implements Observer {
	
	List<CClone> clones;
	
	public CClonable() {
		super();
		clones = new ArrayList<>();
	}
	
	public CClonable(I_AObject abstraction) {
		super(abstraction);
		clones = new ArrayList<>();
	}

	
	/* ---------- Methods ---------- */
	
	@Override
	public I_CreateCommand getCreateCommand() {
		return new CmdCreateCClonable(this.getAbstraction());
	}
	
	@Override
	public void setSelected(boolean selected) {

		System.out.println("CClonable.setSelected() - ");
		
		// No clone => no conflicts
		if (!isSelected()) {
			
			// Propagate the selection of the object
			propagateUpdateSelected(selected);
			System.out.println("CClonable.setSelected() - No conflict");
		}
		
		// 2 users on the same object => conflict
		else if (isSelected() && clones.isEmpty()) {
			
			// Propagate the transformation of the object
			propagateChangeToClones();
			System.out.println("CClonable.setSelected() - One conflict");
		}
		
		// Other user pick the object => conflict
		else if (isSelected() && !alreadyPick()) {
			
			// Propagate the add of a clone
			propagateAddClone();
		}
	}
	
	private void propagateUpdateSelected(boolean selected) {
		
		// Create update command
		I_Command cmdUpdate = new CmdUpdateSelected(this.getId(), selected);
		
		// Propagate command
		if (referent) {
			entity.broadCastUpdateCommand(cmdUpdate);
		} else {
			entity.broadCastUpdateCommand(new CmdReferent(getId(), entity.getId(), cmdUpdate));
		}
	}
	
	private void propagateChangeToClones() {
		
		// Create update command
		I_Command cmdUpdate = new CmdChangeToClones(this.getId());
		
		// Propagate command
		if (referent) {
			entity.broadCastUpdateCommand(cmdUpdate);
		} else {
			entity.broadCastUpdateCommand(new CmdReferent(getId(), entity.getId(), cmdUpdate));
		}		
	}
	
	private void propagateAddClone() {
		
		// Create update command
		I_Command cmdUpdate = new CmdAddClone(this.getId());
		
		// Propagate command
		if (referent) {
			entity.broadCastUpdateCommand(cmdUpdate);
		} else {
			entity.broadCastUpdateCommand(new CmdReferent(getId(), entity.getId(), cmdUpdate));
		}		
	}
	
	
	public void addClone(CClone clone) {
		clones.add(clone);
	}
	
	public void removeClone(CClone clone) {
		clones.remove(clone);
	}
	
	private boolean alreadyPick() {
		boolean b = false;
		for (CClone c:clones) {
			if (!b && c.getIdClient() == ((Client)entity).getId()) {
				b = true;
			}
		}
		return b;
	}
	
	
	@Override
	public void update() {

	}

	public int getNbElm() {
		return this.clones.size();
	}
	
	public CClone getLast() {
		return this.clones.get(this.clones.size()-1);
	}

//	public void ckeck() {
//		System.out.println("CClonable.ckeck()");
//		System.out.println("Information sur le clonable :");
//		System.out.println("Id : "+this.getId());
//		System.out.println("Nb de clones : "+this.clones.size());
//		System.out.println("Pickable : "+this.isPickable());
//		System.out.println("Selectionné : "+this.isSelected());
//	}

}
