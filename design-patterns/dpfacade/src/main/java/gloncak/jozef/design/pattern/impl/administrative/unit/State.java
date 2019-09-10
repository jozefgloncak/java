package gloncak.jozef.design.pattern.impl.administrative.unit;

import java.util.HashSet;
import java.util.Set;

/**
 * Model state (country) which consists of districts
 */
public class State extends AdministrativeUnit {
    private static int idGenerator = 1;

    private Set<Integer> districts;

    public State(String stateName) {
        this.id = idGenerator++;
        this.districts = new HashSet<>();
        setName(stateName);
    }

    public Set<Integer> getDistricts() {
        return districts;
    }

    public void addDistrict(int id) {
        this.districts.add(id);
    }
}
