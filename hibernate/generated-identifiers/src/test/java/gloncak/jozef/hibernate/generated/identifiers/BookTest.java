package gloncak.jozef.hibernate.generated.identifiers;


import gloncak.jozef.hibernate.generated.identifiers.entity.BookSequence;
import gloncak.jozef.hibernate.generated.identifiers.entity.BookUUID;
import gloncak.jozef.hibernate.generated.identifiers.entity.BookWithIdentity;
import gloncak.jozef.hibernate.generated.identifiers.entity.BookWithTable;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BookTest {

    /**
     * Tryies to demonstrate that sequence as specified in {@link BookSequence} is called. You can find out that initial
     * value of this sequence is set to 50. In this test it is tested that ID generated for books are in order 50, 51
     */
    @Test
    public void isSequenceUsedToGenerate() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("book-test");
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        BookSequence book1 = new BookSequence("Awaited");
        em.persist(book1);

        BookSequence book2 = new BookSequence("Awaited II");
        em.persist(book2);

        em.getTransaction().commit();
        Assert.assertEquals(50, book1.getId());
        Assert.assertEquals(51, book2.getId());
        em.close();
    }

    /**
     * Tests whether IDENTITY strategy is used to generate values of ID
     */
    @Test
    public void isIdentityUsedToGenerate() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("book-test");
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        BookWithIdentity book1 = new BookWithIdentity("Awaited");
        em.persist(book1);

        BookWithIdentity book2 = new BookWithIdentity("Awaited II");
        em.persist(book2);

        em.getTransaction().commit();
        Assert.assertEquals(1, book1.getId());
        Assert.assertEquals(2, book2.getId());
        em.close();
    }

    /**
     * Tests whether TABLE strategy is used to generate values of ID
     */
    @Test
    public void isTableGeneratorUsedToGenerate() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("book-test");
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();

        BookWithTable bookWithTableID = new BookWithTable("Anna Kareninova");
        em.persist(bookWithTableID);

        BookWithTable bookWithTableID2 = new BookWithTable("StarGate");
        em.persist(bookWithTableID2);

        em.getTransaction().commit();
        em.close();
    }

    /**
     * Tests that when ID is of type UUID and is used annotation @GeneratedValue that generated ID is of type UUID.
     */
    @Test
    public void isUUIDGeneratorUsetToGenerate() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("book-test");
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();

        BookUUID bookUUID1 = new BookUUID("Harry Potter");
        em.persist(bookUUID1);

        em.getTransaction().commit();
        Assert.assertTrue("Generated identifier doesn't conform with UUID format.",
                bookUUID1.getId().toString().matches("[a-z0-9]{8}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{4}-[a-z0-9]{12}"));

        em.close();
    }

}