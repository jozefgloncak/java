package gloncak.jozef.java8.features.def.methods.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface FootballPlayer {
    Logger LOG = LoggerFactory.getLogger(FootballPlayer.class);

    default void introduce() {
        LOG.info("Hello I am a football player.");
    }

}
