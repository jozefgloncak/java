package gloncak.jozef.hibernate.many.to.many.entities.third;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEER_BIDIRECT")
public class EmployerBidirect {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<PersonBidirect> persons = new HashSet<>();

    public EmployerBidirect() {
    }

    public EmployerBidirect(String name) {
        this.name = name;
    }

    public void addPerson(PersonBidirect person) {
        this.persons.add(person);
        person.getEmployers().add(this);
    }

    public void removePerson(PersonBidirect person) {
        this.persons.remove(person);
        person.getEmployers().remove(this);
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

    public Set<PersonBidirect> getPersons() {
        return persons;
    }

    public void setPersons(Set<PersonBidirect> persons) {
        this.persons = persons;
    }
}
