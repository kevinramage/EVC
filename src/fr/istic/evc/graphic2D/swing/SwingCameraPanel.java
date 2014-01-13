package fr.istic.evc.graphic2D.swing;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.istic.evc.graphic2D.base.CameraPanel;

public class SwingCameraPanel extends CameraPanel {

	private JPanel panel;
	
	public SwingCameraPanel() {
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.black);
		System.out.println("Je passe par la");
		/*
		panel.add(new JButton("Bottom"));
		panel.add(new JButton("Top"));
		panel.add(new JButton("Left"));
		panel.add(new JButton("Right"));*/
	}
	
	
	@Override
	public Object getComponent() {
		return panel;
	}

}
