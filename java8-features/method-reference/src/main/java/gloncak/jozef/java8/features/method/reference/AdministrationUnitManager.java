package gloncak.jozef.java8.features.method.reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class AdministrationUnitManager {
    private static final Logger LOG = LoggerFactory.getLogger(AdministrationUnitManager.class);

    void processAdministrationUnit(String administrationUnit) {
        LOG.info("procesing administration unit {}.", administrationUnit);
    }

    static void processStaticalyAdministrationUnit(String administrationUnit) {
        LOG.info("procesing administration unit STATICALLY {}.", administrationUnit);
    }
}
