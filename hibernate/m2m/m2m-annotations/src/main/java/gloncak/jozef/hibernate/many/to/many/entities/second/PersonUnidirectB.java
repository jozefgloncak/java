package gloncak.jozef.hibernate.many.to.many.entities.second;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PERSON_UNIDIRECT_B")
public class PersonUnidirectB {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    private Set<EmployerUnidirectB> employers;

    public PersonUnidirectB() {
    }

    public PersonUnidirectB(String name) {
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

    public Set<EmployerUnidirectB> getEmployers() {
        return employers;
    }

    public void setEmployers(Set<EmployerUnidirectB> employees) {
        this.employers = employees;
    }
}
