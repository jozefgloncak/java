package gloncak.jozef.springframework.pure.custom.events;

import gloncak.jozef.springframework.pure.custom.events.publisher.MyEventPublisher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Create context and publish custom events.
 */
public class App
{
    public static void main( String[] args ) {
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("Beans.xml");
        MyEventPublisher myEventPublisher = appCtx.getBean("myEventPublisher", MyEventPublisher.class);

        //publish custom events
        //events are handled in 2 various handlers
        myEventPublisher.publishEvent();
        myEventPublisher.publishEvent();
    }
}
