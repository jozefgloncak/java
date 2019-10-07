package gloncak.jozef.springframework.pure.bean.lifecycle.config;

import gloncak.jozef.springframework.pure.bean.lifecycle.beans.Person;
import gloncak.jozef.springframework.pure.bean.lifecycle.beans.Subject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(initMethod = "initialize", destroyMethod = "tidyUp")
    public Subject subject() {
        return new Subject();
    }

}
