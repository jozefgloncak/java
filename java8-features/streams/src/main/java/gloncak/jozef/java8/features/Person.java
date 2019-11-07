package gloncak.jozef.java8.features;

import java.util.Collections;
import java.util.List;

public class Person {
    private String name;
    private List<String> subjects = Collections.emptyList();

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, List<String> subjects) {
        this.name = name;
        this.subjects = subjects;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + '}';
    }
}
