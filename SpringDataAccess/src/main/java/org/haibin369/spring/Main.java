package org.haibin369.spring;

import org.haibin369.spring.bean.User;
import org.haibin369.spring.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        testGetUser();

    }

    private static void testAddUser() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("config/ApplicationContext.xml");
        UserService userService = (UserService) appContext.getBean("userService");
        User user = new User();
        user.setUsername("BenChen");
        user.setPassword("123456");
        userService.addUser(user);
    }

    private static void testGetUser() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("config/ApplicationContext.xml");
        UserService userService = (UserService) appContext.getBean("userService");
        User user = userService.getUser("BenChen", "123456");
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
    }


}
