package gloncak.jozef.jsp.integratespringjsp2.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

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

    private boolean male;
    private String gender;


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

    public boolean getMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
        if (male) {
            gender = "male";
        } else {
            gender = "female";
        }
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
}
