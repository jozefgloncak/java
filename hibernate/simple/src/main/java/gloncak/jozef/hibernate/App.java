package gloncak.jozef.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static SessionFactory factory;
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        try {
            factory = new Configuration().configure().buildSessionFactory();
            AnimalManager animalManager = new AnimalManager(factory);
            animalManager.deleteAll();
            LOG.info("animals from backend DB (all deleted)");
            animalManager.listAnimals().forEach(animal -> LOG.info(" {}", animal));

            LOG.info("inserting one animal - Murko");
            Integer animalID = animalManager.addAnimal("Murko", (short) 2, Gender.MALE);

            LOG.info("animals from backend DB");
            animalManager.listAnimals().forEach(animal -> LOG.info(" {}", animal));

            LOG.info("updating Murko's age to value 7");
            animalManager.updateAge(animalID, (short) 7);

            LOG.info("animals from backend DB (after age update)");
            animalManager.listAnimals().forEach(animal -> LOG.info(" {}", animal));
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

}
