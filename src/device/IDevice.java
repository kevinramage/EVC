package device;

import graphic2D.CameraManager;
import javax.media.j3d.BranchGroup;
import object3D.controller.interfaces.ICObject;



public interface IDevice {
	void setBranchGroup(BranchGroup branchGroup);
	void setCameraManager(CameraManager cameraManager);
	void unselectedAll();
	void addToBlackList(ICObject obj);
	void removeFromBlackList(ICObject obj);
	void forcePick(ICObject obj);
	void forceUnpick(ICObject obj);
}
