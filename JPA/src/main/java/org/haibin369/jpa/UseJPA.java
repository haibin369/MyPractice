package org.haibin369.jpa;

import org.haibin369.jpa.entity.Student;
import org.haibin369.jpa.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class UseJPA {
    public static void main(String[] args) throws Exception {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MyPersistence");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            oneToMany(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }

    }

    public static void persist(EntityManager entityManager) throws Exception {
        Teacher teacher = new Teacher("haibin369", "SZ");
        entityManager.persist(teacher);
    }

    public static void find(EntityManager entityManager) throws Exception {
        Teacher teacher = entityManager.find(Teacher.class, 1);
        System.out.println("ID: " + teacher.getId());
        System.out.println("Name: " + teacher.getName());
        System.out.println("Address: " + teacher.getAddress());
    }

    public static void update(EntityManager entityManager) {
        Teacher teacher = entityManager.find(Teacher.class, 1);
        System.out.println("ID: " + teacher.getId());
        System.out.println("Name: " + teacher.getName());
        System.out.println("Address: " + teacher.getAddress());

        teacher.setName(teacher.getName() + System.currentTimeMillis());
        entityManager.flush();
        System.out.println("ID: " + teacher.getId());
        System.out.println("Name: " + teacher.getName());
        System.out.println("Address: " + teacher.getAddress());
    }

    public static void remove(EntityManager entityManager) {
        Teacher teacher = entityManager.find(Teacher.class, 1);
        System.out.println("ID: " + teacher.getId());
        System.out.println("Name: " + teacher.getName());
        System.out.println("Address: " + teacher.getAddress());

        entityManager.remove(teacher);
        teacher = entityManager.find(Teacher.class, 1);
        System.out.println(teacher == null);
    }

    private static void oneToMany(EntityManager entityManager) {
        Teacher teacher = entityManager.find(Teacher.class, 1);
        Student student1 = new Student();
        student1.setName("fffff");
        student1.setTeacher(teacher);
        Student student2 = new Student();
        student2.setName("gggggg");
        student2.setTeacher(teacher);
        List<Student> studentList = new ArrayList<Student>(2);
        studentList.add(student1);
        studentList.add(student1);
        teacher.setStudents(studentList);

        entityManager.persist(teacher);
        entityManager.persist(student1);
        entityManager.persist(student2);
    }
}
