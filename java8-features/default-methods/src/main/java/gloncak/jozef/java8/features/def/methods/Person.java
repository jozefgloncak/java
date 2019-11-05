package gloncak.jozef.java8.features.def.methods;

import gloncak.jozef.java8.features.def.methods.interfaces.FootballPlayer;
import gloncak.jozef.java8.features.def.methods.interfaces.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Person implements FootballPlayer, Student {
    private static final Logger LOG = LoggerFactory.getLogger(Person.class);
    private String name;

    public Person(String name) {
        this.name = name;
    }

    /* overriding of default methods with equal name from interfaces */
    @Override
    public void introduce() {
        LOG.info("Hello my name is {}.", name);
        FootballPlayer.super.introduce();
        Student.super.introduce();
    }
}
