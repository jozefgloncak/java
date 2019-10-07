package gloncak.jozef.springframework.pure.bean.lifecycle.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Subject {
    private static final Logger LOG = LoggerFactory.getLogger(Subject.class);

    public void initialize() {
        LOG.info("initialize Subject instance");
    }

    public void tidyUp() {
        LOG.info("tiding up Subject instance");
    }
}
