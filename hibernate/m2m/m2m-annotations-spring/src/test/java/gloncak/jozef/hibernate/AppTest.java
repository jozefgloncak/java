package gloncak.jozef.hibernate;

import gloncak.jozef.hibernate.entity.Employer;
import gloncak.jozef.hibernate.entity.Person;
import gloncak.jozef.hibernate.repo.EmployerRepository;
//import org.junit.Assert;
import gloncak.jozef.hibernate.repo.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {App.class} )
//@EnableJpaRepositories
@DataJpaTest
public class AppTest {

    @Autowired
    EmployerRepository employerRepo;

    @Autowired
    PersonRepository personRepo;
//    @Test
//    @Rollback(false)
////    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//    public void storeDB() {
//        Person person = new Person("Adam Adamovic");
//        Person person2 = new Person("Beata Beatovic");
//        Employer emp1 = new Employer("SOFTIP");
//        emp1.addPerson(person);
//        employerRepo.saveAndFlush(emp1);
//        Integer emp1Id = emp1.getId();
//
//        Optional<Employer> readEmp = employerRepo.findById(emp1Id);
//        Assert.assertTrue(readEmp.isPresent());
//        Assert.assertEquals(1, readEmp.get().getPersons().size());
//
//
//    }
    @Test
    @Rollback(false)
//    @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
    public void storeEmployerWithPersonTest() {
        Person person = new Person("Adam Adamovic");
        Employer emp1 = new Employer("SOFTIP");
        emp1.addPerson(person);
        employerRepo.saveAndFlush(emp1);
        Integer emp1Id = emp1.getId();

        Optional<Employer> readEmp = employerRepo.findById(emp1Id);
        Assertions.assertTrue(readEmp.isPresent());
        Assertions.assertEquals(1, readEmp.get().getPersons().size());
    }

    @Test
    @Rollback(false)
    public void storeOnePersonDB() {
        Person person = new Person("Beata Beatovic");
        personRepo.saveAndFlush(person);
        Integer personId = person.getId();

        Optional<Person> readPerson = personRepo.findById(personId);
        Assertions.assertTrue(readPerson.isPresent());
    }
}
