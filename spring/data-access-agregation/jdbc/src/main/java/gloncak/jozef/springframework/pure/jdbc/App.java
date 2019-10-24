package gloncak.jozef.springframework.pure.jdbc;

import gloncak.jozef.springframework.pure.jdbc.impl.Student;
import gloncak.jozef.springframework.pure.jdbc.impl.template.StudentJDBCTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class App {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) {
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("Beans.xml");
        StudentJDBCTemplate studentJDBCTemplate = appCtx.getBean("studentJDBCTemplate", StudentJDBCTemplate.class);

        LOG.info("method deleteStudents()");
        studentJDBCTemplate.deleteStudent(4);

        LOG.info("method create()");
        studentJDBCTemplate.create("Martin", 45, 1);
        studentJDBCTemplate.create("Monika", 34, 2);

        LOG.info("method getStudent() - return one concrete record");
        int studentID = 1;
        Student student = studentJDBCTemplate.getStudent(studentID);
        LOG.info(" student {}.", student);

        LOG.info("method getStudents() - return all records");
        List<Student> students = studentJDBCTemplate.getStudents();
        students.forEach(studentIn ->  LOG.info(" student {}.", studentIn));

        LOG.info("method update() - update specific row");
        studentJDBCTemplate.updateStudent(1, 20);

        LOG.info("method getStudent()");
        student = studentJDBCTemplate.getStudent(1);
        LOG.info(" student {}.", student);
    }
}
