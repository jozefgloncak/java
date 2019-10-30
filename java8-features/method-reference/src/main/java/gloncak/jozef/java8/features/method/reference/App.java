package gloncak.jozef.java8.features.method.reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) {
        String[] ADMINISTRATIVE_UNITS = new String[]{"Martin", "Banska Bystrica", "Braislava", "Kosice"};
        AdministrationUnitManager manager = new AdministrationUnitManager();

        //reference to method of instance
        LOG.info("* Reference to instance method");
        Arrays.stream(ADMINISTRATIVE_UNITS)
            .forEach(manager::processAdministrationUnit);

         //reference to method of class (static method)
        LOG.info("* Reference to static method (class method)");
        Arrays.stream(ADMINISTRATIVE_UNITS)
            .forEach(AdministrationUnitManager::processStaticalyAdministrationUnit);

        //reference to method of instance from class type
        LOG.info("* Reference to instance method from class type.");
        Arrays.stream(ADMINISTRATIVE_UNITS)
            .map(String::toUpperCase) //elements in stream are of type String.
            .forEach(LOG::info);

         //reference to constructor
        LOG.info("* Reference to constructor");
        Arrays.stream(ADMINISTRATIVE_UNITS)
                .map(AdministrativeUnit::new) //reference to constructor
                .map(AdministrativeUnit::toString)  //reference to method of instance from class type
                .forEach(LOG::info);  //reference to instance method
    }
}
