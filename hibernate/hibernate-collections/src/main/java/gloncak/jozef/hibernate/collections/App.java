package gloncak.jozef.hibernate.collections;

import gloncak.jozef.hibernate.collections.entity.Student;
import gloncak.jozef.hibernate.collections.entity.Subject;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SessionFactory sessionFactory;
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            StudentPersistenceManager studentPersistenceManager = new StudentPersistenceManager(sessionFactory);

            LOG.info("adding student Martin to DB");
            Integer martinID = studentPersistenceManager.addStudent("Martin", prepareSujbects());

            Student studentMartin = studentPersistenceManager.readStudent(martinID);
            LOG.info("student Martin from DB");
            LOG.info(" {}", studentMartin);
        } catch (HibernateException e) {
            LOG.info("Exception during creatinb session factory: ", e);
        }
    }

    private static List<Subject> prepareSujbects() {
        return Arrays.asList(new Subject("Math"), new Subject("Literature"), new Subject("English"), new Subject(
                "History"));
    }
}
