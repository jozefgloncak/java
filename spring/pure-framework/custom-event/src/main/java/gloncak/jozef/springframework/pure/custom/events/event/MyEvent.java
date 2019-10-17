package gloncak.jozef.springframework.pure.custom.events.event;

import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {
    private static int counter = 1;
    private int eventID;
    public MyEvent(Object source) {
        super(source);
        eventID = counter++;
    }

    public int getEventID() {
        return eventID;
    }
}
