package gloncak.jozef.hibernate.many.to.many.entities;

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
    private Set<EmployeerBidirect> employees = new HashSet<>();

    public PersonBidirect() {
    }

    public PersonBidirect(String name) {
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

    public Set<EmployeerBidirect> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeerBidirect> employees) {
        this.employees = employees;
    }
}
