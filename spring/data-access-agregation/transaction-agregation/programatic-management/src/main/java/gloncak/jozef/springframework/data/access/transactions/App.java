package gloncak.jozef.springframework.data.access.transactions;

import gloncak.jozef.springframework.data.access.transactions.impl.StudentMarks;
import gloncak.jozef.springframework.data.access.transactions.impl.template.StudentJDBCTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.stream.Stream;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    private static final Object[][] INIT_RECORDS = new Object[][] {
            {1, "Adam", 15, 55, 2015}
            ,{2, "Barbora", 19, 44, 2015}
            ,{3, "Adam", 35, 17, 2016}
            ,{4, "Cyril", 19, 17, 2017}
    };

    public static void main( String[] args ) {
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("Beans.xml");
        StudentJDBCTemplate studentJDBCTemplate = appCtx.getBean("studentJDBCTemplate", StudentJDBCTemplate.class);

        LOG.info("Delete all data from tables STUDENT and MARKS");
        studentJDBCTemplate.deleteAllTableContent();

        Stream.of(INIT_RECORDS)
                .forEach(initRecord -> {
                    LOG.info("Insert data for {} to STUDENT and MARKS tables.", initRecord[0]);
                    studentJDBCTemplate.create(
                            (int)initRecord[0], (String)initRecord[1], (int)initRecord[2], (int)initRecord[3],
                            (int)initRecord[4]);
                });

        LOG.info("Retreiving data from DB");
        List<StudentMarks> studentMarks = studentJDBCTemplate.listStudents();
        studentMarks.forEach(studentMark -> {
            LOG.info(" retreived: name {}, age {}, marks {}, year {}", studentMark.getName(), studentMark.getAge(),
                    studentMark.getMarks(), studentMark.getYear());
        });

    }
}
