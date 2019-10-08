package gloncak.jozef.springframework.pure.bean.postprocesors;

import gloncak.jozef.springframework.pure.bean.postprocesors.beans.Car;
import gloncak.jozef.springframework.pure.bean.postprocesors.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class App {
    public static void main(String[] args) {
        final ApplicationContext appCtx = new AnnotationConfigApplicationContext(AppConfig.class);
        ((AbstractApplicationContext)appCtx).registerShutdownHook();

        final Car car = appCtx.getBean("car", Car.class);
    }
}
