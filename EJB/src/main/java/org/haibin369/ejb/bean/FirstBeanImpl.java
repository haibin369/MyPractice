package org.haibin369.ejb.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;

@Stateless(mappedName = "FirstEJB")
public class FirstBeanImpl implements FirstBean {
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("After construct......");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("Before destroy......");
    }
}
