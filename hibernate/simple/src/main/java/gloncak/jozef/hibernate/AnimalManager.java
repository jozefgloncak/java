package gloncak.jozef.hibernate;

import gloncak.jozef.hibernate.entity.Animal;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class AnimalManager {
    private final SessionFactory factory;

    public AnimalManager(SessionFactory factory) {
        this.factory = factory;
    }

    public Integer addAnimal(String name, Short age, boolean gender){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer animalID = null;

        try {
            tx = session.beginTransaction();
            Animal animal = new Animal(name, age, gender);
            animalID = (Integer) session.save(animal);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return animalID;
    }

    List<Animal> listAnimals() {
        Session session = factory.openSession();
        Transaction tx = null;
        List<Animal> result = Collections.emptyList();
        try {
            tx = session.beginTransaction();
            return session.createQuery("FROM Animal", Animal.class).list();
        } catch (HibernateException e) {

        } finally {
            session.close();
        }
        return null;
    }

    public void deleteAll() {
        Session session = factory.openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.createQuery("delete from Animal").executeUpdate();
        } catch (HibernateException e) {

        } finally {
            session.close();
        }
    }

    public void updateAge(Integer animalID, short newAge) {
        Session session = factory.openSession();
        try {
            Transaction tx = session.beginTransaction();
            Animal animal = session.get(Animal.class, animalID);
            animal.setAge(newAge);
            session.update(animal);
            tx.commit();
        } catch (HibernateException e) {

        } finally {
            session.close();
        }
    }
}
