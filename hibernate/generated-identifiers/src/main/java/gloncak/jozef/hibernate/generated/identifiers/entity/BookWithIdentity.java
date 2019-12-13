package gloncak.jozef.hibernate.generated.identifiers.entity;

import javax.persistence.*;

@Entity
@Table(name = "BOOK_WITH_IDENTITY")
public class BookWithIdentity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;

    @Column(name = "NAME")
    private String name;

    public BookWithIdentity() {
    }

    public BookWithIdentity(String name) {
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
