package gloncak.jozef.design.pattern;

import gloncak.jozef.design.pattern.impl.administrative.AdministrativeUnits;
import gloncak.jozef.design.pattern.impl.person.Citizens;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        AdministrativeUnits administrativeUnits = generateDataToAdministrativeSubsystem();
        Citizens citizens = generateDataToCitizens();
        System.out.println(administrativeUnits);
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
