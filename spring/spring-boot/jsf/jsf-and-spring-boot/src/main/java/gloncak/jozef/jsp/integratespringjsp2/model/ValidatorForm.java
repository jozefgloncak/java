package gloncak.jozef.jsp.integratespringjsp2.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Component
@RequestScope
public class ValidatorForm {
    private String name;
    private Integer age;
    private Double rating;

    @Min(20)
    @Max(150)
    @NotNull
    private Integer weight;

    private Long birthNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Long getBirthNumber() {
        return birthNumber;
    }

    public void setBirthNumber(Long birthNumber) {
        this.birthNumber = birthNumber;
    }
}
