package project;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.Transform3D;
import javax.vecmath.Vector3d;

import network.ICallback;
import network.MulticastSender;
import network.WorldListener;
import network.WorldSender;
import object3D.controller.CWorld;
import object3D.controller.interfaces.ICObject;
import object3D.controller.interfaces.ICWorld;

import command.I_Command;

import device.Mouse;
import factory.WorldBuilder;
import graphic2D.Camera;
import graphic2D.CameraManager;
import graphic2D.IHM;

public class Server extends UnicastRemoteObject implements IServer, IEntity {
	
	private static final long serialVersionUID = 1L;
	private transient MulticastSender sender ;
	private ICWorld world;
	private String title;
	private int compteur;
	
	
	
	public Server(final String worldName, String title) throws RemoteException {
		
		// Title
		this.title = title;
		
		// Configure RMI object
		try {
            LocateRegistry.createRegistry (Configuration.RMI_PORT) ;
            Naming.rebind ("//" + Configuration.HOST_NAME + ":" + Configuration.RMI_PORT + "/" + Configuration.SERVER_NAME, this) ;
            sender = new MulticastSender (Configuration.DIFFUSION_ADDRESS, Configuration.CREATE_PORT, Configuration.UPDATE_PORT, Configuration.DELETE_PORT) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
		
		
		
		// IP
		String ipAddress = "";
		try {
			ipAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		final String ip = ipAddress;
		
		
		
		// Configure the world multicast receiver
		ICallback callback = new ICallback() {
			
			@Override
			public void call(Object data) {
				WorldSender sender = new WorldSender(Configuration.DIFFUSION_ADDRESS, Configuration.WORLD_RESPONSE_PORT);
				sender.diffuseMessage(worldName + "-" + ip);
			}
		};
		WorldListener listener = new WorldListener(Configuration.DIFFUSION_ADDRESS, Configuration.WORLD_REQUEST_PORT, callback);
		listener.start();
		
		// World
		world = new CWorld();
		WorldBuilder.getInstance().load(world, "resources/world/" + worldName, this);
		
		
		// System camera
		Camera systemCamera = new Camera();
		Transform3D transform3D = new Transform3D();
		transform3D.setTranslation(new Vector3d(0, 0, 20));
		systemCamera.setTransform3D(transform3D);
		
		// Camera Manager
		CameraManager cameraManager = new CameraManager(world.getPresentation().getWorldTransform());
		cameraManager.changeCamera(systemCamera);
		world.setCameraManager(cameraManager);
		
		// Devices
		Mouse mouse = new Mouse();
		mouse.setCameraManager(cameraManager);
		world.addDevice(mouse);
		
		// Show world
		world.show();
		
		// IHM
		IHM ihm = new IHM(world, systemCamera);
		ihm.setTitle(title);
	}

	@Override
	public String getDiffusionGroupName() throws RemoteException {
		return Configuration.DIFFUSION_ADDRESS ;
	}

	@Override
	public Integer getCreatePort() throws RemoteException {
		return new Integer (Configuration.CREATE_PORT);
	}

	@Override
	public Integer getUpdatePort() throws RemoteException {
		return new Integer(Configuration.UPDATE_PORT);
	}



	@Override
	public void sendCommand(I_Command cmd) {
		cmd.execute(this);
		sender.updateObject(cmd);
	}
	
	@Override
	public void addObject(I_Command cmd) throws RemoteException {
		broadCastAddCommand(cmd);
	}
	
	
	
	/* -------- Methodes Diffusion -------- */ 
		
	@Override
	public synchronized int obtainID() {
		return ++compteur;
	}
	
	
	@Override
	public List<I_Command> getListObjs() {
		List<ICObject> lo = world.getObjects();
		List<I_Command> lc = new ArrayList<>();
		for (ICObject o:lo) {
			lc.add(o.getCreateCommand());
		}
		return lc;
	}

	@Override
	public void update(I_Command cmd) {
		sender.updateObject(cmd);
	}
	
	@Override
	public boolean isServer() {
		return true;
	}

	
	@Override
	public ICWorld getWorld() {
		return this.world;
	}

	
	@Override
	public int getId() {
		return -1;
	}

	@Override
	public void addObjectInWorld(ICWorld world, ICObject obj) {
		world.add(obj);
	}

	@Override
	public void broadCastAddCommand(I_Command cmd) {
		cmd.execute(this);
		sender.createObject(cmd);
	}
	
	@Override
	public void broadCastUpdateCommand(I_Command cmd) {
		cmd.execute(this);
		sender.updateObject(cmd);
	}
	
	@Override
	public void broadCastDeleteCommand(I_Command cmd) {
		cmd.execute(this);
		sender.deleteObjects(cmd);
	}

	@Override
	public void removeObjects(I_Command cmdDelete) throws RemoteException {
		cmdDelete.execute(this);
		sender.deleteObjects(cmdDelete);
	}

	@Override
	public int getDeletePort() throws RemoteException {
		return Configuration.DELETE_PORT;
	}

	@Override
	public boolean havePick(ICObject obj) {
		return false;
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
		// TODO Auto-generated method stub
		
	}






}
