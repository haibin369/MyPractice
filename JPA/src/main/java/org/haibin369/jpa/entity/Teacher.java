package org.haibin369.jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teacher")
@SecondaryTable(name = "teacher_address", pkJoinColumns = @PrimaryKeyJoinColumn(name = "teacher_id"))
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "teacher_name", length = 50)
    private String name;
    @Column(table = "teacher_address")
    private String address;
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Student.class, mappedBy = "teacher")
    private List<Student> students = new ArrayList<Student>();

    public Teacher() {
    }

    public Teacher(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
