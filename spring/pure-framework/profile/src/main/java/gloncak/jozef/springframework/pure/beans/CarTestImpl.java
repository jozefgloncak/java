package gloncak.jozef.springframework.pure.beans;

import gloncak.jozef.springframework.pure.api.beans.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarTestImpl implements Car {
    private static final Logger LOG = LoggerFactory.getLogger(CarProductionImpl.class);

    @Override
    public String getType() {
        LOG.info("getType() test implementation");
        return null;
    }

    @Override
    public String getPlate() {
        LOG.info("getPlate() test implementation");
        return null;
    }
}
