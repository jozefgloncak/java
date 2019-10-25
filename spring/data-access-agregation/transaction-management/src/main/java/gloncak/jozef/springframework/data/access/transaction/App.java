package gloncak.jozef.springframework.data.access.transaction;

import gloncak.jozef.springframework.data.access.transaction.api.StudentDAO;
import gloncak.jozef.springframework.data.access.transaction.impl.StudentMarks;
import gloncak.jozef.springframework.data.access.transaction.impl.template.StudentJDBCDeclarativeManagementTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    private static final Object[][] INIT_RECORDS = new Object[][]{{1, "Adam", 15, 55, 2015}, {2, "Barbora", 19, 44,
            2015}, {3, "Adam", 35, 17, 2016}, {4, "Cyril", 19, 17, 2017}};

    private static final String[] XMLBeans = new String[]{
            "BeansProgramaticTxManagement.xml",
            "BeansDeclarativeTxManagement.xml"};

    public static void main(String[] args) {
        Arrays.stream(XMLBeans).forEach(xmlBean -> {
            LOG.info("======Running application for {} configuration bean.", xmlBean);
            ApplicationContext appCtx = new ClassPathXmlApplicationContext(xmlBean);
            StudentDAO studentDAO = appCtx.getBean("studentJDBCTemplate", StudentDAO.class);

            LOG.info("Delete all data from tables STUDENT and MARKS");
            studentDAO.deleteAllTableContent();

            if (xmlBean.equals("BeansDeclarativeTxManagement.xml")) {
                for (int i = 0; i < INIT_RECORDS.length; i++) {
                    if (i + 1 == INIT_RECORDS.length) { //simulating error during entering data
                        try {
                            ((StudentJDBCDeclarativeManagementTemplate) studentDAO).createWithException();
                        } catch (RuntimeException e) {
                            LOG.info("Not inserted data for {}.", INIT_RECORDS[i][0]);
                        }
                    } else {
                        LOG.info("Insert data for {} to STUDENT and MARKS tables.", INIT_RECORDS[i][0]);
                        studentDAO.create((int) INIT_RECORDS[i][0], (String) INIT_RECORDS[i][1],
                                (int) INIT_RECORDS[i][2], (int) INIT_RECORDS[i][3], (int) INIT_RECORDS[i][4]);
                    }
                }
            } else {
                Stream.of(INIT_RECORDS).forEach(initRecord -> {
                    LOG.info("Insert data for {} to STUDENT and MARKS tables.", initRecord[0]);
                    studentDAO.create((int) initRecord[0], (String) initRecord[1], (int) initRecord[2],
                            (int) initRecord[3], (int) initRecord[4]);
                });
            }

            LOG.info("Retreiving data from DB");
            List<StudentMarks> studentMarks = studentDAO.listStudents();
            studentMarks.forEach(studentMark -> {
                LOG.info(" retreived: name {}, age {}, marks {}, year {}", studentMark.getName(),
                        studentMark.getAge(), studentMark.getMarks(), studentMark.getYear());
            });
        });

    }
}
