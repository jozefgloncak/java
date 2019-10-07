package gloncak.jozef.springframework.pure.scopes;

import gloncak.jozef.springframework.pure.scopes.bean.Envelope;
import gloncak.jozef.springframework.pure.scopes.bean.Message;
import gloncak.jozef.springframework.pure.scopes.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        scopesViaXML();
        scopesViaAnnotations();
    }

    private static void scopesViaAnnotations() {
        LOG.info("");
        LOG.info("Annotation configuration of bean scope");
        LOG.info(">>>>>Singleton scope");
        ApplicationContext appContextAnnotation = new AnnotationConfigApplicationContext(AppConfig.class);

        Envelope envelopeSingleton1 = appContextAnnotation.getBean("envelopeSingleton", Envelope.class);
        envelopeSingleton1.setTitle("title singleton 1");

        Envelope envelopeSingleton2 = appContextAnnotation.getBean("envelopeSingleton", Envelope.class);
        envelopeSingleton2.setTitle("title singleton 2");
        LOG.info(envelopeSingleton1.getTitle());
        LOG.info(envelopeSingleton2.getTitle());

        LOG.info(">>>>>Prorotype scope");
        Envelope envelopePrototype1 = appContextAnnotation.getBean("envelopePrototype", Envelope.class);
        envelopePrototype1.setTitle("title prototype 1");

        Envelope envelopePrototype2 = appContextAnnotation.getBean("envelopePrototype", Envelope.class);
        envelopePrototype2.setTitle("title prototype 2");

        LOG.info(envelopePrototype1.getTitle());
        LOG.info(envelopePrototype2.getTitle());
    }

    private static void scopesViaXML() {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("Beans.xml");

        //singleton scope
        LOG.info("XML configuraton of bean scope");
        LOG.info(">>>>>Singleton scope");
        Message msgSingleton1 = appContext.getBean("msgSingleton", Message.class);
        msgSingleton1.setMessage("message singleton 1");

        Message msgSingleton2 = appContext.getBean("msgSingleton", Message.class);
        msgSingleton2.setMessage("message singleton 2");

        //msgSingleton1 and msgSingleton2 are the same instance
        LOG.info(msgSingleton1.getMessage());
        LOG.info(msgSingleton2.getMessage());

        //prototype scope
        LOG.info(">>>>>Prorotype scope");
        Message msgPrototype1 = appContext.getBean("msgProto", Message.class);
        msgPrototype1.setMessage("message prototype 1");

        Message msgPrototype2 = appContext.getBean("msgProto", Message.class);
        msgPrototype2.setMessage("message prototype 2");

        //msgPrototype1 and msgPrototype2 are 2 different instances
        LOG.info(msgPrototype1.getMessage());
        LOG.info(msgPrototype2.getMessage());
    }
}
