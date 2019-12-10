package gloncak.jozef.hibernate.annotations;

import gloncak.jozef.hibernate.annotations.entity.Car;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        StandardServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(Car.class);
        Metadata metadata = metadataSources.getMetadataBuilder().build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        CarPersister carPersister = new CarPersister(sessionFactory);

        LOG.info("Insert car BB100FE");
        int car1 = carPersister.addCar("BB100FE", 400, "black");

        LOG.info("Insert car BA100AA");
        int car2 = carPersister.addCar("BA100AA", 700, "yellow");

        LOG.info("Cars from DB");
        carPersister.listCars().forEach(car -> LOG.info("- {}", car));

        LOG.info("Delete car BB100FE");
        carPersister.deleteCar(car1);

        LOG.info("Cars from DB");
        carPersister.listCars().forEach(car -> LOG.info("- {}", car));
    }

}
