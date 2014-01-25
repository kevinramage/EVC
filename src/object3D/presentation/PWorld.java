package object3D.presentation;

import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;

import object3D.controller.CWorld;
import object3D.presentation.interfaces.IPObject;
import object3D.presentation.interfaces.IPWorld;

import com.sun.j3d.utils.universe.SimpleUniverse;

import device.IDevice;

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
		simpleUnivers.getViewer().getView().setFieldOfView(1.5);
		scene = new BranchGroup();
		scene.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
		transformRoot = new TransformGroup();
		transformRoot.setCapability(TransformGroup.ALLOW_CHILDREN_EXTEND);
		transformRoot.setCapability(TransformGroup.ALLOW_CHILDREN_READ);
		transformRoot.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		transformRoot.setCapability(BranchGroup.ALLOW_DETACH);
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
		branchGroupUseless.setCapability(BranchGroup.ALLOW_DETACH);
		branchGroupUseless.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
		branchGroupUseless.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
		branchGroupUseless.addChild((TransformGroup)presentation);
		transformRoot.addChild(branchGroupUseless);
	}

	@Override
	public void remove(IPObject presentation) {
		transformRoot.removeChild(((TransformGroup)presentation).getParent());
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
		BranchGroup b = new BranchGroup();
		b.addChild((Behavior)device);
		scene.addChild(b);
	}
	
	
	
}
