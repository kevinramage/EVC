package fr.istic.evc.graphic2D.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.istic.evc.graphic2D.command.Command;

public class CmdActionListener implements ActionListener {

	private Command command;
	
	public CmdActionListener(Command command) {
		this.command = command;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		command.execute();
	}
	
}
