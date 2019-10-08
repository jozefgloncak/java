package gloncak.jozef.springframework.pure.bean.postprocesors.postprocesor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class CustomPostprocesor implements BeanPostProcessor {
    private static final Logger LOG = LoggerFactory.getLogger(CustomPostprocesor.class);

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        LOG.info("Procesing bean {} in post processing - before initialization.", beanName);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        LOG.info("Procesing bean {} in post processing - after initialization.", beanName);
        return bean;
    }
}
