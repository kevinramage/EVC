package fr.istic.evc.graphic2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.vecmath.Color3f;

import fr.istic.evc.object3D.base.abstraction.AObject;
import fr.istic.evc.object3D.base.abstraction.I_AObject;
import fr.istic.evc.object3D.base.controller.CObject;
import fr.istic.evc.project.Client;

public class CommandPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private Client client;
	
	public CommandPanel(Client client) {
		
		this.client = client;
		
		// Configuration
		setSize(1024-250-200, 200);
		setLocation(200, 0);
		setLayout(null);
		
		// Buttons
		addSelectBtn();
		addTranslateBtn();
		addRotateBtn();
		addZoomInBtn();
		addZoomOutBtn();
		addBtn();
		deleteBtn();
		addCubeBtn();
		addSphereBtn();
		addTriangleBtn();
	}
	
	private void addSelectBtn() {
		JButton btnSelect = new JButton();
		btnSelect.setSize(40, 40);
		btnSelect.setLocation(20, 10);
		btnSelect.setIcon(new ImageIcon("resources/image/select.png"));
		btnSelect.setToolTipText("Select");
		add(btnSelect);
	}
	private void addTranslateBtn() {
		JButton btnTranslate = new JButton();
		btnTranslate.setSize(40, 40);
		btnTranslate.setLocation(70, 10);
		btnTranslate.setIcon(new ImageIcon("resources/image/translate.png"));
		btnTranslate.setToolTipText("Translate");
		add(btnTranslate);			
	}
	private void addRotateBtn() {
		JButton btnRotate = new JButton();
		btnRotate.setSize(40, 40);
		btnRotate.setLocation(120, 10);
		btnRotate.setIcon(new ImageIcon("resources/image/rotate.png"));
		btnRotate.setToolTipText("Rotate");
		add(btnRotate);
	}
	private void addZoomInBtn() {
		JButton btnZoomIn = new JButton();
		btnZoomIn.setSize(40, 40);
		btnZoomIn.setLocation(170, 10);
		btnZoomIn.setIcon(new ImageIcon("resources/image/zoomIn.png"));
		btnZoomIn.setToolTipText("Zoom in");
		add(btnZoomIn);	
	}
	private void addZoomOutBtn() {
		JButton btnZoomOut = new JButton();
		btnZoomOut.setSize(40, 40);
		btnZoomOut.setLocation(220, 10);
		btnZoomOut.setIcon(new ImageIcon("resources/image/zoomOut.png"));
		btnZoomOut.setToolTipText("Zoom out");
		add(btnZoomOut);	
	}
	private void addBtn() {
		JButton btnAdd = new JButton();
		btnAdd.setSize(40, 40);
		btnAdd.setLocation(270, 10);
		btnAdd.setIcon(new ImageIcon("resources/image/add.png"));
		btnAdd.setToolTipText("Add");
		add(btnAdd);
	}
	private void deleteBtn() {
		JButton btnDelete = new JButton();
		btnDelete.setSize(40, 40);
		btnDelete.setLocation(320, 10);
		btnDelete.setIcon(new ImageIcon("resources/image/delete.png"));
		btnDelete.setToolTipText("Delete");
		add(btnDelete);
	}
	private void addCubeBtn() {
		JButton btnCube = new JButton();
		btnCube.setSize(40, 40);
		btnCube.setLocation(60, 60);
		btnCube.setIcon(new ImageIcon("resources/image/cube.png"));
		btnCube.setToolTipText("Cube");
		add(btnCube);
		
		btnCube.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				I_AObject obj = new AObject();
				obj.setGeometry("cube");
				obj.setAmbientColor(new Color3f(1.0f, 0.0f, 0.0f));
				client.createObject(new CObject(obj));
			}
		});
	}
	private void addSphereBtn() {
		JButton btnSphere = new JButton();
		btnSphere.setSize(40, 40);
		btnSphere.setLocation(120, 60);
		btnSphere.setIcon(new ImageIcon("resources/image/sphere.png"));
		btnSphere.setToolTipText("Sphere");
		add(btnSphere);
		btnSphere.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				I_AObject obj = new AObject();
				obj.setGeometry("sphere");
				obj.setAmbientColor(new Color3f(1.0f, 0.0f, 0.0f));
				client.createObject(new CObject(obj));
			}
		});
	}
	private void addTriangleBtn() {
		JButton btnTriangle = new JButton();
		btnTriangle.setSize(40, 40);
		btnTriangle.setLocation(180, 60);
		btnTriangle.setIcon(new ImageIcon("resources/image/triangle.png"));
		btnTriangle.setToolTipText("Triangle");
		add(btnTriangle);
	}
}
