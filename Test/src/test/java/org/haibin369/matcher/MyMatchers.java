package org.haibin369.matcher;

import org.haibin369.model.User;
import org.hamcrest.Matcher;

/**
 * Created by Administrator on 09-6-5.
 */
public class MyMatchers {

    public static Matcher<User> isAdmin() {
        return new IsAdminMatcher();
    }
}
