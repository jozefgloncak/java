package gloncak.jozef.springframework.pure.jdbc.impl.template;

import gloncak.jozef.springframework.pure.jdbc.api.StudentDAO;
import gloncak.jozef.springframework.pure.jdbc.impl.Student;
import gloncak.jozef.springframework.pure.jdbc.impl.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class StudentJDBCTemplate implements StudentDAO {
    private static final Logger LOG = LoggerFactory.getLogger(StudentJDBCTemplate.class);

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(String name, Integer age, Integer id) {
        String SQL = "insert into STUDENT (name, age, id) values (?, ?, ?)";
        jdbcTemplate.update(SQL, name, age, id);
        LOG.info(" Created new Student name: {} age: {}.", name, age);
    }

    @Override
    public Student getStudent(Integer id) {
        String SQL = "select * from STUDENT where id = ?";
        Student student = jdbcTemplate.queryForObject(SQL, new Object[]{id}, new StudentMapper());
        return student;
    }

    @Override
    public List<Student> getStudents() {
        String SQL = "select * from STUDENT";
        return jdbcTemplate.query(SQL, new StudentMapper());
    }

    @Override
    public void deleteStudent(Integer id) {
        String SQL = "delete from STUDENT";
        jdbcTemplate.update(SQL);
    }

    @Override
    public void updateStudent(Integer id, Integer age) {
        String SQL = "update STUDENT set age = ? where id = ?";
        jdbcTemplate.update(SQL, age, id);

    }
}
