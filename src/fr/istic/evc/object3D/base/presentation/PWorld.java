package fr.istic.evc.object3D.base.presentation;

import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Node;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;

import com.sun.j3d.utils.universe.SimpleUniverse;

import fr.istic.evc.device.IDevice;
import fr.istic.evc.object3D.base.controller.CWorld;
import fr.istic.evc.object3D.base.presentation.interfaces.IPObject;
import fr.istic.evc.object3D.base.presentation.interfaces.IPWorld;

public class PWorld implements IPWorld{

	protected CWorld controller;
	protected Canvas3D canvas3D;
	protected SimpleUniverse simpleUnivers;
	protected BranchGroup scene;
	protected TransformGroup transformRoot;
	
	
	public PWorld(CWorld controller) {
		
		canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		simpleUnivers = new SimpleUniverse(canvas3D);
		simpleUnivers.getViewingPlatform().setNominalViewingTransform();
		scene = new BranchGroup();
		transformRoot = new TransformGroup();
		transformRoot.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
	}

	@Override
	public Canvas3D getCanvas() {
		return canvas3D;
	}

	@Override
	public TransformGroup getWorldTransform() {
		return simpleUnivers.getViewingPlatform().getViewPlatformTransform();
	}

	@Override
	public void add(IPObject presentation) {
		BranchGroup branchGroupUseless = new BranchGroup();
		branchGroupUseless.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		branchGroupUseless.addChild((TransformGroup)presentation);
		transformRoot.addChild(branchGroupUseless);
	}

	@Override
	public void show() {
		scene.addChild(transformRoot);
		simpleUnivers.addBranchGraph(scene);
	}

	@Override
	public void addDevice(IDevice device) {
		
		// Device
		device.setBranchGroup(scene);
		
		// Add to scene
		BoundingSphere bounds = new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 1.0) ;
		((Behavior)device).setSchedulingBounds (bounds) ;
		scene.addChild((Behavior)device);
	}
	
}
