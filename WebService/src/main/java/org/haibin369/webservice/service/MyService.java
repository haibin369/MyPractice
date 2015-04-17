package org.haibin369.webservice.service;

import org.haibin369.webservice.exception.AuthenticationException;
import org.haibin369.webservice.model.User;

import javax.jws.HandlerChain;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;


@WebService(endpointInterface = "org.haibin369.webservice.service.IMyService", serviceName = "MyService")
@HandlerChain(file = "WS-Handler.xml")
public class MyService implements IMyService {
    private static final List<User> USERS = new ArrayList<User>(10);

    @Override
    public String getMessage() {
        return "Hello World!";
    }

    @Override
    public void postMessage(String message) {
        System.out.println("Message received: " + message);
    }

    @Override
    public String addUser(User user) {
        if (!USERS.contains(user)) {
            USERS.add(user);
            return "Success.";
        }
        return "User already exists.";
    }

    @Override
    public User getUser(String username, String password) {
        User user = new User(username, password);
        return USERS.contains(user) ? user : null;
    }

    @Override
    public List<User> getAllUsers(String authInfo) throws AuthenticationException {
        if("BENCHEN".equals(authInfo)){
            return USERS;
        }else{
            throw new AuthenticationException("Authentication Fail.");
        }
    }
}
