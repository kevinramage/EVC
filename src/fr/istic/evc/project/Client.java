package fr.istic.evc.project;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import javax.media.j3d.Transform3D;
import javax.vecmath.Vector3d;

import fr.istic.evc.Command.CmdCreateCObject;
import fr.istic.evc.Command.I_Command;
import fr.istic.evc.Command.I_CreateCommand;
import fr.istic.evc.device.Mouse;
import fr.istic.evc.graphic2D.Camera;
import fr.istic.evc.graphic2D.CameraManager;
import fr.istic.evc.graphic2D.IHM;
import fr.istic.evc.network.MulticastReceiverCreate;
import fr.istic.evc.network.MulticastReceiverUpdate;
import fr.istic.evc.object3D.base.abstraction.I_AObject;
import fr.istic.evc.object3D.base.controller.CWorld;
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
	private int id;
	private ICWorld world;
	private Camera systemCamera;
    private IServer is ;
    private MulticastReceiverUpdate multUpdate;
    private MulticastReceiverCreate multCreate;
    
    private int compteur;
	

	
	// ---------------------------------------------------------
	// 						Constructor
	// ---------------------------------------------------------
	public Client(String title) throws RemoteException {
		
		this.title = title;
		
		try {
            is = (IServer)Naming.lookup ("//" + hostName + ":" + port + "/" + serverName) ;
            id = is.obtainID();
        	multUpdate = new MulticastReceiverUpdate(this, is.getDiffusionGroupName(), is.getUpdatePort());
        	multUpdate.start();
        	multCreate = new MulticastReceiverCreate(this, is.getDiffusionGroupName(), is.getCreatePort());
        	multCreate.start();
        	compteur = 0;
        	
        } catch (Exception e) {
            e.printStackTrace () ;
            System.exit (1) ;
        }
		
		// World
		world = new CWorld();
		world.show();

		// System Camera
		systemCamera = new Camera();
		Transform3D transform3D = new Transform3D();
		transform3D.setTranslation(new Vector3d(0, 0, 30));
		systemCamera.setTransform3D(transform3D);
		
		// Camera Manager
		CameraManager cameraManager = new CameraManager(world.getPresentation().getWorldTransform());
		cameraManager.changeCamera(systemCamera);
		
		// Device
		Mouse mouse = new Mouse();
		mouse.setCameraManager(cameraManager);
		world.addDevice(mouse);
		
		// IHM
		IHM ihm = new IHM(cameraManager, world, systemCamera, this);
		ihm.setTitle(title);
		
		this.recuperateObjects();
	}

	// ---------------------------------------------------------
	// 						Methods
	// ---------------------------------------------------------
//	private List<ICObject> getObjects() {
//		//IServer o = new Server();
//		//return o.getObjects();
//		return new ArrayList<ICObject>();
//	}
	
	/**
	 * Build world from server's objects
	 * @throws RemoteException
	 */
	private void recuperateObjects() throws RemoteException {
		List<I_CreateCommand> lc = is.getListObjs();
		for (I_CreateCommand cmd: lc) {
			cmd.execute(world, this);
		}
	}

	public void changed(I_Command cmd) {
		try {
			is.sendCommand(cmd);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void addObject(I_CreateCommand cmd) {
		cmd.execute(world, this);
	}
	
	public List<ICObject> getObjects() {
		return world.getObjects();
	}
	

	@Override
	public boolean isServer() {
		return false;
	}

	public void createObject(I_AObject abstraction) {
		compteur++;
		System.out.println("Client.createObject()");
		System.out.println("Compteur : "+compteur);
		abstraction.setId(""+compteur+"-"+id);
		try {
			is.addObject(new CmdCreateCObject(abstraction));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
