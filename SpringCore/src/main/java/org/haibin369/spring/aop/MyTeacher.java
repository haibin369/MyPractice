package org.haibin369.spring.aop;

public class MyTeacher {
    public void classStart(String myName){
        System.out.println("Teacher: Class Start!");
    }

    public void classOver(){
        System.out.println("Teacher: Class Over!");
    }

    public void teachSomething(){
        System.out.println("Teacher: Teaching Something!");
    }
}
