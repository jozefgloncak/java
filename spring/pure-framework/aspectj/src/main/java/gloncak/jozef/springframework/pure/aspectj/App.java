package gloncak.jozef.springframework.pure.aspectj;

import gloncak.jozef.springframework.pure.aspectj.beans.House;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class App {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("Beans.xml");

        House house = appCtx.getBean("house", House.class);

        //after calling house method {@link AspectLogger} methods are gradually called.
        house.getOpened();
        house.setIsOpened(true);
        house.setNumber(54);
        house.getNumber();
        try {
            house.exampleException();
        } catch (Exception e) {
            LOG.info("This exception shouldn't be propagated upper. It is just for testing purposes.");
        }
    }
}
