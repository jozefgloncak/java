package gloncak.jozef.design.pattern.decorator.impl;

import gloncak.jozef.design.pattern.decorator.api.PersonalCoach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonalCoachImpl extends CoachImpl implements PersonalCoach {
    private static final Logger LOG = LoggerFactory.getLogger(PersonalCoachImpl.class);

    private int seatsNumber;
    private boolean isToiletVacant;

    @Override
    public int getSeatsNumber() {
        LOG.info("Call method: getSeatsNumber()");
        return seatsNumber;
    }

    @Override
    public boolean isToiletVacant() {
        LOG.info("Call method: isToiletVacant()");
        return isToiletVacant;
    }
}
