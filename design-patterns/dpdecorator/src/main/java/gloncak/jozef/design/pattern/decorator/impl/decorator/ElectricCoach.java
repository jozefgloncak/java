package gloncak.jozef.design.pattern.decorator.impl.decorator;

import gloncak.jozef.design.pattern.decorator.impl.adapter.CoachAdapter;
import gloncak.jozef.design.pattern.decorator.api.Coach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElectricCoach extends CoachAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(ElectricCoach.class);

    private boolean isElectricityTurnedOn;

    public ElectricCoach(Coach coach) {
        super(coach);
    }

    public void turnElectricityOn() {
        LOG.info("Call method: turnElectricityOn()");
        this.isElectricityTurnedOn = true;
    }

    public void turnElectricityOff() {
        LOG.info("Call method: turnElectricityOff()");
        this.isElectricityTurnedOn = false;
    }

}
