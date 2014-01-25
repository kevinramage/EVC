package fr.istic.evc.graphic2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class NavigationModePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	protected JButton btnExamine, btnWalk, btnFly;
	
	
	public NavigationModePanel(CameraManager cameraManager) {
		
		// Configuration
		setLayout(null);
		setSize(120, 40);
		setLocation(160, 0);
		
		// Commandes
		addExamineBtn();
		addWalkBtn();
		addFlyBtn();
		
		// Listeners
		JButton [] btnsNavigationMode = new JButton[] { btnExamine, btnWalk, btnFly };
		NavigationModeListener examineListener = new NavigationModeListener(cameraManager, new ExamineMode(), 0);
		examineListener.setNavigationModeButtons(btnsNavigationMode);
		NavigationModeListener walkListener = new NavigationModeListener(cameraManager, new WalkMode(), 1);
		walkListener.setNavigationModeButtons(btnsNavigationMode);
		NavigationModeListener flyListener = new NavigationModeListener(cameraManager, new FlyMode(), 2);
		flyListener.setNavigationModeButtons(btnsNavigationMode);
		btnExamine.addActionListener(examineListener);
		btnWalk.addActionListener(walkListener);
		btnFly.addActionListener(flyListener);		
	}
	
	
	private void addExamineBtn() {
		btnExamine = new JButton();
		btnExamine.setSize(40, 40);
		btnExamine.setIcon(new ImageIcon("resources/image/examine2.png"));
		btnExamine.setToolTipText("Examine");
		add(btnExamine);
	}
	
	private void addWalkBtn() {
		btnWalk = new JButton();
		btnWalk.setSize(40, 40);
		btnWalk.setLocation(40, 0);
		btnWalk.setIcon(new ImageIcon("resources/image/walk.png"));
		btnWalk.setToolTipText("Walk");
		add(btnWalk);
	}
	
	private void addFlyBtn() {
		btnFly = new JButton();
		btnFly.setSize(40, 40);
		btnFly.setLocation(80, 0);
		btnFly.setIcon(new ImageIcon("resources/image/fly.png"));
		btnFly.setToolTipText("Fly");
		add(btnFly);
	}
}
