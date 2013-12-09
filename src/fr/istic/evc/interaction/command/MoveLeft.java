package fr.istic.evc.interaction.command;

import fr.istic.evc.object.common.controller.CUnivers;

public class MoveLeft implements Command {

	@Override
	public void execute() {
		CUnivers.getInstance().getCamera().moveLeft();
	}

}
