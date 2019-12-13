package gloncak.jozef.hibernate.generated.identifiers.entity;

import javax.persistence.*;

@Entity
@Table(name = "BOOK")
public class BookSequence {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence-generator"
    )
    @SequenceGenerator(
            name = "sequence-generator",
            sequenceName = "book_seq"
            ,initialValue = 50

    )
    private int id;

    @Column(name = "NAME")
    private String name;

    public BookSequence() {
    }

    public BookSequence(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
