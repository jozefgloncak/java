package gloncak.jozef.springframework.pure.di;

import gloncak.jozef.springframework.pure.di.beans.Person;
import gloncak.jozef.springframework.pure.di.beans.PersonAutowiredOnConstructor;
import gloncak.jozef.springframework.pure.di.beans.PersonAutowiredOnProperties;
import gloncak.jozef.springframework.pure.di.beans.PersonAutowiredOnSetter;
import gloncak.jozef.springframework.pure.di.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("Beans.xml");
        Person person1 = appContext.getBean("person1", Person.class);
        Person person2 = appContext.getBean("person2", Person.class);
        Person person3 = appContext.getBean("person3", Person.class);

        LOG.info("\n======== XML configuration =========");
        LOG.info("\n person1 {}", person1);
        LOG.info("\n person2 {}", person2);
        LOG.info("\n person3 {}", person3);

        ApplicationContext appContextAnnotations = new AnnotationConfigApplicationContext(AppConfig.class);

        PersonAutowiredOnSetter personAnnotOnSetter = appContextAnnotations.getBean("personAnnotOnSetter",
                PersonAutowiredOnSetter.class);
        PersonAutowiredOnConstructor personAnnotOnConstructor = appContextAnnotations.getBean(
                "personAnnotOnConstructor", PersonAutowiredOnConstructor.class);
        PersonAutowiredOnProperties personAnnotOnProperties = appContextAnnotations.getBean("personAnnotOnProperties"
                , PersonAutowiredOnProperties.class);

        LOG.info("\n======== JAVA annotations configuration =========");
        LOG.info("\n personAnnotOnSetter {}", personAnnotOnSetter);
        LOG.info("\n personAnnotOnConstructor {}", personAnnotOnConstructor);
        LOG.info("\n personAnnotOnProperties {}", personAnnotOnProperties);


    }
}
