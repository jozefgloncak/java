package gloncak.jozef.design.pattern.impl.facade;

import gloncak.jozef.design.pattern.impl.administrative.AdministrativeUnits;
import gloncak.jozef.design.pattern.impl.administrative.unit.District;
import gloncak.jozef.design.pattern.impl.administrative.unit.Settlement;
import gloncak.jozef.design.pattern.impl.administrative.unit.State;
import gloncak.jozef.design.pattern.impl.citizen.Citizens;
import gloncak.jozef.design.pattern.impl.citizen.Citizen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Represents facade on system of evidence of administrative units and citizens
 */
public class CitizenInfoFacade {
    public static final Logger LOG = LoggerFactory.getLogger(CitizenInfoFacade.class);

    final private AdministrativeUnits administrativeUnits;
    private Citizens citizens;

    public CitizenInfoFacade(AdministrativeUnits administrativeUnits, Citizens citizens) {
        this.administrativeUnits = administrativeUnits;
        this.citizens = citizens;
    }

    /**
     * Aggregate complex information for citizen.
     *
     * @return instance of {@link CitizenInfoDTO} with complex information about citizen.
     */
    public Optional<CitizenInfoDTO> getFullCitizenInfo(int citizenId) {
        final CitizenInfoDTO citizenInfoDTO = new CitizenInfoDTO();
        Optional<Citizen> citizenOpt = citizens.findCitizen(citizenId);
        if (citizenOpt.isEmpty()) {
            LOG.warn("No citizen with id {} has been found.", citizenId);
            return Optional.empty();
        }

        final Citizen citizen = citizenOpt.get();
        final Optional<Settlement> settlementOpt = administrativeUnits.findInSettlement(citizen.getPermanentStay());
        if (settlementOpt.isEmpty()) {
            LOG.warn("No settlement for citizen {} has been found.", citizen.getName());
            return Optional.empty();
        }

        Settlement settlement = settlementOpt.get();
        final Optional<District> districtOpt = administrativeUnits.findInDistrict(settlement.getBelongsToDistrict());
        if (districtOpt.isEmpty()) {
            LOG.warn("No district for citizen {} has beeen found.", citizen.getName());
            return Optional.empty();
        }

        final District district = districtOpt.get();
        final Optional<State> stateOpt = administrativeUnits.findInState(district.getBelongsToState());
        if (stateOpt.isEmpty()) {
            LOG.warn("No state for citizen {} has been found.", citizen.getName());
            return Optional.empty();
        }

        final State state = stateOpt.get();

        citizenInfoDTO.setName(citizen.getName());
        citizenInfoDTO.setPermanentStay(settlement.getName());
        citizenInfoDTO.setDistrict(district.getName());
        citizenInfoDTO.setState(state.getName());

        return Optional.of(citizenInfoDTO);
    }

}
