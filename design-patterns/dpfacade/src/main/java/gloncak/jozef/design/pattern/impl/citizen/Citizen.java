package gloncak.jozef.design.pattern.impl.citizen;

import java.util.ArrayList;
import java.util.List;

/**
 * Model real citizen (citizen) in country
 */
public class Citizen {
    private static int idGenerator = 1;

    private String name;
    final private int citizenId;
    private Integer permanentStay = null;
    final private List<Integer> previousStays;

    public Citizen(String name) {
        this.name = name;
        this.citizenId = idGenerator++;
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

    public int getCitizenId() {
        return citizenId;
    }

    public String getName() {
        return name;
    }
}
