package gloncak.jozef.springframework.pure.custom.events.event.handler;

import gloncak.jozef.springframework.pure.custom.events.event.MyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

public class MyEventHandler implements ApplicationListener<MyEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(MyEventHandler.class);

    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        LOG.info("MyEvent: {}", myEvent.getEventID());
    }
}
