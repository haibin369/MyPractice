package org.haibin369.spring.bean;

public class Teacher implements People {

    private String name;
    private Student student;

    public Teacher(String name, Student myStudent){
        this.name = name;
        this.student = myStudent;
    }

    @Override
    public String getName() {
        return name;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public void hello() {
        System.out.println("Hello, I'm " + name + ".");
        System.out.println(student.getName() + " is my student.");
    }


}
