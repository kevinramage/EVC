package fr.istic.evc.Clone;

import java.util.ArrayList;
import java.util.List;

import fr.istic.evc.Command.CmdUpdateSelected;
import fr.istic.evc.Command.I_Command;
import fr.istic.evc.Command.I_CreateCommand;
import fr.istic.evc.device.Mouse;
import fr.istic.evc.object3D.base.abstraction.I_AObject;
import fr.istic.evc.object3D.base.controller.CObject;
import fr.istic.evc.pattern.Observer;
import fr.istic.evc.project.Client;

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
		System.out.println("CClonable.setSelected()");
		System.out.println(clones.size());
			if (!entity.isServer()) {
				I_Command cmd = null;
				if (!isSelected()) {
					System.out.println("CAS 1");
					cmd = new CmdUpdateSelected(this.getId(), selected);
				}
				else if (isSelected() && clones.isEmpty()){
					System.out.println("CAS 2");
					cmd = new CmdChangeToClones(this.getId());
				}
				else if (isSelected() && !alreadyPick()) {
					System.out.println("CAS 3");
					System.out.println(this.clones.size());
					cmd = new CmdAddClone(this.getId());
				}
				if (cmd != null)
					((Client)entity).changed(cmd);
				else
					System.out.println("Aucune commande à lancer");
			}
			else {
				updateSelected(selected);
			}
	}
	
	public void addClone(CClone clone) {
		clones.add(clone);
	}
	
	public void removeClone(CClone clone) {
		System.out.println("CClonable.removeClone()");
		System.out.println("Av - "+clones.size());
		clones.remove(clone);
		System.out.println("Ap - "+clones.size());
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

}
