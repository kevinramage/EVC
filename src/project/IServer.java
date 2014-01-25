package project;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import command.I_Command;

public interface IServer extends Remote{

	String getDiffusionGroupName () throws RemoteException ; 
	Integer getCreatePort () throws RemoteException ;
	Integer getUpdatePort() throws RemoteException;
	void sendCommand(I_Command cmd) throws RemoteException;
	List<I_Command> getListObjs() throws RemoteException;
	void update(I_Command cmd) throws RemoteException;
	int obtainID() throws RemoteException;
	void addObject(I_Command cmd) throws RemoteException;
	void removeObjects(I_Command cmdDelete) throws RemoteException;
	int getDeletePort() throws RemoteException;

}
