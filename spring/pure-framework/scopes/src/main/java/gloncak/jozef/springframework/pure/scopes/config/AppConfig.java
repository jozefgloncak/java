package gloncak.jozef.springframework.pure.scopes.config;

import gloncak.jozef.springframework.pure.scopes.bean.Envelope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {
    @Bean
    @Scope(value="singleton")
    public Envelope envelopeSingleton() {
        return new Envelope();
    }

    @Bean
    @Scope(value="prototype")
    public Envelope envelopePrototype() {
        return new Envelope();
    }
}
