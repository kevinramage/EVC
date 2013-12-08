package fr.istic.evc.gui;

import java.awt.Dimension;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;

import com.sun.j3d.utils.universe.SimpleUniverse;

public class WorldArea extends InterfaceItem {
	
	protected Canvas3D canvas3D;
	protected SimpleUniverse simpleUniverse;
	
	public WorldArea() {

		GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration () ;
		canvas3D = new Canvas3D (config);
		panel.add(canvas3D);
	}
	
	public void init() {
		BranchGroup scene = new BranchGroup();
		scene.compile();
		simpleUniverse = new SimpleUniverse(canvas3D);
		simpleUniverse.getViewingPlatform ().setNominalViewingTransform ();
		simpleUniverse.addBranchGraph(scene);
	}
	
	
	public void setSize(int w, int h) {
		panel.setSize(w, h);
		panel.setPreferredSize(new Dimension(w, h));
		canvas3D.setPreferredSize(new Dimension(w, h));
		canvas3D.setSize(new Dimension(w, h));
	}
}
