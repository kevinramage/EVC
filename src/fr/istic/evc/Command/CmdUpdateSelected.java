package fr.istic.evc.Command;

import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.project.IEntity;

public class CmdUpdateSelected implements I_Command {

	private static final long serialVersionUID = 1L;
	private String id;
	private boolean selected;

	public CmdUpdateSelected(String id, boolean selected) {
		this.id = id;
		this.selected = selected;
	}

	@Override
	public void execute(IEntity entity) {
		ICObject obj = entity.getWorld().getObjectById(id);
		obj.updateSelected(selected);
	}

}
