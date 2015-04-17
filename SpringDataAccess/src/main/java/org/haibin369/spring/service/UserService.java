package org.haibin369.spring.service;

import org.haibin369.spring.bean.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    User getUser(String username, String password);

    void addUser(User user);

    @Transactional(propagation = Propagation.REQUIRED)
    void addUsers(List<User> users);

    void txAddUsers(List<User> users);
}
