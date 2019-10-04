package gloncak.jozef.springframework.pure.configuration.via.java;

import gloncak.jozef.springframework.pure.configuration.via.java.config.AppConfig;
import gloncak.jozef.springframework.pure.configuration.via.java.service.ServiceA;
import gloncak.jozef.springframework.pure.configuration.via.java.service.ServiceB;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext appCtx = new AnnotationConfigApplicationContext(AppConfig.class);
        ServiceA srvA = appCtx.getBean("srvA", ServiceA.class);
        srvA.startService();

        ServiceB srvB = appCtx.getBean("srvB", ServiceB.class);
        srvB.startService();
    }
}
