package org.haibin369.ejb.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class MyInterceptor {
    @AroundInvoke
    public Object log(InvocationContext context) throws Exception {
        String methodName = context.getMethod().getName();
        System.out.println("Method (" + methodName + ") start!");
        Object returnVal = context.proceed();
        System.out.println("Method (" + methodName + ") end!");
        return returnVal;
    }
}
