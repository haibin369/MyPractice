package org.haibin369.rmi.client;


import org.haibin369.rmi.server.RMIService;
import org.haibin369.rmi.server.User;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class RMIClient {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        RMIService service = (RMIService) Naming.lookup("rmi://:1099/myService");
        service.addUser(new User("haibin369", "123456"));

        User user = service.getUser("haibin369");
        System.out.println(user);

        service.addUser(new User("haibin369", "123456"));
    }
}
