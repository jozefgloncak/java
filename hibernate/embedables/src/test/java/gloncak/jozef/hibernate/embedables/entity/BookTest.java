package gloncak.jozef.hibernate.embedables.entity;


import gloncak.jozef.hibernate.embedables.embedable.Author;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BookTest {

    /**
     * Tests that when @Embeded annotation on property is used then this object (in this case Author) is populated by
     * instance of Author and values from the same tables (values for Author and for Book comes from the same DB
     * table AUTHOR
     */
    @Test
    public void isEmbededPropertyPopulated() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("book-test");
        EntityManager em = emFactory.createEntityManager();

        em.getTransaction().begin();

        Book book1 = new Book("Davinci code", "1234567890", new Author("Dan Brown", "USA"));
        Book book2 = new Book("Divergent", "0123456789", new Author("Veronica Roth", "USA"));
        em.persist(book1);
        em.persist(book2);
        em.getTransaction().commit();
        int idDavinciCode = book1.getId();
        em.close();

        em = emFactory.createEntityManager();
        em.getTransaction().begin();
        Book book1FromDB = em.find(Book.class, idDavinciCode);
        em.getTransaction().commit();
        Assert.assertNotSame(book1FromDB, book1);

        Assert.assertEquals("Dan Brown", book1FromDB.getAuthor().getName());
        Assert.assertEquals("USA", book1FromDB.getAuthor().getNationality());

        em.close();
    }

}