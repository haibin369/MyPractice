package org.haibin369.spring.configuration;

import org.haibin369.spring.aop.MyTeacher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRunner_AOP {
    public static void main(String[] args) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("config/SpringContext_AOP.xml");
        MyTeacher myTeacher = (MyTeacher) appContext.getBean("myTeacher");
        myTeacher.classStart("Ben");
        myTeacher.teachSomething();
        myTeacher.classOver();
    }
}
