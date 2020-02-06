package gloncak.jozef.jsp.integratespringjsp2.model;

import org.springframework.stereotype.Component;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
@SessionScoped
public class PersonData implements Serializable {
    private static final long serialVersionUID = 1L;
    private String firstName;
    private Integer age;
    private Integer height;
    private LocalDate jobStartDate;

    private static final List<Person> ORIGINAL_PERSONS =
            new ArrayList<>(Arrays.asList(
                    new Person("Adam", 21, 173, LocalDate.of(1985, 4, 21))
                    , new Person("Barbor", 32, 165, LocalDate.of(1990, 12, 1))
                    , new Person("Cyril", 29, 185, LocalDate.of(1983, 6, 30))
                    , new Person("Denisa", 23, 163, LocalDate.of(1989, 3, 15))
                    , new Person("Emil", 35, 181, LocalDate.of(1987, 8, 17))
                    , new Person("Filip", 27, 173, LocalDate.of(1980, 4, 15))
                    , new Person("Gustav", 34, 168, LocalDate.of(1980, 1, 24))
                    , new Person("Gustav", 35, 173, LocalDate.of(1990, 9, 23))
                    , new Person("Gustav", 29, 189, LocalDate.of(1984, 3, 14))
                    , new Person("Hana", 16, 159, LocalDate.of(1987, 11, 1))
                    , new Person("Ivan", 23, 177, LocalDate.of(1983, 5, 5))
            ));

    private List<Person> persons = new ArrayList<>(ORIGINAL_PERSONS);

    public PersonData() {
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void restoreData() {
        this.persons = new ArrayList<>(ORIGINAL_PERSONS);
    }

    public String addPerson() {
        Person person = new Person(firstName, age, height, jobStartDate);
        persons.add(person);
        return null;
    }

    public String deletePerson(Person person) {
        persons.remove(person);
        return null;
    }

    public String editPerson(Person person) {
        person.setCanEdit(true);
        return null;
    }

    public String savePerson() {

        //set "canEdit" of all persons to false

        for (Person person : persons) {
            person.setCanEdit(false);
        }
        return null;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public LocalDate getJobStartDate() {
        return jobStartDate;
    }

    public void setJobStartDate(LocalDate jobStartDate) {
        this.jobStartDate = jobStartDate;
    }


}