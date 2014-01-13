package fr.istic.evc.project;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import fr.istic.evc.object3D.base.controller.interfaces.ICObject;

public interface IServer extends Remote{

	String getDiffusionGroupName () throws RemoteException ; 
	Integer getDiffusionPort () throws RemoteException ;
	void reSend() throws RemoteException; 
}
