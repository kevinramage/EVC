package project;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import command.I_Command;
import command.create.I_CreateCommand;

public interface IServer extends Remote{

	String getDiffusionGroupName () throws RemoteException ; 
	Integer getCreatePort () throws RemoteException ;
	Integer getUpdatePort() throws RemoteException;
	void sendCommand(I_Command cmd) throws RemoteException;
	List<I_CreateCommand> getListObjs() throws RemoteException;
	void update(I_Command cmd) throws RemoteException;
	int obtainID() throws RemoteException;
	void addObject(I_CreateCommand cmd) throws RemoteException;

}
