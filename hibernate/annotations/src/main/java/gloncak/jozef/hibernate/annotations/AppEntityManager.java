package gloncak.jozef.hibernate.annotations;

import gloncak.jozef.hibernate.annotations.entity.Car;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AppEntityManager {

    public static void main(String[] args) {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("cars");
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        Car car = new Car("TT155AB", "white", 1000);
        em.persist(car);
        em.getTransaction().commit();
        em.close();
    }
}
