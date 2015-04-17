package org.haibin369.spring.service;

import org.haibin369.spring.bean.User;
import org.haibin369.spring.dao.UserDAO;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private TransactionTemplate transactionTemplate;

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUser(String username, String password) {
        return userDAO.getUser(username, password);
    }

    @Override
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public void addUsers(List<User> users) {
        userDAO.addUsers(users);
    }

    @Override
    public void txAddUsers(final List<User> users) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                userDAO.addUsers(users);
            }
        });
    }
}
