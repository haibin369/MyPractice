package org.haibin369.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.haibin369.mybatis.mapper.UserMapper;
import org.haibin369.mybatis.model.Article;
import org.haibin369.mybatis.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import static org.junit.Assert.*;

public class UserTest {
    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSession sqlSession;
    private static ApplicationContext appContext;

    private UserMapper userMapper;

//    @BeforeClass
//    public static void beforeClass() throws IOException {
//        Reader reader = Resources.getResourceAsReader("config/MyBatisConfig.xml");
//        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, "MySQL");
//    }
//
//    @Before
//    public void beforTest(){
//        sqlSession = sqlSessionFactory.openSession();
//        userMapper = sqlSession.getMapper(UserMapper.class);
//    }

    @BeforeClass
    public static void beforeClass() {
        appContext = new ClassPathXmlApplicationContext("config/ApplicationContext.xml");
        sqlSessionFactory = (SqlSessionFactory) appContext.getBean("sqlSessionFactory");
    }

    @Before
    public void beforTest(){
        sqlSession = sqlSessionFactory.openSession();
        userMapper = (UserMapper) appContext.getBean("userMapper");
    }

    @After
    public void afterTest(){
        sqlSession.close();
    }

    @Test
    public void getUserTest(){
        User user = sqlSession.selectOne("org.haibin369.mybatis.mapper.UserMapper.getUserById", 1);
        assertEquals(user.getUserName(), "summer");
        assertEquals(user.getUserAge(), "100");
    }

    @Test
    public void getUserByMapperTest(){
        User user = userMapper.getUserById(1);
        assertEquals(user.getUserName(), "summer");
        assertEquals(user.getUserAge(), "100");
    }

    @Test
    public void searchUsersTest(){
        List<User> userList = userMapper.searchUsers("summ%");
        assertEquals(2, userList.size());
        for(User user : userList){
            System.out.println(user.getUserName());
        }
    }

    @Test
    public void addUserTest(){
        User user = new User();
        user.setUserName("ChenHaiBin");
        user.setUserAge("19");
        user.setUserAddress("Shenzhen, Nanshan");

        userMapper.addUser(user);
        sqlSession.commit();

        User newUser = userMapper.getUserById(user.getId());
        assertEquals(newUser.getUserName(), user.getUserName());
        assertEquals(newUser.getUserAddress(), user.getUserAddress());
    }

    @Test
    public void updateUserTest(){
        User user = userMapper.getUserById(3);
        String newUserName = user.getUserName() + "_updated";
        user.setUserName(newUserName);
        userMapper.updateUser(user);
        sqlSession.commit();

        User newUser = userMapper.getUserById(3);
        assertEquals(newUser.getUserName(), newUserName);
    }

    @Test
    public void deleteUserTest(){
        userMapper.deleteUserById(4);
        sqlSession.commit();

        User user = userMapper.getUserById(4);
        assertNull(user);
    }

    @Test
    public void getUserArticleTest(){
        User user = userMapper.getUserById(1);
        List<Article> userArticles = userMapper.getUserArticles(user);
        assertEquals(4, userArticles.size());
    }

}
