package gloncak.jozef.design.pattern.command.api.command;

import gloncak.jozef.design.pattern.command.impl.person.Person;

/**
 * Interface is used for design pattern Command
 */
public interface Archivator {

    /**
     * Define way of archivation.
     */
    public void archive(Person person);
}
