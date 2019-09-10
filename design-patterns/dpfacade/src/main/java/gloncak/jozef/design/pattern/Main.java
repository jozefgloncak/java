package gloncak.jozef.design.pattern;

import gloncak.jozef.design.pattern.impl.administrative.AdministrativeUnits;

import java.util.Optional;

/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        AdministrativeUnits administrativeUnits = new AdministrativeUnits();
        Optional<Integer> slovakia = administrativeUnits.addState("Slovakia");
        administrativeUnits.addState("Slovakia");

        if (slovakia.isPresent()) {
            administrativeUnits.addDistrict("Banska Bystrica", slovakia.get());
            administrativeUnits.addDistrict("Ruzomberok", slovakia.get());
        }
        System.out.println(administrativeUnits);
    }
}
