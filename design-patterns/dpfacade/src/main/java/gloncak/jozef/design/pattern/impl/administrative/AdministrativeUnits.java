package gloncak.jozef.design.pattern.impl.administrative;

import gloncak.jozef.design.pattern.impl.administrative.unit.AdministrativeUnit;
import gloncak.jozef.design.pattern.impl.administrative.unit.District;
import gloncak.jozef.design.pattern.impl.administrative.unit.Settlement;
import gloncak.jozef.design.pattern.impl.administrative.unit.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Agregate data for states, districts and setlements
 */
public class AdministrativeUnits {

    private static final Logger LOG = LoggerFactory.getLogger(AdministrativeUnits.class);

    /**
     * map stateId to concrete instance of {@link State}
     */
    private Map<Integer, State> idToState;

    /**
     * map districtId to instance of {@link District}
     * contains all districts of all states.
     */
    private Map<Integer, District> idToDistrict;

    /**
     * map settlementId to instance of {@link Settlement}
     * contains all settlement of all district and states.
     */
    private Map<Integer, Settlement> idToSettlement;

    public AdministrativeUnits() {
        this.idToState = new HashMap<>();
        this.idToDistrict = new HashMap<>();
        this.idToSettlement = new HashMap<>();
    }

    /**
     * Add new state with name {@code stateName} to collections of states
     *
     * @param stateName name of state
     * @param allowEqualNames if true than 2 states with equal are allowed
     * @return code assigned to state
     */
    public Optional<Integer> addState(String stateName, boolean allowEqualNames) {
        if (!allowEqualNames && !isNewNameUnique(idToState.values(), stateName)) {
            LOG.info("New state isn't created because specified name - {} - already exists.", stateName);
            return Optional.empty();
        }
        final State newState = new State(stateName);
        final int newStateId = newState.getId();
        idToState.put(newStateId, newState);
        return Optional.of(newStateId);
    }

    public Optional<Integer> addState(String stateName) {
        return addState(stateName, false);
    }

    public Optional<Integer> addDistrict(String districtName, int stateId,  boolean allowEqualNames) {
        Optional<Integer> result = Optional.empty();
        if (!allowEqualNames && !isNewNameUnique(idToDistrict.values(), districtName)) {
            LOG.info("New district isn't created because specified name - {} - already exists.", districtName);
            return result;
        }
        final State state = idToState.get(stateId);
        if (state == null) {
            LOG.info("State with id {} doesn't exist.", stateId);
            return result;
        }
        final District newDistrict = new District(districtName);
        final int newDistrictId = newDistrict.getId();
        state.addDistrict(newDistrictId);
        idToDistrict.put(newDistrictId, newDistrict);
        return Optional.of(newDistrictId);
    }

    public Optional<Integer> addDistrict(String districtName, int stateId) {
        return addDistrict(districtName, stateId, false);
    }

    private static boolean isNewNameUnique(Collection<? extends AdministrativeUnit> admUnits, String stateName) {
        return admUnits
                .stream()
                .filter(admUnit -> stateName.equals(admUnit.getName()))
                .findAny()
                .isEmpty();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("states: ");
        sb.append(idToState.toString());
        sb.append("\n");
        sb.append("districts: ");
        sb.append(idToDistrict.toString());
        sb.append("\n");
        sb.append("settlements: ");
        sb.append(idToSettlement.toString());
        return sb.toString();
    }
}
