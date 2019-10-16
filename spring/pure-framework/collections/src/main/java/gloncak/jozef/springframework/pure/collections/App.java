package gloncak.jozef.springframework.pure.collections;

import gloncak.jozef.springframework.pure.collections.beans.Car;
import gloncak.jozef.springframework.pure.collections.beans.CarManager;
import gloncak.jozef.springframework.pure.collections.beans.CarType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class App {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("Beans.xml");
        CarManager carManager = appCtx.getBean("carManager", CarManager.class);

        Map<String, Car> cars = carManager.getCars();
        LOG.info("injected cars");
        cars.forEach((carKey, carValue) -> LOG.info("- {}", carValue));

        Set<CarType> carTypes = carManager.getCarTypes();
        LOG.info("car typesl");
        carTypes.forEach(carType -> LOG.info("- {}", carType));

        LOG.info("drivers");
        List<String> driversID = carManager.getDriversID();
        driversID.forEach(driverID -> LOG.info("- {}", driversID));
    }
}
