package gloncak.jozef.springframework.pure.bean.template;

import gloncak.jozef.springframework.pure.bean.template.bean.Person;
import gloncak.jozef.springframework.pure.bean.template.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main( String[] args ) {
        LOG.info("Annotation configuration");
        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
        writePersonDetails((Person)appContext.getBean("personaDefault"));
        writePersonDetails((Person)appContext.getBean("personaCustom"));

        LOG.info("=========================");
        LOG.info("XML configuration");
        ApplicationContext appContextXml = new ClassPathXmlApplicationContext("Beans.xml");
        writePersonDetails((Person) appContextXml.getBean("personDefault"));
        writePersonDetails((Person)appContextXml.getBean("personCustom"));
    }

    private static void writePersonDetails(Person person) {
        LOG.info("Person");
        LOG.info(" {}", person.getFirstName());
        LOG.info(" {}", person.getMiddleName());
        LOG.info(" {}", person.getSurname());
    }
}
