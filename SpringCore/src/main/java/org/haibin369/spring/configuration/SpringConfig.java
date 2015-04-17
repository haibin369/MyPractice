package org.haibin369.spring.configuration;

import org.haibin369.spring.bean.School;
import org.haibin369.spring.bean.Student;
import org.haibin369.spring.bean.Teacher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SpringConfig {

    @Bean
    @Scope("prototype")
    public Student student() {
        return new Student("Ben");
    }

    @Bean
    public Teacher teacher() {
        return new Teacher("Bang", student());
    }

    @Bean
    public School school(){
        School school = School.getSchool();
        school.setTeacher(teacher());
        school.setStudent(student());
        return school;
    }
}
