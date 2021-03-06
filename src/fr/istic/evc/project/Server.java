//package fr.istic.evc.project;
//
//
//public class Server extends UnicastRemoteObject implements IServer, IEntity {
//	
//	private static final long serialVersionUID = 1L;
//	private static final int rmiPort = 1234;
//	private static final int createDiffusionPort = 4321;
//	private static final int updateDiffusionPort = 4322;
//	private static final int deleteDiffusionPort = 4323;
//	private static final String hostName = "127.0.0.1";
//	private static final String serverName = "williamServer";
//	private static final String groupName = "239.19.10.10";
//	
//	private int compteur;
//	
//	transient MulticastSender sender ;
//	ICWorld world;
//	ICAmbientLight ambientLight1;
//	//TODO a supprimer
//	String title;
//	
//	
//	
//	public Server(String title) throws RemoteException {
//		this.title = title;
//		// Configuration
//		try {
//            //Object serverRMIPort;
//            LocateRegistry.createRegistry (rmiPort) ;
//            Naming.rebind ("//" + hostName + ":" + rmiPort + "/" + serverName, this) ;
//            sender = new MulticastSender (groupName, createDiffusionPort, updateDiffusionPort, deleteDiffusionPort) ;
//            compteur = 0;
//        } catch (Exception e) {
//            e.printStackTrace() ;
//        }
//		
//		// World
//		world = new CWorld();
////		world.setServer(this);
//		
//		// Box1
////		ICObject box1 = new CObject();
////		box1.setEntity(this);
////		box1.setId("box1");
////		box1.updateGeometry("cube");
////		box1.updateAmbientColor(new Color3f(1.0f, 0.0f, 0.0f));
////		box1.updateDiffuseColor(new Color3f(1.0f, 0.0f, 0.0f));
////		box1.updatePosition(new Vector3d(-10, 0, -5));
////		box1.updatePickable(true);
////		world.add(box1);
////		
////		// Box2
////		ICObject box2 = new CObject();
////		box2.setEntity(this);
////		box2.setId("box2");
////		box2.setGeometry("cube");
////		box2.updateAmbientColor(new Color3f(0.5f, 0.5f, 0.5f));
////		box2.setDiffuseColor(new Color3f(0.5f, 0.5f, 0.5f));
////		box2.setPosition(new Vector3d(10, 0, 5));
////		box2.setPickable(true);
////		world.add(box2);
////		
////		// Box3
////		ICObject box3 = new CObject();
////		box3.setEntity(this);
////		box3.setId("box3");
////		box3.setGeometry("cube");
////		box3.updateAmbientColor(new Color3f(0.0f, 0.0f, 1.0f));
////		box3.setDiffuseColor(new Color3f(0.0f, 0.0f, 1.0f));
////		box3.setPosition(new Vector3d(0, 0, -5 ));
////		box3.setPickable(true);
////		world.add(box3);
//		
//		
////		CSubject s1 = new CSubject();
////		s1.setEntity(this);
////		s1.setId("0-21");
////		s1.setGeometry("sphere");
////		s1.updateAmbientColor(new Color3f(0.0f, 0.0f, 1.0f));
////		s1.setDiffuseColor(new Color3f(0.0f, 0.0f, 1.0f));
////		s1.setPosition(new Vector3d(-5, 0, -5 ));
////		s1.setPickable(true);
////		world.add(s1);
////		
////		CSubject s2 = new CSubject();
////		s2.setEntity(this);
////		s2.setId("0-22");
////		s2.setGeometry("sphere");
////		s2.updateAmbientColor(new Color3f(1.0f, 0.0f, 0.0f));
////		s2.setDiffuseColor(new Color3f(1.0f, 0.0f, 0.0f));
////		s2.setPosition(new Vector3d(5, 0, -5 ));
////		s2.setPickable(true);
////		world.add(s2);
////		
////		
////		ICObject elastic = new CElasticObject(s1, s2);
////		elastic.setEntity(this);
////		elastic.setId("0-23");
////		elastic.setPickable(false);
////		world.add(elastic);
//		
//		// Box du monde
////		ICObject boxWorld = new CObject();
////		boxWorld.setEntity(this);
////		boxWorld.setId("box1");
////		boxWorld.updateGeometry("cube");
////		boxWorld.updateAmbientColor(new Color3f(1.0f, 0.0f, 0.0f));
////		boxWorld.updateDiffuseColor(new Color3f(1.0f, 0.0f, 0.0f));
////		boxWorld.updatePosition(new Vector3d(-10, 0, 0));
////		boxWorld.updateOrientation(new Quat4d(0, 1, 0, Math.PI/2));
////		boxWorld.updatePickable(true);
////		world.add(boxWorld);
////		
////
////		ICObject box2 = new CObject();
////		box2.setEntity(this);
////		box2.setId("box2");
////		box2.updateGeometry("cube");
////		box2.updateAmbientColor(new Color3f(0.0f, 1.0f, 0.0f));
////		box2.updateDiffuseColor(new Color3f(0.0f, 1.0f, 0.0f));
////		box2.updatePosition(new Vector3d(10, 0, 0));
////		box2.updateOrientation(new Quat4d(0, 1, 0, Math.PI/2));
////		box2.updatePickable(true);
////		world.add(box2);
//		
//
//		ICObject box3 = new CObject();
//		box3.setEntity(this);
//		box3.setId("box3");
//		box3.updateGeometry("cube");
//		box3.updateAmbientColor(new Color3f(0.0f, 0.0f, 1.0f));
//		box3.updateDiffuseColor(new Color3f(0.0f, 0.0f, 1.0f));
//		box3.updatePosition(new Vector3d(0, 0, -10));
//		box3.updateOrientation(new Quat4d(0, 1, 0, Math.PI/2));
//		box3.updatePickable(true);
//		world.add(box3);
//		
//
////		ICObject box4 = new CObject();
////		box4.setEntity(this);
////		box4.setId("box4");
////		box4.updateGeometry("cube");
////		box4.updateAmbientColor(new Color3f(0.8f, 0.8f, 0.8f));
////		box4.updateDiffuseColor(new Color3f(0.8f, 0.8f, 0.8f));
////		box4.updatePosition(new Vector3d(0, 0, -10));
////		box4.updateOrientation(new Quat4d(0, 1, 0, Math.PI/2));
////		box4.updatePickable(true);
////		world.add(box4);
//		
//		ICObject clonable = new CClonable();
//		clonable.setEntity(this);
//		clonable.setId("0-100");
//		clonable.updateGeometry("cube");
//		clonable.updateAmbientColor(new Color3f(1.0f, 0.0f, 0.0f));
//		clonable.updateDiffuseColor(new Color3f(1.0f, 0.0f, 0.0f));
//		clonable.updatePosition(new Vector3d(0, 0, 0));
//		clonable.updateOrientation(new Quat4d(0, 1, 0, Math.PI/2));
//		clonable.updatePickable(true);
//		world.add(clonable);
//		
//		
//		
//		// Ambient light 1
//		ICAmbientLight ambientLight1 = new CAmbientLight();
//		ambientLight1.setEntity(this);
//		ambientLight1.setId("ambientLight1");
//		ambientLight1.updateAmbientColor(new Color3f(0.2f, 0.2f, 0.2f));
//		world.add(ambientLight1);
//
//		// Directional light 1
//		ICDirectionalLight directionalLight1 = new CDirectionalLight();
//		directionalLight1.setEntity(this);
//		directionalLight1.setId("directionalLight1");
//		world.add(directionalLight1);
//		
//		
//		// World
////		world = WorldBuiler.getInstance().load("01.xml");
//		
//		
//		// System camera
//		Camera systemCamera = new Camera();
//		Transform3D transform3D = new Transform3D();
//		transform3D.setTranslation(new Vector3d(0, 0, 0));
//		systemCamera.setTransform3D(transform3D);
//		
//		// Devices
//		world.addDevice(new Mouse());
//		
//		world.setCameraManager(new CameraManager(new TransformGroup()));
//		
//		// Show world
//		world.show();
//		
//		// IHM
//		IHM ihm = new IHM(world, systemCamera);
//		ihm.setState(JFrame.ICONIFIED);
//		ihm.setTitle(title);
//	}
//
//	@Override
//	public String getDiffusionGroupName() throws RemoteException {
//		return groupName ;
//	}
//
//	@Override
//	public Integer getCreatePort() throws RemoteException {
//		return new Integer (createDiffusionPort);
//	}
//
//	@Override
//	public Integer getUpdatePort() throws RemoteException {
//		return new Integer(updateDiffusionPort);
//	}
//
//	@Override
//	public int getDeletePort() {
//		return new Integer(deleteDiffusionPort);
//	}
//
//
//
//	@Override
//	public void sendCommand(I_Command cmd) {
//		cmd.execute(this);
//		sender.updateObject(cmd);
//	}
//	
//	
//	
//	/* -------- Methodes Diffusion -------- */ 
//	
//	@Override
//	public void addObject(I_CreateCommand cmd) throws RemoteException{
//		cmd.execute(this);
//		sender.createObject(cmd);
//	}
//
//
//	@Override
//	public void removeObjects(I_Command cmdDelete) throws RemoteException {
//		cmdDelete.execute(this);
//		sender.deleteObjects(cmdDelete);
//	}
//	
//	@Override
//	public synchronized int obtainID() {
//		return compteur++;
//	}
//	
//	
//	@Override
//	public List<I_CreateCommand> getListObjs() {
//		List<ICObject> lo = world.getObjects();
//		List<I_CreateCommand> lc = new ArrayList<>();
//		for (ICObject o:lo) {
//			lc.add(o.getCreateCommand());
//		}
//		return lc;
//	}
//
//	@Override
//	public void update(I_Command cmd) {
//		sender.updateObject(cmd);
//	}
//	
//	@Override
//	public boolean isServer() {
//		return true;
//	}
//
//	
//	@Override
//	public ICWorld getWorld() {
//		return this.world;
//	}
//
//	
//	@Override
//	public int getId() {
//		return -1;
//	}
//
//	@Override
//	public boolean havePick(ICObject obj) {
//		return false;
//	}
//
//	@Override
//	public String getTitle() {
//		return title;
//	}
//
//	@Override
//	public void showAllObjects() {
//		String str = "\n"+this.getTitle()+"\n";
//		str += "Liste des Objets : \n";
//		for (ICObject o:this.getWorld().getObjects()) {
//			str += "\t - "+o.getId()+" de type : "+o.getClass()+"\n";
//		}
//		
//		System.err.println(str);
//	}	
//}
