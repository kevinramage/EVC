package fr.istic.evc.gui;


public class AreaAccess {
	
	private static AreaAccess instance;
	
	private CustomArea customArea;
	
	private AreaAccess() {
	}
	
	public static AreaAccess getInstance() {
		if (instance == null)
			instance = new AreaAccess();
		
		return instance;
	}
	
	public CustomArea getCustomArea() {
		return customArea;
	}

	public void setCustomArea(CustomArea customArea) {
		this.customArea = customArea;
	}
	
	
}
