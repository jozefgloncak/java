package gloncak.jozef.design.pattern.command.impl.person;

import gloncak.jozef.design.pattern.command.api.command.Archivator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Model for real person.
 *
 * It is used to demonstrate usage of command design pattern
 */
public class Person {

    private static final Logger LOG = LoggerFactory.getLogger(Person.class);

    /**
     * Destroy person.
     *
     * In implementation there is just log as placeholder for real destroy activities (e. g. destroy some other inner
     * instances of other objects, unregistering from subscriptions.....
     * @param archivator
     */
    public void destroy(Archivator archivator) {
        LOG.info("Destroying inner instances in Person");
        archivator.archive();
        LOG.info("Final destroyng activities.");
    }
}
