package fr.istic.evc.graphic2D.swing;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import fr.istic.evc.graphic2D.base.Window;

public class SwingWindow extends Window{
	private JFrame frame;
	
	public SwingWindow() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("EVC - Titre à trouver");
		frame.setLayout(null);
		frame.setBackground(Color.orange);
	}
	
	@Override
	public void show() {
		frame.setVisible(true);
	}
	
	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setSize(width, height);
		frame.pack();
	}
	
	@Override
	public Object getComponent() {
		return frame;
	}
	
	// Get the exact window height
	@Override
	protected int getWindowWidth() {
		return frame.getContentPane().getSize().width;
	}
	
	// Get the exact window height
	@Override
	protected int getWindowHeight() {
		return frame.getContentPane().getSize().height;
	}
	
	@Override
	public void exit() {
		System.exit(0);
	}


}
