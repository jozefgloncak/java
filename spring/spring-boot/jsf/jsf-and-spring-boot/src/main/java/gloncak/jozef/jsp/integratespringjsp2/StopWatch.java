package gloncak.jozef.jsp.integratespringjsp2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

@ApplicationScope
@Component
public class StopWatch {
    private static final Logger LOG = LoggerFactory.getLogger(StopWatch.class);

    private Duration duration = Duration.of(0, ChronoUnit.SECONDS);

    private boolean disabled;
    private String name;

    private LocalDateTime startPoint;
    private LocalDateTime endPoint;

    private boolean running = false;

    public void startStop(ActionEvent e) {
        if (!running) {
            running = true;
            startPoint = LocalDateTime.now();
        } else {
            running = false;
            endPoint = LocalDateTime.now();
            duration = duration.plus(Duration.between(startPoint, endPoint));
        }
    }

    public void disableEnable(ValueChangeEvent e) {
        LOG.info("Stop wath was changed to {}", (boolean)e.getNewValue()?"enabled":"disabled");
    }

    public void stopWatchNameChanged(ValueChangeEvent e) {
        LOG.info("Stop wath name changed from {} to {}", (String)e.getOldValue(), (String)e.getNewValue());
    }

    public Duration getDuration() {
        return duration;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }



    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
