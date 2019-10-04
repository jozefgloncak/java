package gloncak.jozef.springframework.pure.configuration.via.java.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceA {
    private static final Logger LOG = LoggerFactory.getLogger(ServiceB.class);

    public void startService() {
        LOG.info("Starting service A");
    }
}
