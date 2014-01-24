package fr.istic.evc.project;

import java.rmi.RemoteException;

public class Launcher {

	public static void main(String[] args) throws RemoteException {
		new Server("William");
		new Client("Domestique 1").ihm.setLocation(0, 0);
		new Client("Domestique 2").ihm.setLocation(800, 0);
		new Client("Domestique 3").ihm.setLocation(400, 400);
	}
}
