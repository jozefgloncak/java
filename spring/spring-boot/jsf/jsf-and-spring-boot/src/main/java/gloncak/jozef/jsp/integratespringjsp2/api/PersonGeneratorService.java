package gloncak.jozef.jsp.integratespringjsp2.api;

import gloncak.jozef.jsp.integratespringjsp2.model.Person;

import java.util.List;

public interface PersonGeneratorService {

    /**
     * Generates pseudo random persons.
     *
     * @param numOfPersons defines how many person should be generated
     * @return list of persons
     */
    List<Person> generatePersons(int numOfPersons);
}
