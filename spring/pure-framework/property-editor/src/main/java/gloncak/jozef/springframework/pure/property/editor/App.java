package gloncak.jozef.springframework.pure.property.editor;

import gloncak.jozef.springframework.pure.property.editor.beans.Person;
import gloncak.jozef.springframework.pure.property.editor.config.AppConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext appCtx = new AnnotationConfigApplicationContext(AppConfiguration.class);
        Person person = appCtx.getBean("person", Person.class);
        LOG.info("Address in person is correctly instantiated despite it was specified in Person class as  @Value" +
                "(\"Moskovska|644|97335\")");
        LOG.info("person: {}", person);
    }
}
