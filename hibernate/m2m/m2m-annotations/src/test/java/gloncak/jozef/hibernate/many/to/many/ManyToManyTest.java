package gloncak.jozef.hibernate.many.to.many;

import gloncak.jozef.hibernate.many.to.many.entities.EmployeerBidirect;
import gloncak.jozef.hibernate.many.to.many.entities.EmployeerUnidirect;
import gloncak.jozef.hibernate.many.to.many.entities.PersonBidirect;
import gloncak.jozef.hibernate.many.to.many.entities.PersonUnidirect;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ManyToManyTest {

    @Test
    public void unidirectManyToManyTest() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("employment");
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        PersonUnidirect person1 = new PersonUnidirect("Marian Komaromy");
        PersonUnidirect person2 = new PersonUnidirect("Adam Adamovic");
        PersonUnidirect person3 = new PersonUnidirect("Beata Burkovic");

        EmployeerUnidirect pantheon = new EmployeerUnidirect("Pantheon.tech, s. r. o.");
        EmployeerUnidirect accenture = new EmployeerUnidirect("Accenture technologies solution, s. r. o.");
        em.persist(person1);
        em.persist(person2);
        em.persist(person3);

        Set<PersonUnidirect> personsForPantheon = new HashSet<>();
        personsForPantheon.add(person1);
        personsForPantheon.add(person2);
        pantheon.setPersons(personsForPantheon);


        Set<PersonUnidirect> personsForAccenture = new HashSet<>();
        personsForAccenture.add(person2);
        personsForAccenture.add(person3);
        accenture.setPersons(personsForAccenture);

        em.persist(pantheon);
        em.persist(accenture);
        List resultList = em.createNativeQuery("SELECT * FROM EMPLOYEER_UNIDIRECT_PERSON_UNIDIRECT;").getResultList();
        Assert.assertEquals(4, resultList.size()); //in link table there are stored all 4 relations person - employer

        em.getTransaction().commit();
        em.getTransaction().begin();

        em.remove(accenture);
        resultList = em.createNativeQuery("SELECT * FROM EMPLOYEER_UNIDIRECT_PERSON_UNIDIRECT;").getResultList();
        Assert.assertEquals(2, resultList.size()); //in link table there are stored all 2 relations person - employer
        // (one employeer has been deleted)

        em.getTransaction().commit();

        em.close();
    }

    @Test
    public void bidirectManyToManyTest() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("employment_bidirectional");
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();

        PersonBidirect person1 = new PersonBidirect("Cyril Cyrilovic");
        PersonBidirect person2 = new PersonBidirect("David Davidovic");
        PersonBidirect person3 = new PersonBidirect("Emil Emilovic");

        em.persist(person1);
        em.persist(person2);
        em.persist(person3);

        EmployeerBidirect alpha = new EmployeerBidirect("Alpha company");
        EmployeerBidirect beta = new EmployeerBidirect("Beta company");

        alpha.addPerson(person1);
        alpha.addPerson(person2);

        beta.addPerson(person2);
        beta.addPerson(person3);

        em.persist(alpha);
        em.persist(beta);
        List resultList = em.createNativeQuery("SELECT * FROM EMPLOYEER_BIDIRECT_PERSON_BIDIRECT;").getResultList();
        Assert.assertEquals(4, resultList.size());
        em.getTransaction().commit();

        em.getTransaction().begin();
        alpha.removePerson(person2);
        alpha.removePerson(person1);

        resultList = em.createNativeQuery("SELECT * FROM EMPLOYEER_BIDIRECT_PERSON_BIDIRECT;").getResultList();
        Assert.assertEquals(2, resultList.size());

        em.getTransaction().commit();
        em.close();

    }


}
