package gloncak.jozef.design.pattern.servant.pattern;

import gloncak.jozef.design.pattern.servant.api.Location;
import gloncak.jozef.design.pattern.servant.api.Property;

/**
 * Servant which provide service of counting insurance
 */
public class InsuranceCounter {

    private static final double RATE_M_2 = 5;

    /**
     * Count insurance according to partial data from {@code property} instance.
     *
     * Calculation is fiction, isn't real.
     *
     * @param property
     * @return insurance amount
     */
    public double countInsurance(Property property) {
        int age = property.getAge();
        int area = property.getArea();
        double coeficient = property.getLocation().getCoeficient();
        double ageCoeficient = 0.5;
        if (age < 10) {
            ageCoeficient = 1;
        } else if (age >= 10 && age < 20) {
            ageCoeficient = 0.8;
        } else if (age >= 20 && age < 30) {
            ageCoeficient = 0.6;
        }

        return RATE_M_2 * area * coeficient * ageCoeficient;
    }
}
