package project;

import factory.WorldBuilder;
import graphic2D.Camera;
import graphic2D.CameraManager;
import graphic2D.IHM;

import java.awt.Color;
import java.io.File;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import network.MulticastReceiverCreate;
import network.MulticastReceiverDelete;
import network.MulticastReceiverUpdate;
import object3D.controller.CCamera;
import object3D.controller.CWorld;
import object3D.controller.interfaces.ICObject;
import object3D.controller.interfaces.ICWorld;

import command.I_Command;
import command.delete.CmdDeleteCObject;

import device.Mouse;



public class Client implements IEntity{

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
    private Color3f color;
   
    
    //TODO a supprimer
    public IHM ihm;
	

	
	// ---------------------------------------------------------
	// 						Constructor
	// ---------------------------------------------------------
	public Client(String worldName, String address, String title) throws RemoteException {
		
		this.title = title;
		
		try {
			
			// RMI
            is = (IServer)Naming.lookup ("//" + address + ":" + Configuration.RMI_PORT + "/" + Configuration.SERVER_NAME) ;
            
            // Id
            id = is.obtainID();
            
            // Color
            color = getColorsFromId();
            
            // Create
        	multCreate = new MulticastReceiverCreate(this, is.getDiffusionGroupName(), is.getCreatePort());
        	multCreate.start();
            
        	// Update
        	multUpdate = new MulticastReceiverUpdate(this, is.getDiffusionGroupName(), is.getUpdatePort());
        	multUpdate.start();

        	// Delete
        	multDelete = new MulticastReceiverDelete(this, is.getDiffusionGroupName(), is.getDeletePort());
        	multDelete.start();
        	
        } catch (Exception e) {
            e.printStackTrace () ;
            System.exit (1) ;
        }
		
		// World
		world = new CWorld();

		// System Camera
		systemCamera = new Camera();
		Transform3D transform3D = new Transform3D();
		transform3D.setTranslation(new Vector3d(0, 0, 15));
		systemCamera.setTransform3D(transform3D);
		
		// Camera Manager
		CameraManager cameraManager = new CameraManager(world.getPresentation().getWorldTransform());
		cameraManager.changeCamera(systemCamera);
		world.setCameraManager(cameraManager);
		
		// Load iivc
		File file = new File("resources/iivc/" + worldName);
		if (file.exists()) {
			WorldBuilder.getInstance().load(world, "resources/iivc/" + worldName, this);
		}
		// World
		world.show();
		
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
		
		// Camera
		CCamera camera = (CCamera) world.getObjectById(getId() + "-camera");
		if ( camera != null) {
			camera.setAmbientColor(color);
			camera.setManager(cameraManager);
		}
			
		// Presentation Camera
		/*
		ICObject camera = new CCamera(cameraManager);
		camera.setEntity(this);
		camera.updatePosition(new Vector3d(0, 0, 20));
		camera.setPickable(true);
//		camera.updateAmbientColor(getCameraColor());
		camera.updateAmbientColor(new Color3f(Color.orange));
		createObject(camera);
		*/
		
	}


	// ---------------------------------------------------------
	// 						Methods
	// ---------------------------------------------------------
	
	private static Color3f [] colors = new Color3f[] {
		new Color3f(Color.red), 
		new Color3f(Color.blue),
		new Color3f(Color.green)
	};
	
	private Color3f getColorsFromId() {
		return colors[id];
	}

	/**
	 * Build world from server's objects
	 * @throws RemoteException
	 */
	private void recuperateObjects() throws RemoteException {
		List<I_Command> lc = is.getListObjs();
		for (I_Command cmd: lc) {
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
	
	public List<ICObject> getObjects() {
		return world.getObjects();
	}
	

	@Override
	public boolean isServer() {
		return false;
	}

	public void createObject(ICObject controller) {
		controller.setId(id + "-" + controller.getId());
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
	
	public int getId(){
		return id;
	}

	@Override
	public void addObjectInWorld(ICWorld world, ICObject obj) {
		createObject(obj);
	}

	@Override
	public void broadCastAddCommand(I_Command cmd) {
		try {
			is.sendCommand(cmd);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void broadCastUpdateCommand(I_Command cmd) {
		try {
			is.sendCommand(cmd);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void broadCastDeleteCommand(I_Command cmd) {
		try {
			is.sendCommand(cmd);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
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
			e.printStackTrace();
		}
	}
	
	public void removeObjects(I_Command cmdDelete) {
		cmdDelete.execute(this);
	}
	
	public void removeClone(I_Command cmdDelete){
		try {
			is.removeObjects(cmdDelete);
		} catch (RemoteException e) {
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


	@Override
	public void removeSeletedObjects() {
		
		// Prepare command
		CmdDeleteCObject cmd = new CmdDeleteCObject();
		List<ICObject> objs = world.getObjects();
		for ( ICObject obj : objs) {
			if ( obj.isSelected()) {
				cmd.addObjectToRemove(obj);
			}
		}
		
		// Send command
		try {
			is.sendCommand(cmd);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void addObject(I_Command cmd) {
		cmd.execute(this);
	}


}
