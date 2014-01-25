package project;

import java.rmi.RemoteException;

public class LauncherClient1 {

	public static void main(String[] args) throws RemoteException {
		
		String worldName = "Demo03Camera.xml";
		
		
		new Server(worldName, "William");
		new Client(worldName, "192.168.1.50", "Domestique 1");
		new Client(worldName, "192.168.1.50", "Domestique 2");
	}
}
