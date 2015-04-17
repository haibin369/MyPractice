package org.haibin369.spring.test;

import org.haibin369.spring.bean.User;
import org.haibin369.spring.service.UserService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {
    private static UserService userService;

    @BeforeClass
    public static void init() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("config/ApplicationContext.xml");
        userService = (UserService) appContext.getBean("userService");
    }

    @Test
    public void getUser() {
        User user = userService.getUser("BenChen", "123456");
        assertEquals(user.getUsername(), "BenChen");
        assertEquals(user.getPassword(), "123456");
    }

    @Test
    public void addUser() {
        String password = String.valueOf(System.currentTimeMillis());
        User user = new User();
        user.setUsername("BenChen");
        user.setPassword(password);
        userService.addUser(user);

        assertNotNull(userService.getUser("BenChen", password));
    }

    @Test
    public void addUsers() {
        int userSize = 3;
        String timeStamp = String.valueOf(System.currentTimeMillis());
        List<User> users = new ArrayList<User>(3);
        for (int i = 0; i < userSize; i++) {
            User user = new User();
            user.setUsername("Ben_" + timeStamp + "_" + i);
            user.setPassword(timeStamp);
            users.add(user);
        }
        userService.addUsers(users);

        for(User user : users){
            assertNotNull(userService.getUser(user.getUsername(), user.getPassword()));
        }
    }

    @Test
    public void txAddUsers() {
        int userSize = 3;
        String timeStamp = String.valueOf(System.currentTimeMillis());
        List<User> users = new ArrayList<User>(3);
        for (int i = 0; i < userSize; i++) {
            User user = new User();
            user.setUsername("Ben");
            user.setPassword(timeStamp);
            users.add(user);
        }
        try {
            userService.txAddUsers(users);
        }catch (Exception e){
            assertTrue(e instanceof DuplicateKeyException);
        }

        for(User user : users){
            assertNull(userService.getUser(user.getUsername(), user.getPassword()));
        }
    }

    @Test
    public void addUsersFail() {
        int userSize = 3;
        String timeStamp = String.valueOf(System.currentTimeMillis());
        List<User> users = new ArrayList<User>(3);
        for (int i = 0; i < userSize; i++) {
            User user = new User();
            user.setUsername("Ben");
            user.setPassword(timeStamp);
            users.add(user);
        }
        try {
            userService.addUsers(users);
        }catch (Exception e){
            System.out.println(e.getMessage());
            assertTrue(e instanceof DuplicateKeyException);
        }

        for(User user : users){
            assertNull(userService.getUser(user.getUsername(), user.getPassword()));
        }
    }
}
