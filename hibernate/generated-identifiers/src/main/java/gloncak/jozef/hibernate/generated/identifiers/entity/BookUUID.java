package gloncak.jozef.hibernate.generated.identifiers.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "BOOK_UUID")
public class BookUUID {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "NAME")
    private String name;

    public BookUUID() {
    }

    public BookUUID(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
