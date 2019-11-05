package gloncak.jozef.java8.features.def.methods.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Student {
    Logger LOG = LoggerFactory.getLogger(Student.class);

    default void introduce() {
        LOG.info("Good morning I am a student.");
    }

}
