package org.haibin369.mybatis.mapper;

import org.haibin369.mybatis.model.Article;
import org.haibin369.mybatis.model.User;

import java.util.List;

public interface UserMapper {
    public User getUserById(int id);

    public List<User> searchUsers(String name);

    public void addUser(User user);

    public void updateUser(User user);

    public void deleteUserById(int id);

    public List<Article> getUserArticles(User user);
}
