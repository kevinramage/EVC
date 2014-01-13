package fr.istic.evc.graphic2D.swing;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.istic.evc.graphic2D.base.Container;
import fr.istic.evc.graphic2D.base.Window;
import fr.istic.evc.utils.RandomColor;

public class SwingContainer extends Container {

	private JPanel panel;
	
	
	// Constructor
	public SwingContainer() {
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(RandomColor.getColor());
	}
	
	// Get component
	@Override
	public Object getComponent() {
		return panel;
	}

	// Set real position
	@Override
	public void setRealPositionY(int positionY) {
		panel.setLocation(panel.getX(), positionY);
	}
	
	// Set real size
	@Override
	public void updateSize() {
		panel.setSize(realWidth, realHeight);
		panel.setPreferredSize(new Dimension(realWidth, realHeight));
	}
	
	// Link to window
	@Override
	public void linkToWindow(Window window) {
		((JFrame)window.getComponent()).add((JPanel)getComponent());
	}
}
