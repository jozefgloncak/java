package gloncak.jozef.hibernate.many.to.many.entity;

import java.util.Set;

public class Student {
    private int id;
    private String name;
    private Set<Subject> subjects;

    public Student() {
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Student(String name, Set<Subject> subjects) {
        this.name = name;
        this.subjects = subjects;
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

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name='" + name + '\'' + ", subjects=" + subjects + '}';
    }
}
