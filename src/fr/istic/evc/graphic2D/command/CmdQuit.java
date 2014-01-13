package fr.istic.evc.graphic2D.command;

import fr.istic.evc.graphic2D.base.Window;

public class CmdQuit implements Command {

	private Window window;
	
	public CmdQuit(Window window) {
		this.window = window;
	}
	
	@Override
	public void execute() {
		window.exit();
	}

}
