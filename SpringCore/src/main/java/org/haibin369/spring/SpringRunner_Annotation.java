package org.haibin369.spring;

import org.haibin369.spring.bean.School;
import org.haibin369.spring.bean.Student;
import org.haibin369.spring.bean.Teacher;
import org.haibin369.spring.configuration.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRunner_Annotation {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        Student ben = (Student) appContext.getBean("student");
        ben.hello();

        Teacher teacher = (Teacher) appContext.getBean("teacher");
        teacher.hello();

        School school = (School) appContext.getBean("school");
        school.getInfo();

    }
}
