package gloncak.jozef.springframework.pure.configuration.via.java.config;

import gloncak.jozef.springframework.pure.configuration.via.java.service.ServiceB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBConfig {

    @Bean(name="srvB")
    public ServiceB getSrvB() {
        return new ServiceB();
    }
}
