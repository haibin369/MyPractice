package org.haibin369.webservice.service;

import org.haibin369.webservice.exception.AuthenticationException;
import org.haibin369.webservice.model.User;

import javax.jws.Oneway;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface IMyService {
    @WebResult(name = "responseMessage")
    public String getMessage();

    @Oneway
    public void postMessage(@WebParam(name = "message") String message);

    @WebResult(name = "result")
    public String addUser(@WebParam(name = "user") User user);

    @WebResult(name = "user")
    public User getUser(@WebParam(name = "username") String username, @WebParam(name = "password") String password);

    @WebResult(name = "user")
    public List<User> getAllUsers(@WebParam(name = "authInfo", header = true) String authInfo) throws AuthenticationException;
}
