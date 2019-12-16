package gloncak.jozef.hibernate.many.to.many.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "PERSON_UNIDIRECT")
public class PersonUnidirect {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    public PersonUnidirect() {
    }

    public PersonUnidirect(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
