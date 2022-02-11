package gloncak.jozef.hibernate.many.to.many.entities.second;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYER_UNIDIRECT_B")
public class EmployerUnidirectB {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    public EmployerUnidirectB() {
    }

    public EmployerUnidirectB(String name) {
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

}
