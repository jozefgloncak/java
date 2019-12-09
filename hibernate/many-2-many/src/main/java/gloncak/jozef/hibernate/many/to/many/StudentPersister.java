package gloncak.jozef.hibernate.many.to.many;

import gloncak.jozef.hibernate.many.to.many.entity.Student;
import gloncak.jozef.hibernate.many.to.many.entity.Subject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class StudentPersister {

    private SessionFactory sessionFactory;

    public StudentPersister(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    int addStudent(String name, Set<Subject> subjects) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Integer id = null;
        try {
            Student newStudent = new Student(name, subjects);
            id = (int) session.save(newStudent);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return id;
    }

    List<Student> listStudents() {
        List<Student> result = new ArrayList<>();

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            result = session.createQuery("from Student", Student.class).list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return result;
    }

    List getStateOfStudentToSubjectTable() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM STUDENT_SUBJECT;");
            return nativeQuery.getResultList();
        } catch(HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return Collections.emptyList();
    }


    public void deleteStudent(int studentId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Student studentToDelete = session.get(Student.class, studentId);
            session.delete(studentToDelete);
            tx.commit();
        } catch(HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }


    public List<Subject> listSubjects() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            return session.createQuery("from Subject", Subject.class).list();
        } catch(HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return Collections.emptyList();
    }


}
