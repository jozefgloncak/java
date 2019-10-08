package gloncak.jozef.springframework.pure.bean.postprocesors.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Car {
    private static final Logger LOG = LoggerFactory.getLogger(Car.class);

    public void init() {
        LOG.info("initialization of Car instance");
    }

    public void destroy() {
        LOG.info("destroying of Car instance");
    }
}
