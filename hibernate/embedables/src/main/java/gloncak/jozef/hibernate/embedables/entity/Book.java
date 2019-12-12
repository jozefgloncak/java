package gloncak.jozef.hibernate.embedables.entity;

import gloncak.jozef.hibernate.embedables.embedable.Author;

import javax.persistence.*;

@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ISBN")
    private String isbn;

    @Embedded
    private Author author;

    public Book() {
    }

    public Book(String name, String isbn, Author author) {
        this.name = name;
        this.isbn = isbn;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
