package gloncak.jozef.jsp.integratespringjsp2.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

public class Person implements Serializable, Cloneable {

    private static final Logger LOG = LoggerFactory.getLogger(Person.class);

    private static Integer idCounter = 0;

    private boolean canEdit;

    private Integer id;

    private String firstName;

    //NotNull annotation currently doesn't work because value which is sent from frontend isn't correctly configured
    @NotNull(message = "Annotation validation - surname can't be null.")
    private String surname = null;

    @Size(min = 2, max = 15, message = "Annotation validation - middle name length >2 & <15")
    private String middleName;

    @Digits(integer = 2, fraction = 0, message = "Annotation validation - age has to be number with integer part max "
            + "2 and fraction part 0")
    @DecimalMin(value = "18", inclusive = false, message = "Annotation validation - Number has to be  >"
            + " 18")
    private Integer age;

    @Min(value = 30, message = "Annotation validation - Height > 30")
    @Max(value = 210, message = "Annotation validation - Height < 210")
    private Integer height; //rounhded height in cm

    @Pattern(regexp = "^[a-zA-Z0-9]{1,}@[a-zA-Z0-9]{1,}.[a-zA-Z]{2}$", message = "Annotation validation - has to "
            + "comply with specified regex ^[a-zA-Z0-9]{1,}@[a-zA-Z0-9]{1,}.[a-zA-Z]{2}$")
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
    private boolean genderFrontend;
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

    private Boolean healthy;

    public Person() {
    }

    public Person(String firstName, Integer age, Integer height, LocalDate jobStartDate) {
        this.id = idCounter++;
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

    public String getGenderValue() {
        return genderValue;
    }

    public void setGenderValue(String genderValue) {
        this.genderValue = genderValue;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isGenderFrontend() {
        return genderFrontend;
    }

    public void setGenderFrontend(boolean genderFrontend) {
        this.genderFrontend = genderFrontend;
    }

    public Boolean getHealthy() {
        return healthy;
    }

    public void setHealthy(Boolean healthy) {
        this.healthy = healthy;
    }

    @Override
    public Person clone() throws CloneNotSupportedException {
        Person newClone =(Person) super.clone();
        newClone.setJobStartDate(LocalDate.of(this.jobStartDate.getYear(), this.jobStartDate.getMonth(), this.jobStartDate.getDayOfMonth()));
        //@TODO: NOT all field are copied, it isn't complete deep copy. Just for demonstration purposes.
        return newClone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.firstName);
        hash = 47 * hash + Objects.hashCode(this.age);
        hash = 47 * hash + Objects.hashCode(this.height);
        hash = 47 * hash + Objects.hashCode(this.jobStartDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.age, other.age)) {
            return false;
        }
        if (!Objects.equals(this.height, other.height)) {
            return false;
        }
        if (!Objects.equals(this.jobStartDate, other.jobStartDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Person{" + "firstName='" + firstName + '\'' + ", age=" + age + ", height=" + height + ", jobStartDate" +
                "=" + jobStartDate + '}';
    }
}
