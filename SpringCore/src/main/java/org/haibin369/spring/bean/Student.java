package org.haibin369.spring.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("student")
@Scope("prototype")
public class Student implements People {
    @Value("Ben")
    private String name;

    public Student() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void hello() {
        System.out.println("Hello, I'm " + name + ".");
    }
}
