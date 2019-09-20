package gloncak.jozef.design.pattern.command.impl.person;

import gloncak.jozef.design.pattern.command.api.command.Archivator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Model for real person.
 *
 * It is used to demonstrate usage of command design pattern
 */
public class Person {
    private String firstName;
    private String surname;

    private static final Logger LOG = LoggerFactory.getLogger(Person.class);

    public Person(String firstName, String surname) {
        this.firstName = firstName;
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    /**
     * Destroy person.
     *
     * In implementation there is just log as placeholder for real destroy activities (e. g. destroy some other inner
     * instances of other objects, unregistering from subscriptions.....
     * @param archivator
     */
    public void destroy(Archivator archivator) {
        LOG.info("Destroying inner instances in Person");
        if (archivator != null) {
            archivator.archive(this);
        }
        LOG.info("Final destroyng activities.");
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
