package fr.istic.evc.project;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import fr.istic.evc.Command.I_Command;
import fr.istic.evc.Command.I_CreateCommand;
import fr.istic.evc.object3D.base.controller.interfaces.ICObject;

public interface IServer extends Remote{

	String getDiffusionGroupName () throws RemoteException ; 
	Integer getCreatePort () throws RemoteException ;
	Integer getUpdatePort() throws RemoteException;
	void sendCommand(I_Command cmd) throws RemoteException;
	void reSend(ICObject o) throws RemoteException;
	List<I_CreateCommand> getListObjs() throws RemoteException;
	void update(I_Command cmd) throws RemoteException;
	int obtainID() throws RemoteException;

}
