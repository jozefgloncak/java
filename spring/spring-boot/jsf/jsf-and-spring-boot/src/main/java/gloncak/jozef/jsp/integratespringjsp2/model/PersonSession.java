package gloncak.jozef.jsp.integratespringjsp2.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDate;

@Component
@SessionScope
public class PersonSession {

    private Person person;


    public PersonSession() {
        this.person = new Person();
    }

    public PersonSession(String firstName, Integer age, Integer height, LocalDate jobStartDate) {
        this.person = new Person(firstName, age, height, jobStartDate);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
