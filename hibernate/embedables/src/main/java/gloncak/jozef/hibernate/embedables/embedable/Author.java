package gloncak.jozef.hibernate.embedables.embedable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Author {
    @Column(name ="AUTHOR_NAME")
    private String name;

    @Column(name = "AUTHOR_NATIONALITY")
    private String nationality;

    public Author(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public Author() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
