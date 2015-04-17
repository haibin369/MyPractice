package org.haibin369.spring.dao;

import org.haibin369.spring.bean.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAOImpl implements UserDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;

    public NamedParameterJdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUser(String username, String password) {
        String sql = "SELECT * FROM USER WHERE username=:username AND password=:password";
        Map<String, String> params = new HashMap<String, String>(2);
        params.put("username", username);
        params.put("password", password);
        List<User> userList = jdbcTemplate.query(sql, params, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        });
        if (userList.size() > 0) {
            return userList.get(0);
        }

        return null;
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO USER (username, password) VALUES (:username, :password)";
        Map<String, String> params = new HashMap<String, String>(2);
        params.put("username", user.getUsername());
        params.put("password", user.getPassword());

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void addUsers(List<User> users) {
        String sql = "INSERT INTO USER (username, password) VALUES (:username, :password)";
        Map<String, String>[] paramArr = new HashMap[users.size()];
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            Map<String, String> params = new HashMap<String, String>(2);
            params.put("username", user.getUsername());
            params.put("password", user.getPassword());
            paramArr[i] = params;
        }

        jdbcTemplate.batchUpdate(sql, paramArr);
    }
}
