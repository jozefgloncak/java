package gloncak.jozef.design.pattern.impl.citizen;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Aggregate data for instances of class {@link Citizen}
 */
public class Citizens {
    final Map<Integer, Citizen> citizens = new HashMap<>();

    /**
     * Add new citizen to collection of citizens
     *
     * @param name is name of citizen
     * @return instance of new {@link Citizen} class
     */
    public Citizen addCitizen(String name) {
        final Citizen newCitizen = new Citizen(name);
        final int newCitizenId = newCitizen.getCitizenId();
        this.citizens.put(newCitizenId, newCitizen);
        return newCitizen;
    }

    public Optional<Citizen> findCitizen(int citizenId) {
        return Optional.ofNullable(this.citizens.get(citizenId));
    }
}
