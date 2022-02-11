package gloncak.jozef.hibernate.many.to.many.entities.fourth;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "EMPLOYER_PERSON_BIDIRECT")
public class EmployerPersonBidirectJoin implements Serializable {

    @Id
    @ManyToOne
    private EmployerBidirectJoin employer;

    @Id
    @ManyToOne
    private PersonBidirectJoin person;

    public EmployerPersonBidirectJoin() {
    }

    public EmployerPersonBidirectJoin(EmployerBidirectJoin employer,
            PersonBidirectJoin person) {
        this.employer = employer;
        this.person = person;
    }

    public EmployerBidirectJoin getEmployer() {
        return employer;
    }

    public void setEmployer(EmployerBidirectJoin employer) {
        this.employer = employer;
    }

    public PersonBidirectJoin getPerson() {
        return person;
    }

    public void setPerson(PersonBidirectJoin person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployerPersonBidirectJoin that = (EmployerPersonBidirectJoin) o;
        return Objects.equals(employer, that.employer) && Objects.equals(person, that.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employer, person);
    }
}
