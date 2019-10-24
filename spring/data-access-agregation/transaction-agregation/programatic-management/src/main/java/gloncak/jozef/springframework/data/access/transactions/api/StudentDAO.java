package gloncak.jozef.springframework.data.access.transactions.api;

import gloncak.jozef.springframework.data.access.transactions.impl.StudentMarks;

import javax.sql.DataSource;
import java.util.List;

public interface StudentDAO {

        void setDataSource(DataSource ds);

        void deleteAllTableContent();

        void create(Integer id, String name, Integer age, Integer marks, Integer year);

        List<StudentMarks> listStudents();
}
