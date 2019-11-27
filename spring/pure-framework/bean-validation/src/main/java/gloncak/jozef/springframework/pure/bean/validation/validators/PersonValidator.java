package gloncak.jozef.springframework.pure.bean.validation.validators;

import gloncak.jozef.springframework.pure.bean.validation.beans.Person;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors e) {
        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
        ValidationUtils.rejectIfEmpty(e, "surname", "surname.empty");
        Person person = (Person) o;
        if (person.getAge() < 0) {
            e.rejectValue("age", "negativeValue");
        } else if (person.getAge() > 120) e.rejectValue("age", "unreallisticValue");
    }
}
