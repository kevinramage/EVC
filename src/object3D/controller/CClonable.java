package object3D.controller;

import java.util.ArrayList;
import java.util.List;

import object3D.abstraction.I_AObject;
import object3D.controller.interfaces.ICWorld;
import pattern.Observer;

import command.CmdReferent;
import command.I_Command;
import command.clone.CmdChangeToClones;
import command.clone.CmdCreateCClonable;
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
	public I_Command getCreateCommand() {
		return new CmdCreateCClonable(this.getAbstraction());
	}
	
	@Override
	public void setSelected(boolean selected) {
		
		// No clone => no conflicts
		if (!isSelected()) {
			
			// Propagate the selection of the object
			propagateUpdateSelected(selected);
		}
		
		// Select clonable
		else if ( selected ) {
			
			// Propagate the transformation of the object
			propagateChangeToClones();
		}
	}
	
	private void propagateUpdateSelected(boolean selected) {
		
		// Create update command
		I_Command cmdUpdate = new CmdUpdateSelected(getId(), selected);
		
		// Propagate command
		if (referent) {
			entity.broadCastUpdateCommand(cmdUpdate);
		} else {
			entity.broadCastUpdateCommand(new CmdReferent(getId(), entity.getId(), cmdUpdate));
		}
	}
	
	private void propagateChangeToClones() {
		
		// Create update command
		I_Command cmdUpdate = new CmdChangeToClones(getId(), entity.getId());
		
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
	
	
	@Override
	public void update() {

	}

	public int getNbElm() {
		return this.clones.size();
	}
	
	public CClone getLast() {
		return this.clones.get(this.clones.size()-1);
	}


	
	public static String findName(ICWorld world, String id, int count) {
		if ( world.getObjectById(id + "-clone" + count) == null) {
			return id + "-clone" + count;
		} else {
			return findName(world, id, count + 1);
		}
	}
}
