package gloncak.jozef.springframework.pure.configuration.via.java.config;

import gloncak.jozef.springframework.pure.configuration.via.java.service.ServiceA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceAConfig {

    @Bean(name="srvA")
    public ServiceA getSrvA() {
        return new ServiceA();
    }
}
