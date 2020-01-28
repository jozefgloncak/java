package gloncak.jozef.jsp.integratespringjsp2.model;

import gloncak.jozef.jsp.integratespringjsp2.menu.Country;
import gloncak.jozef.jsp.integratespringjsp2.menu.Gender;
import gloncak.jozef.jsp.integratespringjsp2.menu.Town;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@SessionScope
public class PersonUI {

    private Person person;

    private static final Logger LOG = LoggerFactory.getLogger(PersonUI.class);

    public PersonUI() {
        this.person = new Person();
        LOG.debug("Instantiation of classs {}", this.getClass().getCanonicalName());
    }

    public PersonUI(String firstName, Integer age, Integer height, LocalDate jobStartDate) {
        this.person = new Person(firstName, age, height, jobStartDate);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    //selection one list - data
    private List<Town> towns = Arrays.asList(new Town("Martin", "MT"), new Town("Bratislava", "BL"));
    private List<String> townValues = Arrays.asList("Svaty Jur", "Pezinok");
    //:selection one list - data

    //selection one radio - data
    private List<Gender> genders = Arrays.asList(new Gender("male", "1"), new Gender("female", "0"), new Gender(
            "unknown", "-1"));
    private List<String> genderValues = Arrays.asList("man", "woman");
    //:selection one radio - data

    public List<Town> getTowns() {
        return towns;
    }

    public void setTowns(List<Town> towns) {
        this.towns = towns;
    }

    public List<String> getTownValues() {
        return townValues;
    }

    public void setTownValues(List<String> townValues) {
        this.townValues = townValues;
    }

    public List<Gender> getGenders() {
        return genders;
    }

    public void setGenders(List<Gender> genders) {
        this.genders = genders;
    }

    public List<String> getGenderValues() {
        return genderValues;
    }

    public void setGenderValues(List<String> genderValues) {
        this.genderValues = genderValues;
    }
}
