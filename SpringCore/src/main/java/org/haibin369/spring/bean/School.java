package org.haibin369.spring.bean;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 09-5-5
 * Time: 上午1:30
 * To change this template use File | Settings | File Templates.
 */
public class School {
    private static School school = new School();
    private Teacher teacher;
    private Student student;

    private School() {
    }

    public static School getSchool() {
        return school;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public static void setSchool(School school) {
        School.school = school;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void getInfo() {
        System.out.println("Teacher in school: " + teacher.getName());
        System.out.println("Student in school: " + student.getName());
    }

    public void openSchool() {
        System.out.println("School is opened.");
    }

    public void closeSchool() {
        System.out.println("School is closed.");
    }
}
