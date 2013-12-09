package fr.istic.evc.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class CustomArea extends InterfaceItem {
	
	private Map<String, JPanel> mapContent;
	
	public CustomArea() {
		mapContent = new HashMap<String, JPanel>();
		mapContent.put("moveView", new MoveView());
		AreaAccess.getInstance().setCustomArea(this);
		panel.setLayout(null);
		System.out.println("Custom area constructor");
		//panel.setBackground(Color.red);
	}

	public void changeView(String viewName) {
		System.out.println("ViewName: " + viewName);
		panel.removeAll();
		JPanel content = mapContent.get(viewName);
		content.setBackground(Color.green);
		content.setPreferredSize(panel.getPreferredSize());
		content.setSize(panel.getSize());
		System.out.println("Content Size: " + content.getSize());
		System.out.println("Components: " + content.getComponentCount());
		panel.add(content);
		panel.validate();
		panel.repaint();
	}
}
