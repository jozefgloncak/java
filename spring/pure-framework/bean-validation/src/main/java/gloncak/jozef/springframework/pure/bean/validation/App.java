package gloncak.jozef.springframework.pure.bean.validation;

import gloncak.jozef.springframework.pure.bean.validation.beans.Person;
import gloncak.jozef.springframework.pure.bean.validation.validators.PersonValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;

public class App {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("Beans.xml");
        PersonValidator personValidator = appCtx.getBean("personValidator", PersonValidator.class);

        Person person1 = appCtx.getBean("person1", Person.class);
        BeanPropertyBindingResult person1Errors = new BeanPropertyBindingResult(person1, "person1");
        personValidator.validate(person1, person1Errors);
        LOG.info("errors for person1:");
        person1Errors.getAllErrors().forEach(error -> LOG.info("- {}", error));

        Person person2 = appCtx.getBean("person2", Person.class);
        BeanPropertyBindingResult person2Errors = new BeanPropertyBindingResult(person2, "person2");
        personValidator.validate(person2, person2Errors);
        LOG.info("errors for person2:");
        person2Errors.getAllErrors().forEach(error -> LOG.info("- {}", error));

    }
}
