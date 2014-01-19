package fr.istic.evc.project;

import java.rmi.RemoteException;

public class Launcher {

	public static void main(String[] args) throws RemoteException {
		new Server("William");
		new Client("Domestique 1");
		//new Client("Domestique 2");
	}
}
