package fr.istic.evc.interaction;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class Action {
	
	public static final String ACTION_PATH = "fr.istic.evc.interaction";
	protected JPanel panel;
	
	public Action() {
		panel = new JPanel();
		//panel.setLayout(new BorderLayout());
		//panel.setLocation(0,0);
		//panel.setPreferredSize(new Dimension(30, 30));
		//panel.setSize(30, 30);
		panel.setBackground(Color.yellow);
		System.out.println("Action");
	}
	
	
	public JPanel getPanel() {
		return panel;
	}
}
