package gloncak.jozef.hibernate;

import gloncak.jozef.hibernate.entity.Employer;
import gloncak.jozef.hibernate.entity.Person;
import gloncak.jozef.hibernate.repo.EmployerPersonJoinTableRepository;
import gloncak.jozef.hibernate.repo.EmployerRepository;
import gloncak.jozef.hibernate.repo.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles({"junit_memory"})
public class AppInMemoryDbTest {

    @Autowired
    EmployerRepository employerRepo;

    @Autowired
    PersonRepository personRepo;

    @Autowired
    EmployerPersonJoinTableRepository empPerJoinRepo;

    @Test
//    @Rollback(false)
    public void insertDeleteDBTest() {
        Employer slovnaft = new Employer("SLOVNAFT");

        Person per1 = new Person("Martin Martinkovic");
        Person per2 = new Person("Zoltan Zoltanovic");
        Person per3 = new Person("Valentin Valentovic");

        Employer zsr = new Employer("ZSR");
        zsr.addPerson(per1);
        zsr.addPerson(per2);

        Employer jednota = new Employer("COOP Jednota");
        jednota.addPerson(per1);
        jednota.addPerson(per2);
        jednota.addPerson(per3);

        employerRepo.saveAndFlush(zsr);
        employerRepo.saveAndFlush(slovnaft);
        employerRepo.saveAndFlush(jednota);

        Assertions.assertEquals(3, employerRepo.count());
        Assertions.assertEquals(5, empPerJoinRepo.countEmployerPersonJoinTable());

        employerRepo.delete(slovnaft);
        Assertions.assertEquals(2, employerRepo.count());
        Assertions.assertEquals(5, empPerJoinRepo.countEmployerPersonJoinTable()); //ziadne vazby vo vazobnej tabulke

        employerRepo.delete(zsr);
        Assertions.assertEquals(1, employerRepo.count());
        Assertions.assertEquals(3, empPerJoinRepo.countEmployerPersonJoinTable()); //2 vazby vo vazobnej tabulke
        Assertions.assertEquals(3, personRepo.count()); //3 osoby zostavaju

        employerRepo.delete(jednota);
        Assertions.assertEquals(0, employerRepo.findAll().size());
        Assertions.assertEquals(0, empPerJoinRepo.countEmployerPersonJoinTable()); //3 vazby vo vazobnej tabulke
        Assertions.assertEquals(3, personRepo.count()); //3 osoby, lebo neboli aktivne odmazane

        personRepo.delete(per1);
        personRepo.delete(per2);
        personRepo.delete(per3);
        Assertions.assertEquals(0, personRepo.count()); //ziadne osoby
    }

    @Test
    public void insertDeleteNoOwningSiteDBTest() {

        Person per1 = new Person("Martin Martinkovic");
        Person per2 = new Person("Zoltan Zoltanovic");
        Person per3 = new Person("Valentin Valentovic");
        personRepo.save(per1);
        personRepo.save(per2);
        personRepo.save(per3);

        Employer zsr = new Employer("ZSR");
        Employer jednota = new Employer("COOP Jednota");
        zsr.addPerson(per1);
        zsr.addPerson(per2);
        employerRepo.save(zsr);

        jednota.addPerson(per1);
        jednota.addPerson(per3);
        employerRepo.save(jednota);
        Assertions.assertEquals(4, empPerJoinRepo.countEmployerPersonJoinTable()); //4 vazby vo vazobnej tabulke
        Assertions.assertEquals(3, personRepo.count()); //3 osoby, lebo neboli aktivne odmazane
        Assertions.assertEquals(2, employerRepo.count()); //3 osoby, lebo neboli aktivne odmazane

        //mazanie z no owning side
        per1.removeAllEmployers(); //clear person1 from join table
        personRepo.delete(per1);  //deletion successful
        Assertions.assertEquals(2, empPerJoinRepo.countEmployerPersonJoinTable()); //2 vazby prec z vazobnej tabulky
        Assertions.assertEquals(2, personRepo.count()); //1 osoba vymazana

        personRepo.delete(per2); //this deletion silently (ne exception) unsuccessful (exists connection in join table)
        Assertions.assertEquals(2, empPerJoinRepo.countEmployerPersonJoinTable()); //4 vazby vo vazobnej tabulke
        Assertions.assertEquals(2, personRepo.count()); //1 osoba vymazana
        Assertions.assertEquals(2, employerRepo.count()); //zamestnanci sa nemazali

    }

}
