package org.haibin369.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIService extends Remote {

    public void addUser(User user) throws RemoteException;

    public User getUser(String username) throws RemoteException;

}
