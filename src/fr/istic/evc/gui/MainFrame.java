package fr.istic.evc.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

public class MainFrame{

	private JFrame frame;

	public MainFrame(){
		
		// Frame
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
	}
	
	public void show() {
		frame.pack();
		frame.setVisible(true);
	}
	
	public void setTitle(String title) {
		frame.setTitle(title);
	}
	public void setSize(int width, int height) {
		frame.setPreferredSize(new Dimension(width, height));
		frame.setSize(width, height);
	}

	public void addInterface(InterfaceItem item) {
		frame.add(item.getPanel());
		item.init();
	}
}
