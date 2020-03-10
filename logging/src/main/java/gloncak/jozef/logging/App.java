package gloncak.jozef.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);


    public static void main(String[] args) {
        LOG.info("Logging at info level");
        LOG.debug("Logging at debug level");
        LOG.error("Logging at error level");
    }
}
