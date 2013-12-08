package fr.istic.evc.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jdom2.Element;

public class InterfaceItem {

	protected JPanel panel;
	
	public InterfaceItem() {
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
	}
	
	public void load(Element elt, int widthParent, int heightParent) {
		
		// Position
		Element childPosition = elt.getChild("Position");
		String [] positionElt = childPosition.getText().split(" ");
		int x = Integer.parseInt(positionElt[0]);
		int y = Integer.parseInt(positionElt[1]);
		panel.setLocation(x * widthParent / 100, y * heightParent / 100);
		
		// Size
		Element childSize = elt.getChild("Size");
		String [] sizeElt = childSize.getText().split(" ");
		int width = Integer.parseInt(sizeElt[0]);
		int height = Integer.parseInt(sizeElt[1]);
		setSize(width * widthParent / 100, height * heightParent / 100);
	}
	
	public void setSize(int w, int h) {
		panel.setPreferredSize(new Dimension(w, h));
		panel.setSize(w, h);
	}
	public void init() {}

	
	public JPanel getPanel () { return panel; }
}
