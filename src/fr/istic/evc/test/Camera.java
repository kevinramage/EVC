package fr.istic.evc.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
public class Camera {
	
	private Transform3D transform3D;
	
	public Camera() {
		transform3D = new Transform3D();
	}
	
	public Transform3D getTransform3D() {
		return transform3D;
	}
	public void setTransform3D(Transform3D value) {
		transform3D = value;
	}
}

class CameraManager {
	private Camera camera;
	private NavigationMode navigationMode;
	private TransformGroup transformGroup;
	
	public CameraManager(TransformGroup transformGroup) {
		this.transformGroup = transformGroup;
	}
	
	public void changeCamera(Camera camera) {
		this.camera = camera;
		transformGroup.setTransform(camera.getTransform3D());
	}
	public void changeNavigationMode(NavigationMode navigationMode) {
		this.navigationMode = navigationMode;
	}
	public void translateX(int x) {
		navigationMode.translateX(transformGroup, x);
		Transform3D transform3D = new Transform3D();
		transformGroup.getTransform(transform3D);
		camera.setTransform3D(transform3D);
	}
	public void translateY(int y) {
		navigationMode.translateY(transformGroup, y);
		Transform3D transform3D = new Transform3D();
		transformGroup.getTransform(transform3D);
		camera.setTransform3D(transform3D);
	}
	public void translateZ(int z) {
		navigationMode.translateZ(transformGroup, z);
		Transform3D transform3D = new Transform3D();
		transformGroup.getTransform(transform3D);
		camera.setTransform3D(transform3D);
	}
}

class CameraListener implements ActionListener {

	private CameraManager cameraActive;
	private Camera camera;
	private JButton [] btns;
	private int btnIndex;
	private String [] btnsName = new String[] {
			"resources/image/system",
			"resources/image/user1",
			"resources/image/user2",
			"resources/image/user3",
			"resources/image/user4",
			"resources/image/user5",
			"resources/image/user6",
			"resources/image/user7"
	};
	
	public CameraListener(CameraManager cameraActive, Camera camera, int index) {
		this.cameraActive = cameraActive;
		this.camera = camera;
		this.btnIndex = index;
	}
	
	public void setCameraButtons(JButton [] btns) {
		this.btns = btns;
	}
	
	private void clearButtons() {
		for(int i = 0; i < btns.length; i++) {
			btns[i].setIcon(new ImageIcon(btnsName[i] + ".png" ));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		clearButtons();
		cameraActive.changeCamera(camera);
		btns[btnIndex].setIcon(new ImageIcon(btnsName[btnIndex] + "_2.png"));
	}
	
}*/