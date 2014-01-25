package project;

import java.awt.Color;
import java.io.File;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import object3D.controller.CCamera;
import object3D.controller.CWorld;
import object3D.controller.interfaces.ICObject;
import object3D.controller.interfaces.ICWorld;
import network.MulticastReceiverCreate;
import network.MulticastReceiverUpdate;
import command.I_Command;
import command.create.I_CreateCommand;
import device.Mouse;
import factory.WorldBuilder;
import graphic2D.Camera;
import graphic2D.CameraManager;
import graphic2D.IHM;


public class Client implements IEntity{

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
    private Color3f color;
	

	
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
		IHM ihm = new IHM(world, systemCamera, this);
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
	
	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void addObjectInWorld(ICWorld world, ICObject obj) {
		createObject(obj);
	}

	@Override
	public void broadCastUpdateCommand(I_Command cmd) {
		try {
			is.sendCommand(cmd);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	
}
