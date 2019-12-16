package gloncak.jozef.hibernate.many.to.many.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEER_UNIDIRECT")
public class EmployeerUnidirect {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<PersonUnidirect> persons;

    public EmployeerUnidirect() {
    }

    public EmployeerUnidirect(String name) {
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

    public Set<PersonUnidirect> getPersons() {
        return persons;
    }

    public void setPersons(Set<PersonUnidirect> persons) {
        this.persons = persons;
    }
}
