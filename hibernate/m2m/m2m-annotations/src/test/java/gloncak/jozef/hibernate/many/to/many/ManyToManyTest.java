package gloncak.jozef.hibernate.many.to.many;

import gloncak.jozef.hibernate.many.to.many.entities.first.EmployeerUnidirectA;
import gloncak.jozef.hibernate.many.to.many.entities.first.PersonUnidirectA;
import gloncak.jozef.hibernate.many.to.many.entities.fourth.EmployerBidirectJoin;
import gloncak.jozef.hibernate.many.to.many.entities.fourth.EmployerPersonBidirectJoin;
import gloncak.jozef.hibernate.many.to.many.entities.fourth.PersonBidirectJoin;
import gloncak.jozef.hibernate.many.to.many.entities.second.EmployerUnidirectB;
import gloncak.jozef.hibernate.many.to.many.entities.second.PersonUnidirectB;
import gloncak.jozef.hibernate.many.to.many.entities.third.EmployerBidirect;
import gloncak.jozef.hibernate.many.to.many.entities.third.PersonBidirect;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManyToManyTest {

    /**
     * Collection on site of Employee (owning site)
     *
     * Employee contains information about collection of persons.
     */
    @Test
    public void unidirectManyToManyATest() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("employment");
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        PersonUnidirectA person1 = new PersonUnidirectA("Marian Komaromy");
        PersonUnidirectA person2 = new PersonUnidirectA("Adam Adamovic");
        PersonUnidirectA person3 = new PersonUnidirectA("Beata Burkovic");

        EmployeerUnidirectA pantheon = new EmployeerUnidirectA("Pantheon.tech, s. r. o.");
        EmployeerUnidirectA accenture = new EmployeerUnidirectA("Accenture technologies solution, s. r. o.");
        em.persist(person1);
        em.persist(person2);
        em.persist(person3);

        Set<PersonUnidirectA> personsForPantheon = new HashSet<>();
        personsForPantheon.add(person1);
        personsForPantheon.add(person2);
        pantheon.setPersons(personsForPantheon);


        Set<PersonUnidirectA> personsForAccenture = new HashSet<>();
        personsForAccenture.add(person2);
        personsForAccenture.add(person3);
        accenture.setPersons(personsForAccenture);

        em.persist(pantheon);
        em.persist(accenture);
        List resultList =
                em.createNativeQuery("SELECT * FROM EMPLOYEER_UNIDIRECT_A_PERSON_UNIDIRECT_A;").getResultList();
        Assert.assertEquals(4, resultList.size()); //in link table there are stored all 4 relations person - employer

        em.getTransaction().commit();
        em.getTransaction().begin();

        //deletion on not owning (PERSON) site isn't possible
        List<PersonUnidirectA> persons = em.createQuery("SELECT e FROM PersonUnidirectA e",
                PersonUnidirectA.class).getResultList();
        Assert.assertEquals(  "number of persons (non owning site) before deletion", 3, persons.size());
        em.remove(person1); //try delete not owning site
        persons = em.createQuery("SELECT e FROM PersonUnidirectA e",
                PersonUnidirectA.class).getResultList();
        Assert.assertEquals(  "number of persons (non owning site) after deletion", 3, persons.size());


        //deletion on owning (EMPLOYER) site is possible
        List<EmployeerUnidirectA> employeer = em.createQuery("SELECT e FROM EmployeerUnidirectA e",
                EmployeerUnidirectA.class).getResultList();
        Assert.assertEquals(  "number of employers (owning site) before deletion", 2, employeer.size());
        // (one employeer has been deleted)
        em.remove(accenture);
        employeer = em.createQuery("SELECT e FROM EmployeerUnidirectA e",
                EmployeerUnidirectA.class).getResultList();
        Assert.assertEquals(  "number of employers (owning site) after deletion", 1, employeer.size());


        //state in JOINING (vazobna) tabulka
        resultList = em.createNativeQuery("SELECT * FROM EMPLOYEER_UNIDIRECT_A_PERSON_UNIDIRECT_A;").getResultList();
        Assert.assertEquals(2, resultList.size()); //in link table there are stored all 2 relations person - employer

        em.getTransaction().commit();

        em.close();
    }

    /**
     * Collection on site of Person (owning site).
     *
     * Person contains information about collection of employees.
     */
    @Test
    public void unidirectManyToManyBTest() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("employment");
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        PersonUnidirectB person1 = new PersonUnidirectB("Cecil Cecilovic");
        PersonUnidirectB person2 = new PersonUnidirectB("Adam Adamovic");
        PersonUnidirectB person3 = new PersonUnidirectB("Beata Burkovic");
        PersonUnidirectB person4 = new PersonUnidirectB("Daniel Danielovic");

        EmployerUnidirectB pantheon = new EmployerUnidirectB("Pantheon.tech, s. r. o.");
        EmployerUnidirectB softip = new EmployerUnidirectB("SOFTIP, a. s.");
        EmployerUnidirectB benzinka = new EmployerUnidirectB("SLOVNAFT");
        EmployerUnidirectB zsr = new EmployerUnidirectB("ZSR");
        em.persist(pantheon);
        em.persist(softip);
        em.persist(benzinka);
        em.persist(zsr);


        Set<EmployerUnidirectB> person1Employers = new HashSet<>();
        person1Employers.add(pantheon);
        person1Employers.add(benzinka);
        person1.setEmployers(person1Employers);


        Set<EmployerUnidirectB> persons2Employers = new HashSet<>();
        persons2Employers.add(softip);
        persons2Employers.add(benzinka);
        person2.setEmployers(persons2Employers);

        person3.setEmployers(Set.of(benzinka));

        em.persist(person1);
        em.persist(person2);
        em.persist(person3);
        em.persist(person4);


        //deletion on not owning (EMPLOYER) site isn't possible
        List<EmployerUnidirectB> employers = em.createQuery("SELECT e FROM EmployerUnidirectB e",
                EmployerUnidirectB.class).getResultList();
        Assert.assertEquals(  "number of employers (non owning site) before deletion", 4, employers.size());
        em.remove(benzinka); //try delete not owning site
        employers = em.createQuery("SELECT e FROM EmployerUnidirectB e", EmployerUnidirectB.class).getResultList();
        Assert.assertEquals(  "number of employers (non owning site) after deletion", 4, employers.size());

        //deletion on owning (PERSON) site is possible
        List<PersonUnidirectB> persons = em.createQuery("SELECT p FROM PersonUnidirectB p",
                PersonUnidirectB.class).getResultList();
        Assert.assertEquals(  "number of persons (owning site) before deletion", 4, persons.size());
        // (one employeer has been deleted)
        em.remove(person1);
        persons = em.createQuery("SELECT p FROM PersonUnidirectB p", PersonUnidirectB.class).getResultList();
        Assert.assertEquals(  "number of persons (owning site) after deletion", 3, persons.size());

//        List resultList =
//                em.createNativeQuery("SELECT * FROM PERSON_UNIDIRECT_B_EMPLOYER_UNIDIRECT_B;").getResultList();
//        Assert.assertEquals(5, resultList.size()); //in link table there are stored all 4 relations person - employer

//        em.remove(zsr);
        em.getTransaction().commit();
        em.getTransaction().begin();
//        removeAllReferences(em, benzinka);

        List resultList =
                em.createNativeQuery("SELECT * FROM PERSON_UNIDIRECT_B_EMPLOYER_UNIDIRECT_B;").getResultList();
        List employerList = em.createNativeQuery("SELECT * FROM EMPLOYER_UNIDIRECT_B;").getResultList();
        em.getTransaction().commit();
        Assert.assertEquals(3, resultList.size()); //in link table there are stored all 2 relations person - employer
        // (one employeer has been deleted)


        em.close();
    }

    private void removeAllReferences(EntityManager em, EmployerUnidirectB employer) {
        List<PersonUnidirectB> lst = em.createQuery("SELECT e FROM " +
                "PersonUnidirectB e", PersonUnidirectB.class).getResultList();
        lst.forEach(person -> {
            if (person.getEmployers() != null && person.getEmployers().contains(employer)) {
                Set<EmployerUnidirectB> newHashSet = new HashSet<>(person.getEmployers());
                newHashSet.remove(employer);
                person.setEmployers(newHashSet);
                em.persist(person);
            }
        });
        em.remove(employer);
    }

    @Test
    public void bidirectManyToManyTest() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("employment_bidirectional");
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();

        PersonBidirect person1 = new PersonBidirect("Cyril Cyrilovic");
        PersonBidirect person2 = new PersonBidirect("David Davidovic");
        PersonBidirect person3 = new PersonBidirect("Emil Emilovic");
        PersonBidirect person4 = new PersonBidirect("Gustav Gustavovic");

        em.persist(person1);
        em.persist(person2);
        em.persist(person3);
        em.persist(person4);

        EmployerBidirect employer1 = new EmployerBidirect("Alpha company");
        EmployerBidirect employer2 = new EmployerBidirect("Beta company");

        employer1.addPerson(person1);
        employer1.addPerson(person2);

        employer2.addPerson(person2);
        employer2.addPerson(person3);

        em.persist(employer1);
        em.persist(employer2);


        //pridanie na non owning site => OK
        person1.addEmployer(employer2);
        em.persist(person1);


        //remove on not owning site (mapped by) PERSON (no references in JOINING table)
        List<PersonBidirect> persons = em.createQuery("SELECT p FROM PersonBidirect p",
                PersonBidirect.class).getResultList();
        Assert.assertEquals("persons (not owning) before delete", 4, persons.size());
        em.remove(person4);
        persons = em.createQuery("SELECT p FROM PersonBidirect p",
                PersonBidirect.class).getResultList();
        Assert.assertEquals("persons (not owning) after delete", 3, persons.size());

        //remove on not owning site (mapped by) PERSON (references in JOINING table)
        em.remove(person3);
        persons = em.createQuery("SELECT p FROM PersonBidirect p",
                PersonBidirect.class).getResultList();
        Assert.assertEquals("persons (not owning) after delete", 3, persons.size());

        //remove on not owning site (mapped by) PERSON (references in JOINING table)
        //with reference delete
        person3.removeAllEmployers();
        em.remove(person3);
        persons = em.createQuery("SELECT p FROM PersonBidirect p",
                PersonBidirect.class).getResultList();
        Assert.assertEquals("persons (not owning) after delete", 2, persons.size());

        //zaznamy vo vazobnej tabulke
        List resultList = em.createNativeQuery("SELECT * FROM EMPLOYEER_BIDIRECT_PERSON_BIDIRECT;").getResultList();
        Assert.assertEquals(5, resultList.size());


        //remove on owning site EMPLOYER => OK
        List<EmployerBidirect> employer = em.createQuery("SELECT e FROM EmployerBidirect e",
                EmployerBidirect.class).getResultList();
        Assert.assertEquals("employer before delete", 2, employer.size());
        em.remove(employer2);
        employer = em.createQuery("SELECT e FROM EmployerBidirect e", EmployerBidirect.class).getResultList();
        Assert.assertEquals("employer  after delete", 1, employer.size());


        //vazobnej tabulke, po predchadzajucom delete (employer2) uz len 2
        resultList = em.createNativeQuery("SELECT * FROM EMPLOYEER_BIDIRECT_PERSON_BIDIRECT;").getResultList();
        Assert.assertEquals(2, resultList.size());
        em.getTransaction().commit();

        em.getTransaction().begin();
        employer1.removePerson(person2);

        resultList = em.createNativeQuery("SELECT * FROM EMPLOYEER_BIDIRECT_PERSON_BIDIRECT;").getResultList();
        Assert.assertEquals(1, resultList.size());

        //odstranenie na non owing site => ok
        person1.removeEmployer(employer1);

        resultList = em.createNativeQuery("SELECT * FROM EMPLOYEER_BIDIRECT_PERSON_BIDIRECT;").getResultList();
        Assert.assertEquals(0, resultList.size());

        em.getTransaction().commit();
        em.close();

    }

    /**
     * Owning site is JOINING table - EmployerPersonBidirectJoin entity
     */
    @Test
    public void bidirectManyToManyWithJoinTableTest() {
        EntityManagerFactory emFactory
                = Persistence.createEntityManagerFactory("employment_bidirectional_join");
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();

        //employers
        EmployerBidirectJoin emp1 = new EmployerBidirectJoin("ZP Informatika, a. s.");
        EmployerBidirectJoin emp2 = new EmployerBidirectJoin("SOFTIP, a. s.");
        EmployerBidirectJoin emp3 = new EmployerBidirectJoin("Pantheon.tech, s. r. o.");
        EmployerBidirectJoin emp4 = new EmployerBidirectJoin("Accenture Technologies Solution, s. r. o.");
        EmployerBidirectJoin emp5 = new EmployerBidirectJoin("Gamo, s. r. o");

        em.persist(emp1);
        em.persist(emp2);
        em.persist(emp3);
        em.persist(emp4);
        em.persist(emp5);

        List<EmployerBidirectJoin> employers = em.createQuery("SELECT e FROM " +
                "EmployerBidirectJoin e", EmployerBidirectJoin.class).getResultList();
        Assert.assertEquals(5, employers.size());


        //persons
        PersonBidirectJoin per1 = new PersonBidirectJoin("Adam");
        PersonBidirectJoin per2 = new PersonBidirectJoin("Barbora");
        PersonBidirectJoin per3 = new PersonBidirectJoin("Cyril");
        PersonBidirectJoin per4 = new PersonBidirectJoin("Dominika");
        em.persist(per1);
        em.persist(per2);
        em.persist(per3);
        em.persist(per4);

        List<PersonBidirectJoin> persons = em.createQuery("SELECT p FROM " +
                "PersonBidirectJoin p", PersonBidirectJoin.class).getResultList();
        Assert.assertEquals(4, persons.size());

        checkNumberInJoinTable(em, 0);
        //vlozenie vazby cez pridanie do person
        per1.addEmployeer(emp1);
        checkNumberInJoinTable(em, 1);

        //vlozenie vazby cez pridanie do employer
        emp1.addPerson(per2);
        checkNumberInJoinTable(em, 2);

        //vlozenie vazby
        emp2.addPerson(per1);
        checkNumberInJoinTable(em, 3);

        //pokus o vymazanie na non owning site => porusenie referencnej integrity
        checkNumberInEmployer(em, 5);
        Exception exp = null;
        try {
            em.remove(emp1);
            em.flush();
        } catch (Exception e) {
            exp = e;
            em.clear();
        }
        Assert.assertNotNull(exp);
        checkNumberInEmployer(em, 5);


        //join table:
        //per1, emp1
        //per2, emp1
        //per1, emp2
        //odstranenie vsetkych vazieb s emp1 a nasledne samotneho emp1
        emp1.removePersons();
        em.remove(em.contains(emp1) ? emp1 : em.merge(emp1));
        checkNumberInJoinTable(em, 1);
        checkNumberInEmployer(em, 4);

        //join table:
        //per1, emp2
        //odstranenie vsetkych vazieb s per1 a nasledne samotneho per1
        checkNumberInJoinTable(em, 1);
        checkNumberInPerson(em, 4);

        PersonBidirectJoin per1Reload = em.find(PersonBidirectJoin.class, per1.getId());
        per1Reload.removeEmployers();
        em.remove(per1Reload);

        checkNumberInJoinTable(em, 0);
        checkNumberInPerson(em, 3);
        em.getTransaction().commit();
        em.close();
    }

    private void checkNumberInJoinTable(EntityManager em, int awaitedNumber) {
        List<EmployerPersonBidirectJoin> joinEntity = em.createQuery("SELECT j FROM " +
                        "EmployerPersonBidirectJoin j", EmployerPersonBidirectJoin.class)
                .getResultList();
        Assert.assertEquals(awaitedNumber, joinEntity.size());
    }

    private void checkNumberInEmployer(EntityManager em, int awaitedNumber) {
        List<EmployerBidirectJoin> lst = em.createQuery("SELECT e FROM " +
                        "EmployerBidirectJoin e", EmployerBidirectJoin.class)
                .getResultList();
        Assert.assertEquals(awaitedNumber, lst.size());
    }

    private void checkNumberInPerson(EntityManager em, int awaitedNumber) {
        List<PersonBidirectJoin> lst = em.createQuery("SELECT p FROM " +
                        "PersonBidirectJoin p", PersonBidirectJoin.class)
                .getResultList();
        Assert.assertEquals(awaitedNumber, lst.size());
    }


}
