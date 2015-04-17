package org.haibin369.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class Students {

    @Pointcut(value = "execution(* org.haibin369.spring.aop.MyTeacher.classStart(..))")
    public void classStart() {
    }

    @Pointcut(value = "execution(* org.haibin369.spring.aop.MyTeacher.classStart(..)) && args(name)")
    public void classStartWithName(String name){
    }

    @Pointcut(value = "execution(* org.haibin369.spring.aop.MyTeacher.classOver(..))")
    public void classOver() {
    }

    @Pointcut(value = "execution(* org.haibin369.spring.aop.MyTeacher.teachSomething(..))")
    public void teachSomething() {

    }

    @Before(value = "classStartWithName(teacherName)")
    public void hello(String teacherName) {
        System.out.println("Students: Hello teacher " + teacherName + " !");
    }

    @After(value="classStart()")
    public void startStudy() {
        System.out.println("Students: Start study.");
    }

    @Before(value = "classOver()")
    public void endStudy() {
        System.out.println("Students: End study.");
    }

    @After(value = "classOver()")
    public void goodBye() {
        System.out.println("Student: Good bye teacher!");
    }

    @Around(value = "teachSomething()")
    public void talk(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Students: Talking!");
        joinPoint.proceed();
        System.out.println("Students: Talking!");
    }
}
