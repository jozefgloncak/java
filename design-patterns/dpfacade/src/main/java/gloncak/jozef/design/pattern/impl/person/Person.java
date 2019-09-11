package gloncak.jozef.design.pattern.impl.person;

import java.util.ArrayList;
import java.util.List;

/**
 * Model real citizen (person) in country
 */
public class Person {
    private static int idGenerator = 1;

    private String name;
    final private int personId;
    private Integer permanentStay = null;
    final private List<Integer> previousStays;

    public Person(String name) {
        this.name = name;
        this.personId = idGenerator++;
        previousStays = new ArrayList<>();
    }

    public void setPermanentStay(int newPermanentStay) {
        if (this.permanentStay != null) {
            this.previousStays.add(this.permanentStay);
        }
        this.permanentStay = newPermanentStay;
    }

    public Integer getPermanentStay() {
        return permanentStay;
    }

    public int getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }
}
