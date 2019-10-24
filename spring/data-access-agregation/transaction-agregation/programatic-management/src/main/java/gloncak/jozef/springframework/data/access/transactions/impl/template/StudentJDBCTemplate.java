package gloncak.jozef.springframework.data.access.transactions.impl.template;

import gloncak.jozef.springframework.data.access.transactions.api.StudentDAO;
import gloncak.jozef.springframework.data.access.transactions.impl.StudentMarks;
import gloncak.jozef.springframework.data.access.transactions.impl.mapper.StudentMarksMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.util.List;

public class StudentJDBCTemplate implements StudentDAO {

    private static final Logger LOG = LoggerFactory.getLogger(StudentJDBCTemplate.class);
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private PlatformTransactionManager transactionManager;

    public void setTransactionManager(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public void deleteAllTableContent() {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);

        try {
            String freeStudentTableSQL = "delete from STUDENT";
            jdbcTemplate.update(freeStudentTableSQL);

            String freeMarksTableSQL = "delete from MARKS";
            jdbcTemplate.update(freeMarksTableSQL);

            transactionManager.commit(txStatus);
            LOG.info(" Data from tables STUDENT and MARKS deleted successfully");
        } catch(DataAccessException e) {
            transactionManager.rollback(txStatus);
            LOG.info(" Data from tables STUDENT and MARKS WEREN'T deleted.");
        }
    }

    @Override
    public void create(Integer id, String name, Integer age, Integer marks, Integer year) {
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        try {
            String studentSQL = "insert into STUDENT(id, name, age) values (?, ?, ?)";
            jdbcTemplate.update(studentSQL, id, name, age);

            String SQLGetMaxID = "select max(id) from STUDENT";
            int newStudentID = jdbcTemplate.queryForObject(SQLGetMaxID, Integer.class);

            String marksSQL = "insert into MARKS(marks, sid, year) values(?, ?, ?)";
            jdbcTemplate.update(marksSQL, marks, newStudentID, year);

            transactionManager.commit(txStatus);
        } catch (DataAccessException e) {
            transactionManager.rollback(txStatus);
            throw e;
        }
    }

    @Override
    public List<StudentMarks> listStudents() {
        String SQL = "select * from STUDENT, MARKS where id = sid";
        return jdbcTemplate.query(SQL, new StudentMarksMapper());
    }
}
