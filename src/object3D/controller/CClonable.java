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
import command.update.CmdUpdatePosition;
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

		// No clone
		if (!selected) {
			
			// Propagate cmd
			setUpdateSelected(selected);
		}
		
		// On selectionne le clonable, plusieurs cas de figure
		else {
			if (!entity.isServer()) {
				System.out.println("Selection d'un clonable : ");
				I_Command cmd = null;
				if (!isSelected()) {
					System.out.println("Cas 1 - Juste lui");
					cmd = new CmdUpdateSelected(this.getId(), selected);
				}
				else if (isSelected() && clones.isEmpty()){
					System.out.println("Cas 2 - Creation des clones");
					cmd = new CmdChangeToClones(this.getId());
				}
				else if (isSelected() && !alreadyPick()) {
					System.out.println("Cas 3 - Ajout de clone");
					cmd = new CmdAddClone(this.getId());
				}
				if (cmd != null)
					((Client)entity).changed(cmd);
			}
			else {
				updateSelected(selected);
			}
		}
	}
	
	private void setUpdateSelected(boolean selected) {
		
		// Create update command
		I_Command cmdUpdate = new CmdUpdateSelected(this.getId(), selected);
		
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
