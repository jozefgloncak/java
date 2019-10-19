package gloncak.jozef.springframework.pure.aspectj.beans;

import org.slf4j.LoggerFactory;

public class AspectLogger {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(AspectLogger.class);

    public void beforeAdvice(){
        LOG.info("Before method call.");
    }

    public void afterAdvice(){
        LOG.info("After method call.");
    }

    public void afterReturningAdvice(Object retVal) {
        LOG.info("After successful method call - return value {}", retVal != null ? retVal.toString() : "" );
    }

    public void afterThrowingAdvice(Exception ex){
        LOG.info("After exceptional method call - exception {}", ex.toString());
    }
}
