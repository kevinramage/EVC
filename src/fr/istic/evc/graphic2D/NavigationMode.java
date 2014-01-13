package fr.istic.evc.graphic2D;

import javax.media.j3d.TransformGroup;

public interface NavigationMode {
	
	// Translate
	void translateX(TransformGroup transformGroup, int x);
	void translateY(TransformGroup transformGroup, int y);
	void translateZ(TransformGroup transformGroup, int z);
}
