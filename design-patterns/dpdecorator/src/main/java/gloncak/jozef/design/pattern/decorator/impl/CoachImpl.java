package gloncak.jozef.design.pattern.decorator.impl;

import gloncak.jozef.design.pattern.decorator.api.Coach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoachImpl implements Coach {
    private static final Logger LOG = LoggerFactory.getLogger(CoachImpl.class);

    private boolean isMoving;
    private boolean breakPulled;

    @Override
    public boolean isMoving() {
        LOG.info("Call method: isMoving()");
        return isMoving;
    }

    @Override
    public void pullBreak() {
        LOG.info("Call method: pullBreak()");
        breakPulled = true;
    }

    @Override
    public void releaseOfBreak() {
        LOG.info("Call method: releaseOfBreak()");
        breakPulled = false;
    }
}
