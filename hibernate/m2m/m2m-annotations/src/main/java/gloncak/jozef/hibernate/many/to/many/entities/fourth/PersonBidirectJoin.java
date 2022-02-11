package gloncak.jozef.hibernate.many.to.many.entities.fourth;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PERSON_BIDIRECT")
public class PersonBidirectJoin implements Serializable {

    @Id
    @GeneratedValue(generator = "gen2")
    private Integer id;

    private String name;

    @OneToMany(
            mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<EmployerPersonBidirectJoin> employers = new HashSet<>();

    public PersonBidirectJoin() {
    }

    public void addEmployeer(EmployerBidirectJoin employer) {
        EmployerPersonBidirectJoin employerPersonBidirect = new EmployerPersonBidirectJoin(employer, this);
        this.employers.add(employerPersonBidirect);
        employer.getPersons().add(employerPersonBidirect);
    }

    public void removeEmployer(EmployerBidirectJoin employer) {
        EmployerPersonBidirectJoin employerPersonBidirect = new EmployerPersonBidirectJoin(employer, this);
        this.employers.remove(employerPersonBidirect);
        employer.getPersons().remove(employerPersonBidirect);
        employerPersonBidirect.setEmployer(null);
        employerPersonBidirect.setPerson(null);
    }


    public void removeEmployers() {
        Set<EmployerPersonBidirectJoin> employers = new HashSet<>(this.employers);
        for (EmployerPersonBidirectJoin employer: employers) {
            removeEmployer(employer.getEmployer());
        }
    }

    public PersonBidirectJoin(String name) {
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

    public Set<EmployerPersonBidirectJoin> getEmployers() {
        return employers;
    }

    public void setEmployers(
            Set<EmployerPersonBidirectJoin> employers) {
        this.employers = employers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonBidirectJoin that = (PersonBidirectJoin) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
