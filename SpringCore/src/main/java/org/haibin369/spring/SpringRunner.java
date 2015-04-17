package org.haibin369.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.haibin369.spring.bean.School;
import org.haibin369.spring.bean.Student;
import org.haibin369.spring.bean.Teacher;

public class SpringRunner {
    public static void main(String[] args) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("config/SpringContext.xml");
        Student ben = (Student) appContext.getBean("student", "test");
        ben.hello();

        Teacher teacher = (Teacher) appContext.getBean("teacher");
        teacher.hello();

        School school = (School) appContext.getBean("school");
        school.getInfo();

    }
}
