package fr.istic.evc.interaction;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import fr.istic.evc.interaction.command.ActivateMove;

/**
 * Bouton pour changer de vue - Mode Rotation
 * 
 * @author 27001623
 * 
 */
public class RotateAction extends Action {

	public RotateAction() {
		
		JButton btnRotate = new JButton(new ImageIcon("resources/image/rotate.png"));
		btnRotate.setPreferredSize(new Dimension(30, 30));
		btnRotate.setSize(30, 30);
		panel.add(btnRotate);
		panel.setLocation(40, 0);

		System.out.println("RotateAction");
	}
}
