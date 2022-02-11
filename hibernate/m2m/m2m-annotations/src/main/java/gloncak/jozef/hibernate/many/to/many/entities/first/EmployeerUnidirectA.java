package gloncak.jozef.hibernate.many.to.many.entities.first;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEER_UNIDIRECT_A")
public class EmployeerUnidirectA {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<PersonUnidirectA> persons;

    public EmployeerUnidirectA() {
    }

    public EmployeerUnidirectA(String name) {
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

    public Set<PersonUnidirectA> getPersons() {
        return persons;
    }

    public void setPersons(Set<PersonUnidirectA> persons) {
        this.persons = persons;
    }
}
