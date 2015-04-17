package org.haibin369.spring.dao;

import org.haibin369.spring.bean.User;

import java.util.List;

public interface UserDAO {
    User getUser(String username, String password);
    void addUser(User user);
    void addUsers(List<User> users);
}
