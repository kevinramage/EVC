package fr.istic.evc.graphic2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CameraPanel extends JPanel{

	private static final long serialVersionUID = 1L;

	protected JButton btnSystem;
	
	public CameraPanel(CameraManager cameraManager, Camera systemCamera) {
		
		// Configuration
		setSize(160, 40);
		setLocation(0, 0);
		setLayout(null);
		
		// Commandes
		addSystemCameraBtn();
		
		// Listeners
		JButton [] btnsCamera = new JButton[] { btnSystem };
		CameraListener systemCameraListener = new CameraListener(cameraManager, systemCamera, 0);
		systemCameraListener.setCameraButtons(btnsCamera);
		CameraListener user1CameraListener = new CameraListener(cameraManager, new Camera(), 1);
		user1CameraListener.setCameraButtons(btnsCamera);
		CameraListener user2CameraListener = new CameraListener(cameraManager, new Camera(), 2);
		user2CameraListener.setCameraButtons(btnsCamera);
		btnSystem.addActionListener(systemCameraListener);	
	}
	
	private void addSystemCameraBtn() {
		btnSystem = new JButton();
		btnSystem.setSize(40, 40);
		btnSystem.setLocation(0, 0);
		btnSystem.setIcon(new ImageIcon("resources/image/system_2.png"));
		btnSystem.setToolTipText("System camera");
		add(btnSystem);
	}
	
}
