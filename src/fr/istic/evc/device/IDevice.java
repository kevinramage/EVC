package fr.istic.evc.device;

import javax.media.j3d.BranchGroup;

import fr.istic.evc.graphic2D.CameraManager;

public interface IDevice {
	void setBranchGroup(BranchGroup branchGroup);
	void setCameraManager(CameraManager cameraManager);
}
