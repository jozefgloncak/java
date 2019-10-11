package gloncak.jozef.springframework.pure.di.config;

import gloncak.jozef.springframework.pure.di.beans.Address;
import gloncak.jozef.springframework.pure.di.beans.Name;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("gloncak.jozef.springframework.pure.di.beans")
public class AppConfig {

    @Bean("address1")
    public Address addressA() {
        return new Address("Morovska", "Kuty", "93343");
    }

    @Bean("address2")
    public Address addressB() {
        return new Address("Polska", "Kezmarok", "00001");
    }

    @Bean("address3")
    public Address addressC() {
        return new Address("Ukrajinska", "Vysny Komarnik", "00002");
    }

    @Bean("name1")
    public Name nameA() {
        return new Name("Angela", "Aneta", "Abelovicova");
    }

    @Bean("name2")
    public Name nameB() {
        return new Name("Barbora", "Blanka", "Babelovicova");
    }

    @Bean("name3")
    public Name nameC() {
        return new Name("Cecilia", "Catherine", "Cibulkova");
    }

}
