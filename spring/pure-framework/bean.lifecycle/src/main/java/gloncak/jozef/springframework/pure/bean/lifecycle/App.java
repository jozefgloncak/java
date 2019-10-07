package gloncak.jozef.springframework.pure.bean.lifecycle;

import gloncak.jozef.springframework.pure.bean.lifecycle.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main( String[] args ) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("Beans.xml");
        ((AbstractApplicationContext) appContext).registerShutdownHook();

        ApplicationContext appContextAnnoted = new AnnotationConfigApplicationContext(AppConfig.class);
        ((AbstractApplicationContext) appContextAnnoted).registerShutdownHook();
    }
}
