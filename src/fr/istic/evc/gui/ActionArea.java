package fr.istic.evc.gui;


import java.awt.Color;
import java.awt.GridLayout;

import org.jdom2.Element;

import fr.istic.evc.interaction.Action;

public class ActionArea extends InterfaceItem {

	public ActionArea() {
		panel.setLayout(null);
//		panel.setLayout(new GridLayout(10, 2));
	}
	
	public void load(Element elt, int widthParent, int heightParent) {
		super.load(elt, widthParent, heightParent);
		
		// Actions
		Element childActions = elt.getChild("Actions");
		for ( Element element : childActions.getChildren()) {
			
			// Action name
			String actionName = element.getName();
			
			// Action
			try {
				Action action = (Action)Class.forName(Action.ACTION_PATH + "." + actionName).newInstance();
				System.out.println("Action: " + actionName);
				panel.add(action.getPanel());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
