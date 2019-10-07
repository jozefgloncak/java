package gloncak.jozef.springframework.pure.bean.lifecycle.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Person {

    private static final Logger LOG = LoggerFactory.getLogger(Person.class);

    public void init() {
        LOG.info("initializing Person instance");
    }

    public void destroy() {
        LOG.info("destroying Person instance");
    }
}
