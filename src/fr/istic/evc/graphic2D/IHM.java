package fr.istic.evc.graphic2D;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;
import fr.istic.evc.project.Client;

public class IHM extends JFrame{
	
	private static final long serialVersionUID = 1L;

	
	
public IHM(CameraManager cameraManager, ICWorld world, Camera systemCamera, Client client) {
		
		// Frame
		setTitle("EVC");
		setSize(1024, 768);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		// Top panel
		JPanel topPanel = new JPanel();
		topPanel.setSize(new Dimension(1024, 40));
		topPanel.setLayout(null);
		topPanel.add(new ViewPanel(world, cameraManager));
		topPanel.add(new NavigationModePanel(cameraManager));
		topPanel.add(new CameraPanel(cameraManager, systemCamera));
		add(topPanel);
		
		// Center panel
		JPanel centerPanel = new JPanel();
		centerPanel.setSize(1024, 768 - 200- 40);
		centerPanel.setLocation(0, 30);
		centerPanel.setLayout(new BorderLayout());	// Need for the visualisation of the canvas on Windows
		centerPanel.add(world.getPresentation().getCanvas());
		add(centerPanel);
		
		// Bottom panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setSize(1024, 200);
		bottomPanel.setLocation(0, 768 - 200);
		bottomPanel.setLayout(null);
		bottomPanel.add(new CustomPanel());
		bottomPanel.add(new CommandPanel(client));
		add(bottomPanel);
		
		setVisible(true);
	}

	public IHM(ICWorld world, Camera systemCamera) {
		
		// Frame
		setTitle("EVC");
		setSize(1024, 768);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		// Camera Manager
		CameraManager cameraManager = new CameraManager(world.getPresentation().getWorldTransform());
		cameraManager.changeCamera(systemCamera);
		
		// Top panel
		JPanel topPanel = new JPanel();
		topPanel.setSize(new Dimension(1024, 40));
		topPanel.setLayout(null);
		topPanel.add(new ViewPanel(world, cameraManager));
		topPanel.add(new NavigationModePanel(cameraManager));
		topPanel.add(new CameraPanel(cameraManager, systemCamera));
		add(topPanel);
		
		// Center panel
		JPanel centerPanel = new JPanel();
		centerPanel.setSize(1024, 768 - 200- 40);
		centerPanel.setLocation(0, 30);
		centerPanel.setLayout(new BorderLayout());	// Need for the visualisation of the canvas on Windows
		centerPanel.add(world.getPresentation().getCanvas());
		add(centerPanel);
		
		// Bottom panel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setSize(1024, 200);
		bottomPanel.setLocation(0, 768 - 200);
		bottomPanel.setLayout(null);
		bottomPanel.add(new CustomPanel());
		bottomPanel.add(new CommandPanel(null));
		add(bottomPanel);
		
		setVisible(true);
	}
}
