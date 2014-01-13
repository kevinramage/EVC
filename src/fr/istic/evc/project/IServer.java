package fr.istic.evc.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

import fr.istic.evc.Command.Command;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;

public interface IServer extends Remote{

	String getDiffusionGroupName () throws RemoteException ; 
	Integer getCreatePort () throws RemoteException ;
	void reSend() throws RemoteException;
	Integer getUpdatePort() throws RemoteException;
	void sendCommand(Command cmd) throws RemoteException;
	void reSend(ICObject o) throws RemoteException;
	void update(Command cmd) throws RemoteException;

}
