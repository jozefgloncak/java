package gloncak.jozef.springframework.pure;

import gloncak.jozef.springframework.pure.api.beans.Car;
import gloncak.jozef.springframework.pure.config.AppConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext testCtx = new AnnotationConfigApplicationContext();
        testCtx.getEnvironment().setActiveProfiles("test");
        testCtx.register(AppConfiguration.class);
        testCtx.refresh();

        //car from test profile
        Car car = testCtx.getBean(Car.class);
        car.getPlate();
        car.getType();

        try  {
            String manager = testCtx.getBean("manager", String.class);
        } catch (NoSuchBeanDefinitionException e) {
            LOG.info("Bean manager wasn't defined in test scope");
        }
        LOG.info("Driver: {}", testCtx.getBean("driver", String.class));

        //activating profile through system property
        System.setProperty("spring.profiles.active", "production");
        AnnotationConfigApplicationContext productionCtx = new AnnotationConfigApplicationContext();
//        productionCtx.getEnvironment().setActiveProfiles("production");
        productionCtx.register(AppConfiguration.class);
        productionCtx.refresh();

        //car from production profile
        car = productionCtx.getBean(Car.class);
        car.getPlate();
        car.getType();
        LOG.info("Manager: {}", productionCtx.getBean("manager", String.class));
        try  {
            LOG.info("Driver: {}", productionCtx.getBean("driver", String.class));
        } catch (NoSuchBeanDefinitionException e) {
            LOG.info("Bean driver wasn't defined in test scope");
        }

    }
}
