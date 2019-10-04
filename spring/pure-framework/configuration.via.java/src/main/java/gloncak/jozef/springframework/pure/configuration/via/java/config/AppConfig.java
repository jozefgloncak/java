package gloncak.jozef.springframework.pure.configuration.via.java.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ServiceAConfig.class, ServiceBConfig.class})
public class AppConfig {

}
