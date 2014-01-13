package fr.istic.evc.object3D.base.abstraction;

import java.util.HashMap;

import fr.istic.evc.utils.Data;

public abstract class Object3D {
	protected HashMap<String, Data> values;
	
	public Object3D() {
		values = new HashMap<String, Data>();
	}
	
	
	protected void addAttribute(String key, Object value) {
		values.put(key, new Data(value));
	}
}
