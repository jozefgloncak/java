package gloncak.jozef.jsp.integratespringjsp2.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Person implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(Person.class);

    private boolean canEdit;

    private String firstName;

    //NotNull annotation currently doesn't work because value which is sent from frontend isn't correctly configured
    @NotNull(message = "Annotation validation - surname can't be null.")
    private String surname = null;

    @Size(min = 2, max = 15, message = "Annotation validation - middle name length >2 & <15")
    private String middleName;

    @Digits(integer = 2, fraction = 0, message = "Annotation validation - age has to be number with integer part max " +
            "2 and fraction part 0")
    @DecimalMin(value = "18", inclusive = false, message = "Annotation validation - Number has to be  >" +
            " 18")
    private Integer age;

    @Min(value = 30, message = "Annotation validation - Height > 30")
    @Max(value = 130, message = "Annotation validation - Height < 130")
    private Integer height; //rounhded height in cm

    @Pattern(regexp = "^[a-zA-Z0-9]{1,}@[a-zA-Z0-9]{1,}.[a-zA-Z]{2}$", message = "Annotation validation - has to " +
            "comply with specified regex ^[a-zA-Z0-9]{1,}@[a-zA-Z0-9]{1,}.[a-zA-Z]{2}$")
    private String eMail;

    private String zip;

    //selection one menu
    private String country;
    private String countryValue;
    private String countryFrontend;
    //:selection one menu

    //selection one listbox
    private String town;
    private String townValue;
    private String townFrontend;
    //:selection one listbox

    //selection one radio
    private String gender;
    private String genderValue;
    private String genderFrontend;
    //:selection one radio

    //select many checkbox
    private List<String> programmingLanguages;
    //:select many checkbox

    //select many listbox
    private List<String> colours;
    //:select many listbox

    //select many menu
    private List<String> animals;
    //:select many menu

    public Person() {
    }

    public Person(String firstName, Integer age, Integer height, LocalDate jobStartDate) {
        this.firstName = firstName;
        this.age = age;
        this.height = height;
        this.jobStartDate = jobStartDate;
    }

    @Past
    private LocalDate jobStartDate;

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
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

    public String getGender() {
        return gender;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getJobStartDate() {
        return jobStartDate;
    }

    public void setJobStartDate(LocalDate jobStartDate) {
        this.jobStartDate = jobStartDate;
    }

    public void actionListener() {
        System.out.println("tu som");
    }

    public boolean isCanEdit() {
        return canEdit;
    }

    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;
    }

    public void clearData() {
        this.age = null;
        this.eMail = null;
        this.firstName = null;
        this.gender = null;
        this.height = null;
        this.jobStartDate = null;
        this.middleName = null;
        this.surname = null;
        this.zip = null;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryValue() {
        return countryValue;
    }

    public void setCountryValue(String countryValue) {
        this.countryValue = countryValue;
    }

    public String getCountryFrontend() {
        return countryFrontend;
    }

    public void setCountryFrontend(String countryFrontend) {
        this.countryFrontend = countryFrontend;
    }

    public String getTownFrontend() {
        return townFrontend;
    }

    public void setTownFrontend(String townFrontend) {
        this.townFrontend = townFrontend;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getTownValue() {
        return townValue;
    }

    public void setTownValue(String townValue) {
        this.townValue = townValue;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGenderValue() {
        return genderValue;
    }

    public void setGenderValue(String genderValue) {
        this.genderValue = genderValue;
    }

    public String getGenderFrontend() {
        return genderFrontend;
    }

    public void setGenderFrontend(String genderFrontend) {
        this.genderFrontend = genderFrontend;
    }

    public List<String> getProgrammingLanguages() {
        return programmingLanguages;
    }

    public void setProgrammingLanguages(List<String> programmingLanguages) {
        this.programmingLanguages = programmingLanguages;
    }

    public List<String> getColours() {
        return colours;
    }

    public void setColours(List<String> colours) {
        this.colours = colours;
    }

    public List<String> getAnimals() {
        return animals;
    }

    public void setAnimals(List<String> animals) {
        this.animals = animals;
    }
}
