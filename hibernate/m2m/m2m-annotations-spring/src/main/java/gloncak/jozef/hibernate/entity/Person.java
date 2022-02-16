package gloncak.jozef.hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PERSON_BIDIRECT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    @ManyToMany(mappedBy = "persons")
    private Set<Employer> employers = new HashSet<>();

    public Person(String name) {
        this.name = name;
    }

    public void addEmployer(Employer employee) {
        this.employers.add(employee);
        employee.getPersons().add(this);
    }

    public void removeEmployer(Employer employee) {
        this.employers.remove(employee);
        employee.getPersons().remove(this);
    }

    public void removeAllEmployers() {
        Set<Employer> employers = new HashSet<>(this.employers);
        for (Employer employer: employers) {
            removeEmployer(employer);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
