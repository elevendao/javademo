package com.edao.codes.rmi;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIRemote extends Remote {
	public int connect() throws RemoteException;
}
