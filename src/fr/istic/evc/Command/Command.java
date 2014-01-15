package fr.istic.evc.Command;

import java.io.Serializable;
import java.util.List;

import fr.istic.evc.object3D.base.controller.interfaces.ICObject;

public interface Command extends Serializable {
	void execute(List<ICObject> objs);
	boolean isToPropagate();
}
