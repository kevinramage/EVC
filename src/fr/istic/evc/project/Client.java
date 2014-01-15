package fr.istic.evc.project;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import fr.istic.evc.Command.Command;
import fr.istic.evc.device.Mouse;
import fr.istic.evc.graphic2D.Camera;
import fr.istic.evc.graphic2D.IHM;
import fr.istic.evc.network.MulticastReceiverCreate;
import fr.istic.evc.network.MulticastReceiverUpdate;
import fr.istic.evc.object3D.base.controller.CAmbientLight;
import fr.istic.evc.object3D.base.controller.CDirectionalLight;
import fr.istic.evc.object3D.base.controller.CWorld;
import fr.istic.evc.object3D.base.controller.interfaces.ICAmbientLight;
import fr.istic.evc.object3D.base.controller.interfaces.ICDirectionalLight;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;


public class Client implements IEntity{
	
	private static final String hostName = "127.0.0.1";
	private static final int port = 1234;
	private static final String serverName = "williamServer";
	// ---------------------------------------------------------
	// 						Attributes
	// ---------------------------------------------------------
	public String title;
	ICWorld world;
	ICObject box1;
	Camera systemCamera;
    IServer is ;
    IServer clone ;
    MulticastReceiverCreate multCreate ;
    MulticastReceiverUpdate multUpdate;
	

	
	// ---------------------------------------------------------
	// 						Constructor
	// ---------------------------------------------------------
	public Client(String title) throws RemoteException {
		
		this.title = title;
		
		try {
            is = (IServer)Naming.lookup ("//" + hostName + ":" + port + "/" + serverName) ;
            multCreate = new MulticastReceiverCreate (this, is.getDiffusionGroupName (), is.getCreatePort ()) ;
        	multCreate.start () ;
        	multUpdate = new MulticastReceiverUpdate(this, is.getDiffusionGroupName(), is.getUpdatePort());
        	multUpdate.start();
        	
        } catch (Exception e) {
            System.out.println ("There is a problem with the server") ;
            e.printStackTrace () ;
            System.exit (1) ;
        }
		
		// World
		world = new CWorld();
		world.addDevice(new Mouse());
		world.show();

		// Ambient light 1
		ICAmbientLight ambientLight1 = new CAmbientLight();
		ambientLight1.setEntity(this);
		ambientLight1.setId("ambientLight1");
		ambientLight1.updateAmbientColor(new Color3f(0.2f, 0.2f, 0.2f));
		world.add(ambientLight1);

		// Directional light 1
		ICDirectionalLight directionalLight1 = new CDirectionalLight();
		directionalLight1.setEntity(this);
		directionalLight1.setId("directionalLight1");
		world.add(directionalLight1);

		// System Camera
		systemCamera = new Camera();
		Transform3D transform3D = new Transform3D();
		transform3D.setTranslation(new Vector3d(0, 0, 30));
		systemCamera.setTransform3D(transform3D);
		
		IHM ihm = new IHM(world, systemCamera);
		ihm.setTitle(title);
		
		// Start the creation of the world
		is.reSend();
	}

	// ---------------------------------------------------------
	// 						Methods
	// ---------------------------------------------------------
//	private List<ICObject> getObjects() {
//		//IServer o = new Server();
//		//return o.getObjects();
//		return new ArrayList<ICObject>();
//	}

	public void changed(Command cmd) {
		System.out.println("Client.changed()");
		System.out.println(title + " - Etape 2: ");
		try {
			is.sendCommand(cmd);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void addObject(ICObject controller) {
		world.add(controller);
		controller.setEntity(this);
	}
	
	public List<ICObject> getObjects() {
		return world.getObjects();
	}
	

	@Override
	public boolean isServer() {
		return false;
	}
	

}
