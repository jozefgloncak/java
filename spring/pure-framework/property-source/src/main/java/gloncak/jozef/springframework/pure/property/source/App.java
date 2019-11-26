package gloncak.jozef.springframework.pure.property.source;

import gloncak.jozef.springframework.pure.property.source.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        AbstractApplicationContext appCtx =
                new AnnotationConfigApplicationContext(AppConfig.class);
        ConfigurableEnvironment env = appCtx.getEnvironment();
        LOG.info("Property prop1 value: {}", env.getProperty("prop1"));
    }
}
