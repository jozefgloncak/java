package gloncak.jozef.hibernate.collections;

import gloncak.jozef.hibernate.collections.entity.Student;
import gloncak.jozef.hibernate.collections.entity.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class StudentPersistenceManager {
    private SessionFactory sessionFactory;

    StudentPersistenceManager(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    Integer addStudent(String studentName, List<Subject> subjects) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Integer newStudentId = null;
        try {
            Student newStudent = new Student(studentName, subjects);
            newStudentId = (int) session.save(newStudent);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return newStudentId;
    }

    public Student readStudent(Integer studentID) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Student readStudent = null;
        try {
            readStudent = session.get(Student.class, studentID);
            tx.commit();
        } catch(HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }

        return readStudent;
    }
}
