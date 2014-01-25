package object3D.presentation.interfaces;

import javax.media.j3d.Canvas3D;
import javax.media.j3d.TransformGroup;

import device.IDevice;


public interface IPWorld {

	/**
	 * Get the canvas 3D of a 3D world
	 * @return the canvas 3D
	 */
	Canvas3D getCanvas();

	/**
	 * Get the world transform group
	 * @return the world transform group
	 */
	TransformGroup getWorldTransform();

	/**
	 * Add an 3D object to the world 
	 * @param presentation the world presentation
	 */
	void add(IPObject presentation);

	/**
	 * Prepare the 3D scene
	 */
	void show();

	/**
	 * Add a device to the world
	 * @param device device to add
	 */
	void addDevice(IDevice device);

	void remove(IPObject presentation);

}
