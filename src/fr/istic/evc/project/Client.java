package fr.istic.evc.project;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import fr.istic.evc.device.Mouse;
import fr.istic.evc.graphic2D.Camera;
import fr.istic.evc.graphic2D.IHM;
import fr.istic.evc.network.MulticastReceiver;
import fr.istic.evc.object3D.base.controller.CAmbientLight;
import fr.istic.evc.object3D.base.controller.CDirectionalLight;
import fr.istic.evc.object3D.base.controller.CWorld;
import fr.istic.evc.object3D.base.controller.interfaces.ICAmbientLight;
import fr.istic.evc.object3D.base.controller.interfaces.ICDirectionalLight;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;


public class Client {
	
	private static final String hostName = "127.0.0.1";
	private static final int port = 1234;
	private static final String serverName = "williamServer";
	// ---------------------------------------------------------
	// 						Attributes
	// ---------------------------------------------------------
	ICWorld world;
	ICObject box1;
	Camera systemCamera;
    IServer is ;
    IServer clone ;
    MulticastReceiver receiver ;
	

	
	// ---------------------------------------------------------
	// 						Constructor
	// ---------------------------------------------------------
	public Client(String title) throws RemoteException {
		
		// World
		world = new CWorld();
		world.addDevice(new Mouse());
		world.show();
		
		// Ambient light 1
		ICAmbientLight ambientLight1 = new CAmbientLight();
		ambientLight1.setId("ambientLight1");
		ambientLight1.setAmbientColor(new Color3f(0.2f, 0.2f, 0.2f));
		world.add(ambientLight1);

		// Directional light 1
		ICDirectionalLight directionalLight1 = new CDirectionalLight();
		directionalLight1.setId("directionalLight1");
		world.add(directionalLight1);
		
		try {
            is = (IServer)Naming.lookup ("//" + hostName + ":" + port + "/" + serverName) ;
            receiver = new MulticastReceiver (world, is.getDiffusionGroupName (), is.getDiffusionPort ()) ;
        	receiver.start () ;
        } catch (Exception e) {
            System.out.println ("There is a problem with the server") ;
            e.printStackTrace () ;
            System.exit (1) ;
        }
		

		// System Camera
		systemCamera = new Camera();
		Transform3D transform3D = new Transform3D();
		transform3D.setTranslation(new Vector3d(0, 0, 30));
		systemCamera.setTransform3D(transform3D);
		
		IHM ihm = new IHM(world, systemCamera);
		ihm.setTitle(title);
		
		List<ICObject> objs = this.getObjects();
		for ( ICObject o : objs) {
			world.add(o);
		}
		
		// Start the creation of the world
		is.reSend();
	}

	// ---------------------------------------------------------
	// 						Methods
	// ---------------------------------------------------------
	private List<ICObject> getObjects() {
		//IServer o = new Server();
		//return o.getObjects();
		return new ArrayList<ICObject>();
	}
	

}
