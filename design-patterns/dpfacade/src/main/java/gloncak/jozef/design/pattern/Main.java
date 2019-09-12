package gloncak.jozef.design.pattern;

import gloncak.jozef.design.pattern.impl.administrative.AdministrativeUnits;
import gloncak.jozef.design.pattern.impl.facade.CitizenInfoFacade;
import gloncak.jozef.design.pattern.impl.citizen.Citizens;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class Main {
    public static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        //creation of dummy backend system
        AdministrativeUnits administrativeUnits = generateDataToAdministrativeSubsystem();
        Citizens citizens = generateDataToCitizens();

        //creation of facade for backend system
        CitizenInfoFacade citizenInfoFacade = new CitizenInfoFacade(administrativeUnits, citizens);

        //accessing data from backend system through facade
        LOG.info(citizenInfoFacade.getFullCitizenInfo(1).toString());
        LOG.info(citizenInfoFacade.getFullCitizenInfo(2).toString());
        LOG.info(citizenInfoFacade.getFullCitizenInfo(3).toString());
        LOG.info(citizenInfoFacade.getFullCitizenInfo(4).toString());
        LOG.info(citizenInfoFacade.getFullCitizenInfo(5).toString());
    }

    /**
     * Generate example data for demonstration.
     *
     * @return instance of {@link AdministrativeUnits} class.
     */
    private static AdministrativeUnits generateDataToAdministrativeSubsystem() {
        final AdministrativeUnits administrativeUnits = new AdministrativeUnits();
        Optional<Integer> slovakia = administrativeUnits.addState("Slovakia");
        administrativeUnits.addState("Slovakia");

        if (slovakia.isPresent()) {
            Optional<Integer> bbDistrict = administrativeUnits.addDistrict("Banska Bystrica", slovakia.get());
            Optional<Integer> rkDistrict = administrativeUnits.addDistrict("Ruzomberok", slovakia.get());

            if (bbDistrict.isPresent()) {
                administrativeUnits.addSettlement("Kordiky", bbDistrict.get());
                administrativeUnits.addSettlement("Kraliky", bbDistrict.get());
                administrativeUnits.addSettlement("Turecka", bbDistrict.get());
                administrativeUnits.addSettlement("Stare Hory", bbDistrict.get());
            }

            if (rkDistrict.isPresent()) {
                administrativeUnits.addSettlement("Likavka", rkDistrict.get());
            }
        }
        return administrativeUnits;
    }

    /**
     * Generate example data for demonstration
     *
     * @return instance of {@link Citizens} class
     */
    private static Citizens generateDataToCitizens() {
        final Citizens citizens = new Citizens();
        citizens.addCitizen("Adam Adamovic").setPermanentStay(1);
        citizens.addCitizen("Beata Blanarovicova").setPermanentStay(2);
        citizens.addCitizen("Cyril Cesnak").setPermanentStay(3);
        citizens.addCitizen("Denisa Debnarova").setPermanentStay(5);
        return citizens;
    }
}
