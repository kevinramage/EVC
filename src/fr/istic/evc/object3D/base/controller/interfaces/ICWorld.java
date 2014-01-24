/**
 * Interface which contains the controller of the 3D world
 * 
 * @author Kevin RAMAGE
 * @author Thomas CARO
 * 
 */


package fr.istic.evc.object3D.base.controller.interfaces;

import java.util.List;

import fr.istic.evc.device.IDevice;
import fr.istic.evc.graphic2D.CameraManager;
import fr.istic.evc.object3D.base.controller.CObject;
import fr.istic.evc.object3D.base.presentation.interfaces.IPWorld;


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

	void removeObject(CObject controller);

	List<IDevice> getDevices();

//	void setServer(Server server);
}
