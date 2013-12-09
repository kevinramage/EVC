package fr.istic.evc.interaction;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import fr.istic.evc.gui.AreaAccess;
import fr.istic.evc.interaction.command.ActivateMove;

public class MoveAction extends Action {

	public MoveAction() {

		JButton btnMove = new JButton(new ImageIcon("resources/image/move.png"));
		btnMove.setPreferredSize(new Dimension(30, 30));
		btnMove.setSize(30, 30);
		panel.add(btnMove);
		
		
		btnMove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new ActivateMove(AreaAccess.getInstance().getCustomArea()).execute();
			}
		});
		
		
		System.out.println("MoveAction");
		
	}
}
