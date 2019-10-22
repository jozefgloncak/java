package gloncak.jozef.springframework.pure.jdbc.api;

import gloncak.jozef.springframework.pure.jdbc.impl.Student;

import javax.sql.DataSource;
import java.util.List;

public interface StudentDAO {

    void setDataSource(DataSource dataSource);

    void create(String name, Integer age, Integer id);

    Student getStudent(Integer id);

    List<Student> getStudents();

    void deleteStudent(Integer id);

    void updateStudent(Integer id, Integer age);
}
