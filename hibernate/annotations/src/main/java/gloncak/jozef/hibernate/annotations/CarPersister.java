package gloncak.jozef.hibernate.annotations;

import gloncak.jozef.hibernate.annotations.entity.Car;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CarPersister {
    SessionFactory sessionFactory;

    public CarPersister(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    <T, R> R commonMethod(BiFunction<T, Session, R> customFunction, T input) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            R functionApply = customFunction.apply(input, session);
            tx.commit();
            return functionApply;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return null;
    }

    Integer addCar(String plate, int volume, String colour) {
        Car car = new Car(plate, colour, volume);
        return commonMethod(
                (carInput, session) -> (Integer) session.save(carInput),
                car);
    }

    List<Car> listCars() {
        return commonMethod(
                (dummyValue, session) -> session.createQuery("from Car", Car.class).list(),
                null);
    }

    void deleteCar(Integer carID) {
        commonMethod(
                (id, session) -> {
                    Car carToDelete = session.get(Car.class, id);
                    session.delete(carToDelete);
                    return null;
                },
                carID);
    }
}
