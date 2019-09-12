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
     * Find {@link Settlement} with {@code settlementId} in map
     *
     * @param settlementId id of settlement
     * @return return Optional with {@link Settlement if exists} or empty Optional otherwise.
     */
    public Optional<Settlement> findInSettlement(int settlementId) {
        return Optional.ofNullable(idToSettlement.get(settlementId));
    }

    /**
     * Find {@link District} with {@code districtId} in map
     *
     * @param districtId id of district
     * @return return Optional with {@link District if exists} or empty Optional otherwise.
     */
    public Optional<District> findInDistrict(int districtId) {
        return Optional.ofNullable(idToDistrict.get(districtId));
    }

    /**
     * Find {@link State} with {@code stateId} in map
     *
     * @param stateId id of state
     * @return return Optional with {@link State if exists} or empty Optional otherwise.
     */
    public Optional<State> findInState(int stateId) {
        return Optional.ofNullable(idToState.get(stateId));
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

    /**
     * Add district to map of districts and to {@link State} attribute
     *
     * If indicated through {@code allowEqualNames} checks whether district name is unique. If so then checks whether
     * state with specified {@code stateId} exists. If so then create new instance of District and add as specified
     * above.
     *
     * @param districtName is string with name of district
     * @param stateId is id of state
     * @param allowEqualNames if true then equal names for district are permited
     * @return optional object with integer ID of new district, if creation successfull. Empty optional othervise.
     */
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
        newDistrict.setBelongsToState(stateId);
        final int newDistrictId = newDistrict.getId();
        state.addDistrict(newDistrictId);
        idToDistrict.put(newDistrictId, newDistrict);
        return Optional.of(newDistrictId);
    }

    /**
     * Call method {@code #addDistrict} with last parameter set to false.
     *
     * @param districtName
     * @param stateId
     * @return
     */
    public Optional<Integer> addDistrict(String districtName, int stateId) {
        return addDistrict(districtName, stateId, false);
    }

    /**
     * Add settlement to map of settlements and to {@link District} attribute of {@link Settlement} instance
     *
     * If indicated through {@code #allowEqualNames} checks whether settlement name is unique. If so then checks whether
     * district with specified {@code districtId} exists. If so then create new instance of {@link Settlement} and add
     * as specified above.
     *
     * @param settlementName is string with name of settlement
     * @param districtId is id of district
     * @param allowEqualNames if true then equal names for settlements are permitted
     * @return optional object with integer ID of new district, if creation successfull. Empty optional othervise.
     */
    public Optional<Integer> addSettlement(String settlementName, int districtId, boolean allowEqualNames) {
        Optional<Integer> result = Optional.empty();
        if (!allowEqualNames && !isNewNameUnique(idToSettlement.values(), settlementName)) {
            LOG.info("New settlement isn't created because specified name - {} - already exists.", settlementName);
            return result;
        }
        final District district = idToDistrict.get(districtId);
        if (district == null) {
            LOG.info("District with id {} doesn't exist.", districtId);
            return result;
        }
        final Settlement newSettlement = new Settlement(settlementName);
        newSettlement.setBelongsToDistrict(districtId);
        final int newSettlementId = newSettlement.getId();
        district.addSettlement(newSettlementId);
        idToSettlement.put(newSettlementId, newSettlement);
        return Optional.of(newSettlementId);
    }

    public Optional<Integer> addSettlement(String settlementName, int districtId) {
        return addSettlement(settlementName, districtId, false);
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
