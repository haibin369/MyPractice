package org.haibin369.rmi.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RMIServiceImpl extends UnicastRemoteObject implements RMIService {
    private static final Map<String, User> users = new HashMap<String, User>();

    protected RMIServiceImpl() throws RemoteException {
    }

    @Override
    public void addUser(User user) throws RemoteException {
        String username = user.getUsername();
        if(users.get(username) == null) {
            users.put(user.getUsername(), user);
            System.out.println("Add User: username: " + user.getUsername() + ", password: " + user.getPassword());
        }else{
            throw new RemoteException("User already exists");
        }
    }

    @Override
    public User getUser(String username) throws RemoteException {
        return users.get(username);
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        RMIService service = new RMIServiceImpl();
        LocateRegistry.createRegistry(1099);
        Naming.rebind("rmi://:1099/myService", service);
    }
}
