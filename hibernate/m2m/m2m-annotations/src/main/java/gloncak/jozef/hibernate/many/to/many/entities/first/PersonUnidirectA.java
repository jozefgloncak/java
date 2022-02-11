package gloncak.jozef.hibernate.many.to.many.entities.first;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON_UNIDIRECT_A")
public class PersonUnidirectA {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    public PersonUnidirectA() {
    }

    public PersonUnidirectA(String name) {
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
