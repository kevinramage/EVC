package fr.istic.evc.project;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import fr.istic.evc.Command.I_Command;
import fr.istic.evc.Command.I_CreateCommand;
import fr.istic.evc.device.Mouse;
import fr.istic.evc.graphic2D.Camera;
import fr.istic.evc.graphic2D.IHM;
import fr.istic.evc.network.MulticastSender;
import fr.istic.evc.object3D.base.controller.CAmbientLight;
import fr.istic.evc.object3D.base.controller.CDirectionalLight;
import fr.istic.evc.object3D.base.controller.CObject;
import fr.istic.evc.object3D.base.controller.CWorld;
import fr.istic.evc.object3D.base.controller.interfaces.ICAmbientLight;
import fr.istic.evc.object3D.base.controller.interfaces.ICDirectionalLight;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;
import fr.istic.evc.object3D.base.controller.interfaces.ICWorld;

public class Server extends UnicastRemoteObject implements IServer, IEntity {
	
	private static final long serialVersionUID = 1L;
	private static final int rmiPort = 1234;
	private static final int createDiffusionPort = 4321;
	private static final int updateDiffusionPort = 4322;
	private static final String hostName = "127.0.0.1";
	private static final String serverName = "williamServer";
	private static final String groupName = "239.19.10.10";
	
	transient MulticastSender sender ;
	ICWorld world;
	ICAmbientLight ambientLight1;
	
	public Server(String title) throws RemoteException {
		
		// Configuration
		try {
            //Object serverRMIPort;
            LocateRegistry.createRegistry (rmiPort) ;
            Naming.rebind ("//" + hostName + ":" + rmiPort + "/" + serverName, this) ;
            System.out.println ("Ready to serve") ;
            sender = new MulticastSender (groupName, createDiffusionPort, updateDiffusionPort) ;
        } catch (Exception e) {
            e.printStackTrace() ;
        }
		
		// World
		world = new CWorld();
		//world.setServer(this);
		
		/*
		// Ambient light 1
		ambientLight1 = new CAmbientLight();
		ambientLight1.setId("ambientLight1");
		ambientLight1.setAmbientColor(new Color3f(0.2f, 0.2f, 0.2f));
		world.add(ambientLight1);

		// Directional light 1
		ICDirectionalLight directionalLight1 = new CDirectionalLight();
		directionalLight1.setId("directionalLight1");
		world.add(directionalLight1);
		*/
		
		// Box1
		ICObject box1 = new CObject();
		box1.setEntity(this);
		box1.setId("box1");
		box1.setGeometry("cube");
		box1.updateAmbientColor(new Color3f(1.0f, 0.0f, 0.0f));
		box1.setDiffuseColor(new Color3f(1.0f, 0.0f, 0.0f));
		box1.setPosition(new Vector3d(-10, 0, -5));
		box1.IsPickable(true);
		world.add(box1);
		
		// Box2
		ICObject box2 = new CObject();
		box2.setEntity(this);
		box2.setId("box2");
		box2.setGeometry("cube");
		box2.updateAmbientColor(new Color3f(0.5f, 0.5f, 0.5f));
		box2.setDiffuseColor(new Color3f(0.5f, 0.5f, 0.5f));
		box2.setPosition(new Vector3d(10, 0, 5));
		box2.IsPickable(true);
		world.add(box2);
		
		// Box3
		ICObject box3 = new CObject();
		box3.setEntity(this);
		box3.setId("box3");
		box3.setGeometry("cube");
		box3.updateAmbientColor(new Color3f(0.0f, 0.0f, 1.0f));
		box3.setDiffuseColor(new Color3f(0.0f, 0.0f, 1.0f));
		box3.setPosition(new Vector3d(0, 0, -5 ));
		box3.IsPickable(true);
		world.add(box3);
		
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
		
		// System camera
		Camera systemCamera = new Camera();
		Transform3D transform3D = new Transform3D();
		transform3D.setTranslation(new Vector3d(0, 0, 30));
		systemCamera.setTransform3D(transform3D);
		
		// Devices
		world.addDevice(new Mouse());
		
		// Show world
		world.show();
		
		// IHM
		IHM ihm = new IHM(world, systemCamera);
		ihm.setTitle(title);
	}
	

	@Override
	public String getDiffusionGroupName() throws RemoteException {
		return groupName ;
	}

	@Override
	public Integer getCreatePort() throws RemoteException {
		return new Integer (createDiffusionPort);
	}

	@Override
	public Integer getUpdatePort() throws RemoteException {
		return new Integer(updateDiffusionPort);
	}


//	@Override
//	public void reSend() {
//		for ( ICObject o : world.getObjects()) {
//			System.out.println(o.getPresentation().getClass().getName());
//			if ( !o.getPresentation().getClass().getName().equals("fr.istic.evc.object3D.base.presentation.PAmbientLight") &&  
//					!o.getPresentation().getClass().getName().equals("fr.istic.evc.object3D.base.presentation.PDirectionalLight"))
//				sender.createObject(o.getAbstraction());
//		}
//	}

	@Override
	public void sendCommand(I_Command cmd) {
		cmd.execute(world.getObjects());
		sender.updateObject(cmd);
	}

	/*
	@Override
	public List<ICObject> getObjects() {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	/* -------- Methodes Diffusion -------- */ 
	@Override
	public void reSend(ICObject o) {
		o.setEntity(this);
		sender.createObject(o.getAbstraction());
	}
	
	@Override
	public List<I_CreateCommand> getListObjs() {
		List<ICObject> lo = world.getObjects();
		List<I_CreateCommand> lc = new ArrayList<>();
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
	
}
