package gloncak.jozef.hibernate.annotations;

import gloncak.jozef.hibernate.annotations.entity.Car;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Demonstrate in memory DB testing through JUnit.
 */
public class DBTablesTest {

    private static final Logger LOG = LoggerFactory.getLogger(DBTablesTest.class);

    @Test
    public void insertDataTest() {

        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("cars-test");
        EntityManager em = emFactory.createEntityManager();

        //insert data to DB
        em.getTransaction().begin();
        Car car1 = new Car("TT155AB", "white", 1000);
        Car car2 = new Car("BB200AB", "black", 900);
        em.persist(car1);
        em.persist(car2);
        em.persist(new Car("TT300AB", "black", 300));
        em.getTransaction().commit();

        //read concrete data (specified primary key) from DB
        em.getTransaction().begin();
        Car car1FromDB = em.find(Car.class, car1.getId());
        Assert.assertNotNull(car1FromDB);
        em.getTransaction().commit();

        //read all data from DB
        em.getTransaction().begin();
        List allCars = em.createQuery("FROM Car").getResultList();
        Assert.assertEquals(3, allCars.size());
        em.getTransaction().commit();
        em.close();

        //retreiving through new entity manager
        //cars where plate starts with 'TT'
        EntityManager em2 = emFactory.createEntityManager();
        em2.getTransaction().begin();
        List<Car> carsWithTT = em2.createQuery("FROM Car WHERE plate LIKE 'TT%'").getResultList();
        Assert.assertEquals(2, carsWithTT.size());
        Assert.assertEquals("TT", carsWithTT.get(0).getPlate().substring(0, 2));
        Assert.assertEquals("TT", carsWithTT.get(1).getPlate().substring(0, 2));
        em2.getTransaction().commit();
        em2.close();
    }

    /**
     * Tests that @CreationTimestamp annotated property is generated once entity is persisted.
     */
    @Test
    public void isCreationTimestampGeneratedTest() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("cars-test");
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        Car newCar = new Car("BB231BA", "lemon", 400);
        em.persist(newCar);
        Assert.assertNull(newCar.getCreated());
        em.getTransaction().commit();
        Assert.assertNotNull(newCar.getCreated());
        em.close();
    }

    /**
     * Tests whether @UpdateTimestamp annotated property is regenerated after each update.
     */
    @Test
    public void isUpdateTimestampGeneratedTest() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("cars-test");
        EntityManager em = emFactory.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        Car car = new Car("BR567AA", "green", 600);
        LOG.info("created entity new car {}", car);
        em.persist(car);
        LOG.info("persisting entity new car {}", car);
        Assert.assertNull(car.getLastUpdated());
        tx.commit();
        LOG.info("commit entity new car {}", car);
        LocalDateTime lastUpdated1 = car.getLastUpdated();
        Assert.assertNotNull(lastUpdated1);

        tx.begin();
        car.setColor("violet");
        LOG.info("change color of entity car {}", car);
        tx.commit();
        LOG.info("commit color change of entity car {}", car);
        LocalDateTime lastUpdated2 = car.getLastUpdated();
        Assert.assertTrue(lastUpdated1.isBefore(lastUpdated2));

        em.close();
    }

}
