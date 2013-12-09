package fr.istic.evc.interaction.command;

import fr.istic.evc.gui.CustomArea;

public class ActivateMove implements Command {
	
	private CustomArea area;
	
	public ActivateMove(CustomArea area) {
		this.area = area;
	}
	
	@Override
	public void execute() {
		area.changeView("moveView");
		
	}

}
