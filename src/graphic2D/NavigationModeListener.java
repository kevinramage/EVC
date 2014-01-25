package fr.istic.evc.graphic2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class NavigationModeListener implements ActionListener {

	private NavigationMode navigationMode;
	private CameraManager cameraActive;
	private int btnIndex;
	private JButton [] btns;
	private String [] btnsName = new String [] { 	
			"resources/image/examine",
			"resources/image/walk",
			"resources/image/fly"
	};
	                              		
	
	public NavigationModeListener(CameraManager cameraActive, NavigationMode navigationMode, int index) {
		this.navigationMode = navigationMode;
		this.cameraActive = cameraActive;
		this.btnIndex = index;
	}
	
	public void setNavigationModeButtons(JButton [] btns) {
		this.btns = btns;
	}
	
	private void clearButtons() {
		for ( int i = 0; i < btns.length; i++){
			btns[i].setIcon(new ImageIcon(btnsName[i] + ".png"));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		clearButtons();
		cameraActive.changeNavigationMode(navigationMode);
		btns[btnIndex].setIcon(new ImageIcon(btnsName[btnIndex] + "2.png"));
	}
}