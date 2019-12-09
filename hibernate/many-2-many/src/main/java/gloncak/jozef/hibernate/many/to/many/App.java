package gloncak.jozef.hibernate.many.to.many;

import gloncak.jozef.hibernate.many.to.many.entity.Student;
import gloncak.jozef.hibernate.many.to.many.entity.Subject;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    private static final Subject math = new Subject("Math");
    private static final Subject english = new Subject("English");
    private static final Subject literature = new Subject("Literature");

    public static void main(String[] args) {
        Configuration configure =  new Configuration().configure();
        SessionFactory sessionFactory = configure.buildSessionFactory();


        StudentPersister studentPersister = new StudentPersister(sessionFactory);
        stateOfStudentSubjectTable(studentPersister);

        LOG.info("Insert student Marian");
        int marianStudentId = studentPersister.addStudent("Marian", subjectsSet(math, english));
        stateOfStudentSubjectTable(studentPersister);

        LOG.info("Insert student Zoltan");
        int zoltanStudentId = studentPersister.addStudent("Zoltan", subjectsSet(english, literature));
        stateOfStudentSubjectTable(studentPersister);

        LOG.info("Insert student Adam");
        int adamStudentId = studentPersister.addStudent("Adam", subjectsSet(math, english, literature));
        stateOfStudentSubjectTable(studentPersister);

        List<Student> students = studentPersister.listStudents();
        students.forEach(student -> LOG.info("- {}", student));

        LOG.info("Delete student Zoltan with id {}", zoltanStudentId);
        studentPersister.deleteStudent(zoltanStudentId);
        stateOfStudentSubjectTable(studentPersister);

        LOG.info("Delete student Adam with id {}", adamStudentId);
        studentPersister.deleteStudent(adamStudentId);
        stateOfStudentSubjectTable(studentPersister);
        studentPersister.listSubjects().forEach(subject -> LOG.info("- {}", subject));
    }

    private static void stateOfStudentSubjectTable(StudentPersister studentPersister) {
        List result = studentPersister.getStateOfStudentToSubjectTable();
        LOG.info("Native table STUDENT_SUBJECT - contains student id, subject id couples");
        if (result.isEmpty()) {
            LOG.info("- TABLE IS EMPTY");
        }
        result.forEach(entry -> LOG.info("- {}", entry));
    }

    private static Set<Subject> subjectsSet(Subject ... subjects) {
        HashSet<Subject> result = new HashSet<>();
        Stream.of(subjects).forEach(result::add);
        return result;
    }
}
