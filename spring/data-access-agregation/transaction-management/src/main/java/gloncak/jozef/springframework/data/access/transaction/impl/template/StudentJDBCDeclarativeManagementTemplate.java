package gloncak.jozef.springframework.data.access.transaction.impl.template;

import gloncak.jozef.springframework.data.access.transaction.api.StudentDAO;
import gloncak.jozef.springframework.data.access.transaction.api.StudentDAOWithExceptions;
import gloncak.jozef.springframework.data.access.transaction.impl.StudentMarks;
import gloncak.jozef.springframework.data.access.transaction.impl.mapper.StudentMarksMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class StudentJDBCDeclarativeManagementTemplate implements StudentDAO, StudentDAOWithExceptions {

    private static final Logger LOG = LoggerFactory.getLogger(StudentJDBCDeclarativeManagementTemplate.class);
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;


    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public void deleteAllTableContent() {
         try {
            String freeStudentTableSQL = "delete from STUDENT";
            jdbcTemplate.update(freeStudentTableSQL);

            String freeMarksTableSQL = "delete from MARKS";
            jdbcTemplate.update(freeMarksTableSQL);

            LOG.info(" Data from tables STUDENT and MARKS deleted successfully");
        } catch(DataAccessException e) {
            LOG.info(" Data from tables STUDENT and MARKS WEREN'T deleted.");
        }
    }

    @Override
    public void create(Integer id, String name, Integer age, Integer marks, Integer year) {
        try {
            String studentSQL = "insert into STUDENT(id, name, age) values (?, ?, ?)";
            jdbcTemplate.update(studentSQL, id, name, age);

            String SQLGetMaxID = "select max(id) from STUDENT";
            int newStudentID = jdbcTemplate.queryForObject(SQLGetMaxID, Integer.class);

            String marksSQL = "insert into MARKS(marks, sid, year) values(?, ?, ?)";
            jdbcTemplate.update(marksSQL, marks, newStudentID, year);

        } catch (DataAccessException e) {
            throw e;
        }
    }

    @Override
    public List<StudentMarks> listStudents() {
        String SQL = "select * from STUDENT, MARKS where id = sid";
        return jdbcTemplate.query(SQL, new StudentMarksMapper());
    }

    @Override
    public void createWithException() {
        throw new RuntimeException("simulate error during database interaction");
    }
}
