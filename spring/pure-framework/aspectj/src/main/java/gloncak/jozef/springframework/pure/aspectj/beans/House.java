package gloncak.jozef.springframework.pure.aspectj.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class House {
    private static final Logger LOG = LoggerFactory.getLogger(House.class);

    private boolean isOpened;
    private int number;

    public boolean getOpened() {
        LOG.info("in method getOpened()");
        return isOpened;
    }

    public void setIsOpened(boolean opened) {
        LOG.info("in method setIsOpened()");
        isOpened = opened;
    }

    public int getNumber() {
        LOG.info("in method getNumber()");
        return number;
    }

    public void setNumber(int number) {
        LOG.info("in method setNumber()");
        this.number = number;
    }

    public void exampleException() {
        LOG.info("in method exampleException()");
        throw new IllegalStateException();
    }
}
