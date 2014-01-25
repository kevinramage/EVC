package fr.istic.evc.graphic2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CameraListener implements ActionListener {

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
}