package project;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.rmi.RemoteException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import network.ICallback;
import network.WorldListener;
import network.WorldSender;

public class Launcher extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel mainMenu, serverWorldSelectorMenu, clientWorldSelectorMenu;
	private String worldSelected = "";
	private DefaultListModel<String> model;
	private WorldListener listener;
	
	
	// Constructor
	public Launcher() {
		
		// Configure window
		setTitle("EVC");
		setSize(1024, 768);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		// Init menu
		mainMenu = getMainMenu();
		serverWorldSelectorMenu = getServerWorldSelector();
		clientWorldSelectorMenu = getClientWorldSelector();
		
		// Container
		getContentPane().add(mainMenu);
		
		setVisible(true);
	}
	
	
	
	
	// Main menu
	private JPanel getMainMenu() {
		
		// Container
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(1024, 768);
		
		// Lbl title
		JLabel lblTitle = new JLabel("EVC");
		lblTitle.setLocation(450, 50);
		lblTitle.setSize(100, 50);
		lblTitle.setFont(new Font("arial", Font.BOLD, 40));
		panel.add(lblTitle);
		
		// Btn create world
		JButton btnCreateWorld = new JButton("Create world");
		btnCreateWorld.setLocation(400, 300);
		btnCreateWorld.setSize(200, 75);
		btnCreateWorld.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				getContentPane().add(serverWorldSelectorMenu);
				validate();
				repaint();
			}
		});
		panel.add(btnCreateWorld);
		
		// Btn join world
		JButton btnJoinWorld = new JButton("Join world");
		btnJoinWorld.setLocation(400, 450);
		btnJoinWorld.setSize(200, 75);
		btnJoinWorld.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getServerAvailable();
				getContentPane().add(clientWorldSelectorMenu);
				validate();
				repaint();
			}
		});
		panel.add(btnJoinWorld);
		
		return panel;
	}
	
	// Server World selector menu
	private JPanel getServerWorldSelector() {
		
		// Window
		final JFrame window = this;
		
		// Container
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(1024, 768);		
		
		// Lbl title
		JLabel lblTitle = new JLabel("Select a world");
		lblTitle.setLocation(350, 50);
		lblTitle.setSize(500, 50);
		lblTitle.setFont(new Font("arial", Font.BOLD, 40));
		panel.add(lblTitle);
		
		// Read all world
		DefaultListModel<String> model = new DefaultListModel<String>();
		File folder = new File("resources/world");
		for ( File entry : folder.listFiles()) {
			model.addElement(entry.getName());
		}
		
		// Btn create
		final JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Server(worldSelected, "William");
					window.dispose();
					
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		btnCreate.setLocation(650, 600);
		btnCreate.setSize(200, 75);
		btnCreate.setEnabled(false);
		panel.add(btnCreate);
		
		// Btn back
		JButton btnBack = new JButton("Back");
		btnBack.setLocation(100, 600);
		btnBack.setSize(250, 75);
		panel.add(btnBack);
		
		// List
		final JList<String> lstWorld = new JList<String>(model);
		lstWorld.setLocation(280, 150);
		lstWorld.setSize(400, 400);
		lstWorld.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					if (lstWorld.getSelectedIndex() != -1) {
						btnCreate.setEnabled(true);
						worldSelected = lstWorld.getSelectedValue();
					} else {
						btnCreate.setEnabled(false);
					}
				}
			}
		});
		panel.add(lstWorld);
		

		
		
		return panel;
	}
	
	// Server world selector menu
	private JPanel getClientWorldSelector() {
		
		// Window
		final JFrame window = this;
		
		// Container
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(1024, 768);		
		
		// Lbl title
		JLabel lblTitle = new JLabel("Select a world");
		lblTitle.setLocation(350, 50);
		lblTitle.setSize(500, 50);
		lblTitle.setFont(new Font("arial", Font.BOLD, 40));
		panel.add(lblTitle);
		
		// Read all world
		model = new DefaultListModel<String>();
		
		// Btn refresh
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getServerAvailable();
			}
		});
		btnRefresh.setLocation(700, 200);
		btnRefresh.setSize(200, 75);
		panel.add(btnRefresh);
		
		// Btn join
		final JButton btnJoin = new JButton("Join");
		btnJoin.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String world = worldSelected.split("-")[0];
					String address = worldSelected.split("-")[1];
					listener.stop();
					new Client(world, address, "Domestique");
					window.dispose();
					
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		btnJoin.setLocation(650, 600);
		btnJoin.setSize(200, 75);
		panel.add(btnJoin);
		
		// Btn back
		JButton btnBack = new JButton("Back");
		btnBack.setLocation(100, 600);
		btnBack.setSize(250, 75);
		panel.add(btnBack);
		
		// List
		final JList<String> lstWorld = new JList<String>(model);
		lstWorld.setLocation(280, 150);
		lstWorld.setSize(400, 400);
		lstWorld.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					if (lstWorld.getSelectedIndex() != -1) {
						btnJoin.setEnabled(true);
						worldSelected = lstWorld.getSelectedValue();
					} else {
						btnJoin.setEnabled(false);
					}
				}
			}
		});
		panel.add(lstWorld);
		
		return panel;
	}
	
	
	
	
	// Send a request to know the server
	private void getServerAvailable() {

		// Worlds
		model.clear();
		
		// Start listener
		ICallback callback = new ICallback() {
			
			@Override
			public void call(Object data) {
				System.out
						.println("Launcher.getServerAvailable().new ICallback() {...}.call()");
				model.addElement(data.toString());
			}
		};
		listener = new WorldListener(Configuration.DIFFUSION_ADDRESS, Configuration.WORLD_RESPONSE_PORT, callback);
		listener.start();
		
		// Send request
		WorldSender sender = new WorldSender(Configuration.DIFFUSION_ADDRESS, Configuration.WORLD_REQUEST_PORT);
		sender.diffuseMessage("world please ?");
		
		System.out.println("Launcher.getServerAvailable()");
	}
	
	
	
	// Main
	public static void main(String[] args) throws RemoteException {
		new Launcher();
	}


}
