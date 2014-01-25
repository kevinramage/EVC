package device;

import javax.media.j3d.BranchGroup;

import graphic2D.CameraManager;

public interface IDevice {
	void setBranchGroup(BranchGroup branchGroup);
	void setCameraManager(CameraManager cameraManager);
}
