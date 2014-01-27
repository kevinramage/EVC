package project;

import java.rmi.RemoteException;

public class LauncherClient1 {

	public static void main(String[] args) throws RemoteException {
		
		//String ip = "148.60.5.129";
		String ip = "127.0.0.1";
		//String worldName = "Demo01.xml";
		//String worldName = "Demo02Elastic.xml";
		//String worldName = "Demo03Camera.xml";
		//String worldName = "Demo04Clone.xml";
		//String worldName = "Demo05VRML.xml";
		String worldName = "Demo06ElasticWilliam.xml";
		
		
		new Server(worldName, "William");
		new Client(worldName, ip, "Domestique 1");
		//new Client(worldName, ip, "Domestique 2");
		//new Client(worldName, ip, "Domestique 2");
	}
}
