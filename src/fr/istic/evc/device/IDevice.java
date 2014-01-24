package fr.istic.evc.device;

import javax.media.j3d.BranchGroup;

import fr.istic.evc.graphic2D.CameraManager;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;

public interface IDevice {
	void setBranchGroup(BranchGroup branchGroup);
	void setCameraManager(CameraManager cameraManager);
	void unselectedAll();
	void addToBlackList(ICObject obj);
	void removeFromBlackList(ICObject obj);
	void forcePick(ICObject obj);
	void forceUnpick(ICObject obj);
}
