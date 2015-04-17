package org.haibin369.common;

import org.haibin369.exception.ACLException;
import org.haibin369.model.User;

import java.util.ArrayList;
import java.util.List;

public class LoginAction {
    private static final User FORBIDDEN_USER = new User("admin", "admin");
    private static final List<User> LOGIN_USER = new ArrayList<User>();

    public void login(User user) throws ACLException, InterruptedException {
        if (FORBIDDEN_USER.equals(user)) {
            Thread.sleep(2000);
            throw new ACLException("Access Denied!");
        }

        if (!LOGIN_USER.contains(user)) {
            LOGIN_USER.add(user);
        }
    }

    public void logout(User user) throws InterruptedException {
        LOGIN_USER.remove(user);
    }

    public List<User> getLoginUser() {
        return LOGIN_USER;
    }
}
