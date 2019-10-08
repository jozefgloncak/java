package gloncak.jozef.springframework.pure.bean.template.config;

import gloncak.jozef.springframework.pure.bean.template.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean(name = "personaDefault")
    @Scope(scopeName = "prototype")
    public Person person() {
         Person person = new Person();
         defaultInitOfPerson(person);
         return person;
    }

    @Bean(name = "personaCustom")
    @Scope(scopeName = "prototype")
    public Person personCustom() {
        Person person = person();
        person.setFirstName("Michal");
        return person;
    }

    private void defaultInitOfPerson(Person person) {
        person.setFirstName("John");
        person.setMiddleName("Michael");
        person.setSurname("Doe");
    }


}
