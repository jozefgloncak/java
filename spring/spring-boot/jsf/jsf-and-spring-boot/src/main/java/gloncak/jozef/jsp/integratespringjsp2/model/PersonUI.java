package gloncak.jozef.jsp.integratespringjsp2.model;

import gloncak.jozef.jsp.integratespringjsp2.menu.Country;
import gloncak.jozef.jsp.integratespringjsp2.menu.Gender;
import gloncak.jozef.jsp.integratespringjsp2.menu.Town;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
@SessionScope
public class PersonUI {

    private Person person;


    public PersonUI() {
        this.person = new Person();
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

    //selection one element - data
    private List<Country> countries = Arrays.asList(
            new Country("Slovensko", "SK")
            , new Country("Cesko", "CR")
            , new Country("Madarsko", "HU")
            , new Country("Polsko", "PL"));

    private List<String> countryValues = Arrays.asList("Slovensko", "Cesko", "Madarsko", "Polsko");
    //:selection one element

    //selection one list - data
    private List<Town> towns = Arrays.asList(new Town("Martin", "MT"), new Town("Bratislava", "BL"));
    private List<String> townValues = Arrays.asList("Svaty Jur", "Pezinok");
    //:selection one list - data

    //selection one radio - data
    private List<Gender> genders = Arrays.asList(new Gender("male", "1"), new Gender("female", "0"), new Gender(
            "unknown", "-1"));
    private List<String> genderValues = Arrays.asList("man", "woman");
    //:selection one radio - data


    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public List<String> getCountryValues() {
        return countryValues;
    }

    public void setCountryValues(List<String> countryValues) {
        this.countryValues = countryValues;
    }

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
