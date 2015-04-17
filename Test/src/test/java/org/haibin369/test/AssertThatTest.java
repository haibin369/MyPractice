package org.haibin369.test;

/**
 * Created by Administrator on 09-6-5.
 */

import org.haibin369.matcher.IsAdminMatcher;
import org.haibin369.model.User;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.haibin369.matcher.MyMatchers.*;

public class AssertThatTest {

    User user = new User("haibin369", "123456");

    @Test
    public void testAdmin() throws Exception {
        assertThat(user, isAdmin());
    }

    User admin = new User("admin", "admin");


    private int id = 6;
    private boolean trueValue = true;
    private Object nullObject = null;
    private String msg = "Hello World!";

    @Test
    public void testAssertThat() throws Exception {
        //由于静态导入了org.haibin369.matcher.MyMatchers.*，可以调用里面的
        //is(), nullValue(), containsString(), startsWith()方法，可读性更好
        assertThat(id, is(6));
        assertThat(trueValue, is(true));
        assertThat(nullObject, nullValue());
        assertThat(msg, both(startsWith("Helloo")).and(endsWith("World")));
    }

    @Test
    public void testAssert() throws Exception {
        assertEquals(6, id);
        assertTrue(trueValue);
        assertNull(nullObject);
        assertTrue(msg != null && msg.startsWith("Hello") && msg.endsWith("World"));
    }

}
