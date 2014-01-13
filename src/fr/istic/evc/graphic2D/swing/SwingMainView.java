package fr.istic.evc.graphic2D.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;

import fr.istic.evc.graphic2D.base.MainView;
import fr.istic.evc.graphic2D.base.Window;

public class SwingMainView extends MainView {

	private JPanel panel;
	private SwingCanvas3D canvas3D;
	
	// Constructor
	public SwingMainView() {
		
		// Panel
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		// Canvas 3D
		canvas3D = new SwingCanvas3D(SimpleUniverse.getPreferredConfiguration());
		panel.add(canvas3D);
		canvas3D.setFocusable(true);
		canvas3D.requestFocus();
		SimpleUniverse univers = new SimpleUniverse(canvas3D);
		univers.getViewingPlatform().setNominalViewingTransform();
		BranchGroup scene = new BranchGroup();
		TransformGroup transform = new TransformGroup();
		Transform3D t = new Transform3D();
		t.setTranslation(new Vector3d(0, 0, -10));
		transform.setTransform(t);
		transform.addChild(new ColorCube());
		scene.addChild(transform);
		univers.addBranchGraph(scene);
		//scene.compile();

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
		canvas3D.setSize(realWidth, realHeight);
		canvas3D.setPreferredSize(new Dimension(realWidth, realHeight));
	}
	
	// Link to window
	@Override
	public void linkToWindow(Window window) {
		((JFrame)window.getComponent()).add((JPanel)getComponent());
	}
	
	public static void main (String [] argv) {
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(1024, 768));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add((JPanel)new SwingMainView().getComponent());
		frame.setVisible(true);
	}

}
