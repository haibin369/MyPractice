package org.haibin369.test;

import org.haibin369.common.LoginAction;
import org.haibin369.exception.ACLException;
import org.haibin369.model.User;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class LoginActionTest {
    private static LoginAction loginAction;
    private static User user;

    @BeforeClass
    public static void init() {
        loginAction = new LoginAction();
        user = new User("haibin369", "123456");
    }

    @After
    public void clearLoginUser() {
        loginAction.getLoginUser().clear();
    }

    @Test
    public void testLoginSuccess() throws Exception {
        loginAction.login(user);

        assertTrue("User didn't login!", loginAction.getLoginUser().contains(user));
    }

    @Test
    public void testLogout() throws Exception {
        loginAction.login(user);
        loginAction.logout(user);

        assertFalse("User didn't logout!", loginAction.getLoginUser().contains(user));
    }

    //当测试方法抛出ACLException时测试成功
    @Test(expected = ACLException.class)
    public void testAdminLogin() throws ACLException, InterruptedException {
        loginAction.login(new User("admin", "admin"));
    }

    //使用@Rule标记ExpectedException
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testAdminLogin2() throws ACLException, InterruptedException {
        //期待抛出ACLException
        expectedException.expect(ACLException.class);
        //期待抛出的异常信息中包含"Access Denied"字符串
        expectedException.expectMessage(CoreMatchers.containsString("Access Denied"));
        //当然也可以直接传入字符串，表示期待的异常信息（完全匹配）
        //expectedException.expectMessage("Access Denied!");

        loginAction.login(new User("admin", "admin"));
    }

    //忽略该测试，参数为输出信息
    @Ignore("Temporary ignored as no changes.")
    @Test(timeout = 1000)
    public void testLoginTimeout() throws Exception {
        loginAction.login(new User("admin", "admin"));
    }
}