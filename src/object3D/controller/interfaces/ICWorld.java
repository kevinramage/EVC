/**
 * Interface which contains the controller of the 3D world
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 * 
 */


package object3D.controller.interfaces;

import graphic2D.CameraManager;

import java.util.List;

import object3D.presentation.interfaces.IPWorld;
import device.IDevice;


public interface ICWorld {

	/**
	 * Get the presentation layer of the world
	 * @return the presentation of the world
	 */
	IPWorld getPresentation();

	/**
	 * Add an 3D object on the world
	 * @param object the 3D object to add
	 */
	void add(ICObject object);

	/**
	 * Prepare the 3D scene
	 */
	void show();

	/**
	 * Add a device to the world
	 * @param device to add in the world
	 */
	void addDevice(IDevice device);


	List<ICObject> getObjects();

	ICObject getObjectById(String id);

	void setCameraManager(CameraManager cameraManager);

	CameraManager getCameraManager();

	void removeObject(ICObject controller);

	List<IDevice> getDevices();

//	void setServer(Server server);
}
