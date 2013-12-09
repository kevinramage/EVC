package fr.istic.evc.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import fr.istic.evc.interaction.command.MoveLeft;

/**
 * Vue pour l'interaction de déplacement (CustomArea)
 * @author 27001623
 *
 */
public class MoveView extends JPanel {
	private JButton btnLeft, btnRight, btnUp, btnDown, btnCenter;
	
	public MoveView() {
		this.setLayout(new BorderLayout());
		
		createLeft();
		createRight();
		createUp();
		createDown();
		createCenter();
		System.out.println("Move view constructor");
	}
	
	
	private void createLeft() {
		btnLeft = new JButton(new ImageIcon("resources/image/left.png"));
//		btnLeft.setPreferredSize(new Dimension(30, 30));
//		btnLeft.setSize(30, 30);
		btnLeft.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MoveLeft().execute();
			}
		});
		this.add(btnLeft, BorderLayout.WEST);
	}
	
	private void createRight() {
		btnRight = new JButton(new ImageIcon("resources/image/right.png"));
//		btnRight.setPreferredSize(new Dimension(30, 30));
//		btnRight.setSize(30, 30);
		this.add(btnRight, BorderLayout.EAST);
	}
	
	
	private void createCenter() {
		btnCenter = new JButton(new ImageIcon("resources/image/center.png"));
//		btnCenter.setPreferredSize(new Dimension(30, 30));
//		btnCenter.setSize(30, 30);
		this.add(btnCenter, BorderLayout.CENTER);
	}
	
	private void createUp() {
		btnUp = new JButton(new ImageIcon("resources/image/top.png"));
//		btnUp.setPreferredSize(new Dimension(30, 30));
//		btnUp.setSize(30, 30);
		this.add(btnUp, BorderLayout.NORTH);
	}
	
	private void createDown() {
		btnDown = new JButton(new ImageIcon("resources/image/bottom.png"));
//		btnDown.setPreferredSize(new Dimension(30, 30));
//		btnDown.setSize(30, 30);
		this.add(btnDown, BorderLayout.SOUTH);
	}	
}
