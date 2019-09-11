package gloncak.jozef.design.pattern.impl.facade;

import gloncak.jozef.design.pattern.impl.administrative.AdministrativeUnits;
import gloncak.jozef.design.pattern.impl.person.Citizens;
import gloncak.jozef.design.pattern.impl.person.Person;

import java.util.Optional;

/**
 * Represents facade on system of evidence of administrative units and citizens
 */
public class PersonInfoFacade {
    final private AdministrativeUnits administrativeUnits;
    private Citizens citizens;

    public PersonInfoFacade(AdministrativeUnits administrativeUnits, Citizens citizens) {
        this.administrativeUnits = administrativeUnits;
        this.citizens = citizens;
    }

    /**
     * Aggregate complex information for citizen.
     *
     * @return instance of {@link PersonInfoDTO} with complex information about person.
     */
    public PersonInfoDTO getFullPersonInfo(int personId) {
        final PersonInfoDTO personInfoDTO = new PersonInfoDTO();
        Optional<Person> citizen = citizens.findCitizen(personId);
        if (citizen.isPresent()) {
            administrativeUnits.citizen.get().getPermanentStay();
        }
        return personInfoDTO;
    }
}
