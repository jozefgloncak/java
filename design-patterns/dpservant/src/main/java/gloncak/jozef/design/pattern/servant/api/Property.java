package gloncak.jozef.design.pattern.servant.api;

/**
 * Interface which needs to be implemented if instance should be used with
 * {@link gloncak.jozef.design.pattern.servant.pattern.InsuranceCounter}
 *
 */
public interface Property {
    /**
     * How big (in m2) is property
     *
     * @return integer (m2)
     */
    int getArea();

    /**
     * Which Location does property belong to
     *
     * @return concrete {@link Location}
     */
    Location getLocation();

    /**
     * How old is property.
     *
     * @return age of property
     */
    int getAge();
}
