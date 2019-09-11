package gloncak.jozef.design.pattern.impl.person;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Aggregate data for Persons
 */
public class Citizens {
    final Map<Integer, Person> citizens = new HashMap<>();

    /**
     * Add new citizen to collection of citizens
     *
     * @param name is name of citizen
     * @return instance of new {@link Person} class
     */
    public Person addCitizen(String name) {
        final Person newPerson = new Person(name);
        final int newPersonId = newPerson.getPersonId();
        this.citizens.put(newPersonId, newPerson);
        return newPerson;
    }

    public Optional<Person> findCitizen(int citizenId) {
        return Optional.ofNullable(this.citizens.get(citizenId));
    }
}
