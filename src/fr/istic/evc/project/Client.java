package fr.istic.evc.project;

import java.awt.Color;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import fr.istic.evc.Command.CmdDeleteCObject;
import fr.istic.evc.Command.I_Command;
import fr.istic.evc.Command.I_CreateCommand;
import fr.istic.evc.device.Mouse;
import fr.istic.evc.graphic2D.Camera;
import fr.istic.evc.graphic2D.CameraManager;
import fr.istic.evc.graphic2D.IHM;
import fr.istic.evc.network.MulticastReceiverCreate;
import fr.istic.evc.network.MulticastReceiverDelete;
import fr.istic.evc.network.MulticastReceiverUpdate;
import fr.istic.evc.object3D.base.controller.CCamera;
import fr.istic.evc.object3D.base.controller.CWorld;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;


public class Client implements IEntity{

	private static final String hostName = "127.0.0.1";
//	private static final String hostName = "148.60.5.129";
	private static final int port = 1234;
	private static final String serverName = "williamServer";
	// ---------------------------------------------------------
	// 						Attributes
	// ---------------------------------------------------------
	private String title;
	private int id;
	private ICWorld world;
	private Camera systemCamera;
	
    private IServer is ;
    private MulticastReceiverUpdate multUpdate;
    private MulticastReceiverCreate multCreate;
    private MulticastReceiverDelete multDelete;
    
    private int compteur;
    
    //TODO a supprimer
    public IHM ihm;
	

	
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
        	multDelete = new MulticastReceiverDelete(this, is.getDiffusionGroupName(), is.getDeletePort());
        	multDelete.start();
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
		transform3D.setTranslation(new Vector3d(0, 0, 15));
		systemCamera.setTransform3D(transform3D);
		
		// Camera Manager
		CameraManager cameraManager = new CameraManager(world.getPresentation().getWorldTransform());
		cameraManager.changeCamera(systemCamera);
		
		// Ajout du Camera Manager dans le cworld
		world.setCameraManager(cameraManager);
		
		// Device
		Mouse mouse = new Mouse();
		mouse.setCameraManager(cameraManager);
		world.addDevice(mouse);
		
		// IHM
//		IHM ihm = new IHM(world, systemCamera, this);
		ihm = new IHM(world, systemCamera, this);
		ihm.setTitle(title);
		
		// Get server object
		recuperateObjects();
		
		// Presentation Camera
		ICObject camera = new CCamera(cameraManager);
		camera.setEntity(this);
		camera.updatePosition(new Vector3d(0, 0, 20));
		camera.setPickable(true);
//		camera.updateAmbientColor(getCameraColor());
		camera.updateAmbientColor(new Color3f(Color.orange));
		createObject(camera);
		
	}

//	private Color3f getCameraColor() {
//		Color3f [] colors = new Color3f [] { new Color3f(Color.gray), new Color3f(Color.green), new Color3f(Color.yellow) }; 
//		return colors[id % colors.length];
//	}

	// ---------------------------------------------------------
	// 						Methods
	// ---------------------------------------------------------
	
	/**
	 * Build world from server's objects
	 * @throws RemoteException
	 */
	private void recuperateObjects() throws RemoteException {
		List<I_CreateCommand> lc = is.getListObjs();
		for (I_CreateCommand cmd: lc) {
			cmd.execute(this);
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
		cmd.execute(this);
	}
	
	public List<ICObject> getObjects() {
		return world.getObjects();
	}
	

	@Override
	public boolean isServer() {
		return false;
	}

	public void createObject(ICObject controller) {
		compteur++;
		controller.setId(id+"-"+compteur);
		try {
			is.addObject(controller.getCreateCommand());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	

	@Override
	public ICWorld getWorld() {
		return this.world;
	}
	
	
	public int getId() {
		return this.id;
	}

	public void removeObjects() {
		I_Command cmdDelete = new CmdDeleteCObject();
		for (ICObject obj : world.getObjects()) {
			if (obj.isSelected())
				((CmdDeleteCObject)cmdDelete).addObjectToRemove(obj);
		}
		try {
			is.removeObjects(cmdDelete);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void removeObjects(I_Command cmdDelete) {
		cmdDelete.execute(this);
	}
	
	public void removeClone(I_Command cmdDelete) {
		try {
			is.removeObjects(cmdDelete);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Override
	public boolean havePick(ICObject obj) {
		return world.getDevices().contains(obj);
	}

	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public void showAllObjects() {
		String str = "\n"+this.getTitle()+"\n";
		str += "Liste des Objets : \n";
		for (ICObject o:this.getWorld().getObjects()) {
			str += "\t - "+o.getId()+" de type : "+o.getClass()+"\n";
		}
		
		System.err.println(str);
	}	
}
