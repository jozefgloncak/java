package gloncak.jozef.hibernate.many.to.many.entities.third;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PERSON_BIDIRECT")
public class PersonBidirect {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "persons")
    private Set<EmployerBidirect> employers = new HashSet<>();

    public PersonBidirect() {
    }

    public PersonBidirect(String name) {
        this.name = name;
    }

    public void addEmployer(EmployerBidirect employee) {
        this.employers.add(employee);
        employee.getPersons().add(this);
    }

    public void removeEmployer(EmployerBidirect employee) {
        this.employers.remove(employee);
        employee.getPersons().remove(this);
    }

    public void removeAllEmployers() {
        Set<EmployerBidirect> employers = new HashSet<>(this.employers);
        for (EmployerBidirect employer: employers) {
            removeEmployer(employer);
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

    public Set<EmployerBidirect> getEmployers() {
        return employers;
    }

    public void setEmployers(Set<EmployerBidirect> employers) {
        this.employers = employers;
    }
}
