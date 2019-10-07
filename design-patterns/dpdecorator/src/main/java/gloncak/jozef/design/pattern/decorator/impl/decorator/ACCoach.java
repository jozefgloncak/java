package gloncak.jozef.design.pattern.decorator.impl.decorator;

import gloncak.jozef.design.pattern.decorator.api.PersonalCoach;
import gloncak.jozef.design.pattern.decorator.impl.adapter.PersonalCoachAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Decorator for railway coach with Air Condition (AC)
 */
public class ACCoach extends PersonalCoachAdapter {

    private static final Logger LOG = LoggerFactory.getLogger(ACCoach.class);

    private boolean isACOn;

    public ACCoach(PersonalCoach personalCoach) {
        super(personalCoach);
    }

    public void turnACOn() {
        LOG.info("Call method: turnACOn");
        this.isACOn = true;
    }

    public void turnACOff() {
        this.isACOn = false;
        LOG.info("Call method: turnACOff");
    }
}
