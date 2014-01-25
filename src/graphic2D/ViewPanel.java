package graphic2D;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import object3D.controller.interfaces.ICWorld;
import graphic2D.ViewListener.VIEW_TYPE;


public class ViewPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	
	
	public ViewPanel(ICWorld world, CameraManager cameraManager) {
		
		// Configuration
		setLayout(null);
		setLocation(320, 0);
		setSize(240, 40);
		
		// Commandes
		addBackViewBtn(cameraManager);
		addFrontViewBtn(cameraManager);
		addBottomViewBtn(cameraManager);
		addTopViewBtn(cameraManager);
		addLeftViewBtn(cameraManager);
		addRightViewBtn(cameraManager);
	}
	
	private void addBackViewBtn(CameraManager cameraManager) {
		JButton btnBackView = new JButton();
		btnBackView.setSize(40, 40);
		btnBackView.setIcon(new ImageIcon("resources/image/backView.png"));
		btnBackView.setToolTipText("Back view");
		add(btnBackView);
		btnBackView.addActionListener(new ViewListener(cameraManager, VIEW_TYPE.BACK));
	}
	private void addFrontViewBtn(CameraManager cameraManager) {
		JButton btnFrontView = new JButton();
		btnFrontView.setLocation(40, 0);
		btnFrontView.setSize(40, 40);
		btnFrontView.setIcon(new ImageIcon("resources/image/frontView.png"));
		btnFrontView.setToolTipText("Front view");
		add(btnFrontView);	
		btnFrontView.addActionListener(new ViewListener(cameraManager, VIEW_TYPE.FRONT));
	}
	private void addBottomViewBtn(CameraManager cameraManager) {
		JButton btnBottomView = new JButton();
		btnBottomView.setLocation(80, 0);
		btnBottomView.setSize(40, 40);
		btnBottomView.setIcon(new ImageIcon("resources/image/bottomView.png"));
		btnBottomView.setToolTipText("Bottom view");
		add(btnBottomView);
		btnBottomView.addActionListener(new ViewListener(cameraManager, VIEW_TYPE.BOTTOM));
	}
	private void addTopViewBtn(CameraManager cameraManager) {
		JButton btnTopView = new JButton();
		btnTopView.setLocation(120, 0);
		btnTopView.setSize(40, 40);
		btnTopView.setIcon(new ImageIcon("resources/image/topView.png"));
		btnTopView.setToolTipText("Top view");
		add(btnTopView);
		btnTopView.addActionListener(new ViewListener(cameraManager, VIEW_TYPE.TOP));
	}
	private void addLeftViewBtn(CameraManager cameraManager) {
		JButton btnLeftView = new JButton();
		btnLeftView.setLocation(160, 0);
		btnLeftView.setSize(40, 40);
		btnLeftView.setIcon(new ImageIcon("resources/image/leftView.png"));
		btnLeftView.setToolTipText("Left view");
		add(btnLeftView);
		btnLeftView.addActionListener(new ViewListener(cameraManager, VIEW_TYPE.LEFT));
	}
	private void addRightViewBtn(CameraManager cameraManager) {
		JButton btnRightView = new JButton();
		btnRightView.setLocation(200, 0);
		btnRightView.setSize(40, 40);
		btnRightView.setIcon(new ImageIcon("resources/image/rightView.png"));
		btnRightView.setToolTipText("Right view");
		add(btnRightView);
		btnRightView.addActionListener(new ViewListener(cameraManager, VIEW_TYPE.RIGHT));
	}
}
