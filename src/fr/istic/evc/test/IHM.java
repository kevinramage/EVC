package fr.istic.evc.test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import fr.istic.evc.object3D.base.controller.CAmbientLight;
import fr.istic.evc.object3D.base.controller.CDirectionalLight;
import fr.istic.evc.object3D.base.controller.CObject;
import fr.istic.evc.object3D.base.controller.CWorld;
import fr.istic.evc.object3D.base.controller.interfaces.ICAmbientLight;
import fr.istic.evc.object3D.base.controller.interfaces.ICDirectionalLight;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;
import fr.istic.evc.test.View.VIEW_TYPE;

public class IHM extends JFrame {

	private static final long serialVersionUID = 1L;
	
	// Camera
	//private CameraManager actualCamera;
	
	// Constructor
	public IHM() {
		
		/*
		// Move system camera
		TransformGroup transformGroup = world.getPresentation().getWorldTransform();
		Transform3D transform3D = new Transform3D();
		transformGroup.getTransform(transform3D);
		Vector3d vector3D = new Vector3d();
		transform3D.get(vector3D);
		vector3D.z = 30;
		transform3D.set(vector3D);
		transformGroup.setTransform(transform3D);
		
		// Camera
		Camera cameraSystem = new Camera();
		Transform3D tCameraSystem = new Transform3D();
		tCameraSystem.setTranslation(new Vector3d(0, 0, 30));
		cameraSystem.setTransform3D(tCameraSystem);
		
		// navigation Mode
		NavigationMode walkMode = new WalkMode();
		
		// Active camera
		actualCamera = new CameraManager(world.getPresentation().getWorldTransform());
		actualCamera.changeCamera(cameraSystem);
		actualCamera.changeNavigationMode(walkMode);
		
		
		// Create scene
		//BranchGroup scene = new BranchGroup();
		



		// Sphere1
		TransformGroup transformGroup4 = new TransformGroup();
		appearance = new Appearance();
		ambientColour = new Color3f(1.0f, 1.0f, 0.0f);
	    diffuseColour = new Color3f(1.0f, 1.0f, 0.0f);
	    specularColour = new Color3f(1.0f, 1.0f, 1.0f);
	    emissiveColour = new Color3f(0.0f, 0.0f, 0.0f);
	    shininess = 20.0f;
	    appearance.setMaterial(new Material(ambientColour, emissiveColour, diffuseColour, specularColour, shininess));
		Sphere sphere1 = new Sphere(1f, appearance);
		vector3D = new Vector3d(0, 10, 0);
		transform3D = new Transform3D();
		transform3D.set(vector3D);
		transformGroup4.setTransform(transform3D);
		transformGroup4.addChild(sphere1);
		scene.addChild(transformGroup4);
		
		// Sphere2
		TransformGroup transformGroup5 = new TransformGroup();
		appearance = new Appearance();
		ambientColour = new Color3f(0.0f, 1.0f, 1.0f);
	    diffuseColour = new Color3f(0.0f, 1.0f, 1.0f);
	    specularColour = new Color3f(1.0f, 1.0f, 1.0f);
	    emissiveColour = new Color3f(0.0f, 0.0f, 0.0f);
	    shininess = 20.0f;
	    appearance.setMaterial(new Material(ambientColour, emissiveColour, diffuseColour, specularColour, shininess));
		sphere1 = new Sphere(1f, appearance);
		vector3D = new Vector3d(0, -10, 0);
		transform3D = new Transform3D();
		transform3D.set(vector3D);
		transformGroup5.setTransform(transform3D);
		transformGroup5.addChild(sphere1);
		scene.addChild(transformGroup5);		

		

		// Behaviour
		KeyBehaviour keyBehaviour = new KeyBehaviour(actualCamera);
		keyBehaviour.setSchedulingBounds(new BoundingSphere());
		scene.addChild(keyBehaviour);
		
		// Pickable
		MouseInteractor mi = new MouseInteractor (scene) ;
		BoundingSphere bounds = new BoundingSphere (new Point3d (0.0, 0.0, 0.0), 1.0) ;
		mi.setSchedulingBounds (bounds) ;
		scene.addChild(mi);

		// Scene
		//simpleUnivers.addBranchGraph(scene);
		
		
		// Bottom panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setSize(1024, 200);
		bottomPanel.setLocation(0, 768 - 200);
		bottomPanel.setLayout(null);
		add(bottomPanel);
		
		// Custom Panel
		JPanel customPanel = new JPanel();
		customPanel.setSize(200, 200);
		customPanel.setBackground(Color.red);
		customPanel.setLayout(null);
		bottomPanel.add(getTranslationPanel());
		
		// Command Panel
		JPanel commandPanel = new JPanel();
		commandPanel.setSize(1024-250-200, 200);
		commandPanel.setLocation(200, 0);
		commandPanel.setLayout(null);
		bottomPanel.add(commandPanel);
		
		// Selection Panel
		JPanel selectionPanel = new JPanel();
		selectionPanel.setSize(250, 200);
		selectionPanel.setLocation(1024 - 250, 0);
		selectionPanel.setBackground(Color.blue);
		bottomPanel.add(selectionPanel);
		

		
		// Zoom out command
		JButton btnZoomOut = new JButton();
		btnZoomOut.setSize(40, 40);
		btnZoomOut.setLocation(220, 10);
		btnZoomOut.setIcon(new ImageIcon("resources/image/zoomOut.png"));
		btnZoomOut.setToolTipText("Zoom out");
		commandPanel.add(btnZoomOut);	
		
		// Add command
		JButton btnAdd = new JButton();
		btnAdd.setSize(40, 40);
		btnAdd.setLocation(270, 10);
		btnAdd.setIcon(new ImageIcon("resources/image/add.png"));
		btnAdd.setToolTipText("Add");
		commandPanel.add(btnAdd);	
		
		// Delete command
		JButton btnDelete = new JButton();
		btnDelete.setSize(40, 40);
		btnDelete.setLocation(320, 10);
		btnDelete.setIcon(new ImageIcon("resources/image/delete.png"));
		btnDelete.setToolTipText("Delete");
		commandPanel.add(btnDelete);	
		
		// Cube command
		JButton btnCube = new JButton();
		btnCube.setSize(40, 40);
		btnCube.setLocation(60, 60);
		btnCube.setIcon(new ImageIcon("resources/image/cube.png"));
		btnCube.setToolTipText("Cube");
		commandPanel.add(btnCube);	
		
		// Sphere command
		JButton btnSphere = new JButton();
		btnSphere.setSize(40, 40);
		btnSphere.setLocation(120, 60);
		btnSphere.setIcon(new ImageIcon("resources/image/sphere.png"));
		btnSphere.setToolTipText("Sphere");
		commandPanel.add(btnSphere);	
		
		// Triangle command
		JButton btnTriangle = new JButton();
		btnTriangle.setSize(40, 40);
		btnTriangle.setLocation(180, 60);
		btnTriangle.setIcon(new ImageIcon("resources/image/triangle.png"));
		btnTriangle.setToolTipText("Triangle");
		commandPanel.add(btnTriangle);
		
		//
		
		
		
		// Rotate X
		
		// Rotate Y
		
		// Rotate Y
		

		
		// Listeners
		
		// - Camera

		

		
		// - View
		
		
		
		
		
		
		
		// Canvas3D
		//canvas3D.addMouseMotionListener(new WalkMML(world.getPresentation().getWorldTransform()));
		//canvas3D.addMouseWheelListener(new MWL(actualCamera));
		world.load();
		
		// Visible
		setVisible(true);
		
		// Focus
		//canvas3D.setFocusable(true);
		//canvas3D.requestFocus();
		*/
	}
	
	
	private static void getTranslationPanel() {
		/*
		JPanel panel = new JPanel();
		panel.setSize(200, 200);
		panel.setLayout(null);
		
		// Top command
		JButton btnTop = new JButton();
		btnTop.setSize(40, 40);
		btnTop.setLocation(40, 0);
		btnTop.setIcon(new ImageIcon("resources/image/top.png"));
		btnTop.setToolTipText("Top");
		panel.add(btnTop);	
		
		// Bottom command
		JButton btnBottom = new JButton();
		btnBottom.setSize(40, 40);
		btnBottom.setLocation(40, 80);
		btnBottom.setIcon(new ImageIcon("resources/image/bottom.png"));
		btnBottom.setToolTipText("Bottom");
		panel.add(btnBottom);			
		
		// Left command
		JButton btnLeft = new JButton();
		btnLeft.setSize(40, 40);
		btnLeft.setLocation(0, 40);
		btnLeft.setIcon(new ImageIcon("resources/image/left.png"));
		btnLeft.setToolTipText("Left");
		panel.add(btnLeft);
		
		// Right command
		JButton btnRight = new JButton();
		btnRight.setSize(40, 40);
		btnRight.setLocation(80, 40);
		btnRight.setIcon(new ImageIcon("resources/image/right.png"));
		btnRight.setToolTipText("Right");
		panel.add(btnRight);
		
		// Center command
		JButton btnCenter = new JButton();
		btnCenter.setSize(40, 40);
		btnCenter.setLocation(40, 40);
		btnCenter.setIcon(new ImageIcon("resources/image/center.png"));
		btnCenter.setToolTipText("Center");
		panel.add(btnCenter);	
		
		// Zoom in
		JButton btnZoomIn = new JButton();
		btnZoomIn.setSize(40, 40);
		btnZoomIn.setLocation(130, 20);
		btnZoomIn.setIcon(new ImageIcon("resources/image/in.png"));
		btnZoomIn.setToolTipText("Zoom In");
		panel.add(btnZoomIn);	
		
		// Zoom out
		JButton btnZoomOut = new JButton();
		btnZoomOut.setSize(40, 40);
		btnZoomOut.setLocation(130, 70);
		btnZoomOut.setIcon(new ImageIcon("resources/image/out.png"));
		btnZoomOut.setToolTipText("Zoom Out");
		panel.add(btnZoomOut);	
		
		return panel;
		*/
	}
	
	public static void main(String[] args) {
		new IHM();
	}

}
