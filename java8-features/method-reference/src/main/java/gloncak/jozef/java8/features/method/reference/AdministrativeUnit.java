package gloncak.jozef.java8.features.method.reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class AdministrativeUnit {
    private static final Logger LOG = LoggerFactory.getLogger(AdministrativeUnit.class);
    private static int counter = 0;
    private int id;
    private String name;

    public AdministrativeUnit(String name) {
        this.id = counter++;
        this.name = name;
    }

    void provideAdministrativeUnitDetails() {
        LOG.info("{}: {}", id, name);
    }

    @Override
    public String toString() {
        return "AdministrativeUnit{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
