package gloncak.jozef.hibernate.many.to.many.entities.fourth;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEER_BIDIRECT")
public class EmployerBidirectJoin implements Serializable {
    @Id
    @GeneratedValue(generator="gen1")
    private Integer id;

    private String name;

    @OneToMany(
            mappedBy = "employer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<EmployerPersonBidirectJoin> persons = new HashSet<>();

    public EmployerBidirectJoin() {
    }

    public EmployerBidirectJoin(String name) {
        this.name = name;
    }

    public void addPerson(PersonBidirectJoin person) {
        EmployerPersonBidirectJoin employerPersonBidirect = new EmployerPersonBidirectJoin(this, person);
        this.persons.add(employerPersonBidirect);
        person.getEmployers().add(employerPersonBidirect);
    }

    public void removePerson(PersonBidirectJoin person) {
        EmployerPersonBidirectJoin employerPersonBidirect = new EmployerPersonBidirectJoin(this, person);
        this.persons.remove(employerPersonBidirect);
        person.getEmployers().remove(employerPersonBidirect);
        employerPersonBidirect.setEmployer(null);
        employerPersonBidirect.setPerson(null);
    }

    public void removePersons() {
        HashSet<EmployerPersonBidirectJoin> persons = new HashSet<>(this.persons);
        for (EmployerPersonBidirectJoin person : persons) {
            removePerson(person.getPerson());
        }
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

    public Set<EmployerPersonBidirectJoin> getPersons() {
        return persons;
    }

    public void setPersons(Set<EmployerPersonBidirectJoin> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployerBidirectJoin that = (EmployerBidirectJoin) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
