package gloncak.jozef.springframework.pure.custom.events.publisher;

import gloncak.jozef.springframework.pure.custom.events.event.MyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class MyEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    public void publishEvent() {
        MyEvent myEvent = new MyEvent(this);
        publisher.publishEvent(myEvent);
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
