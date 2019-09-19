package gloncak.jozef.design.pattern.command;

import gloncak.jozef.design.pattern.command.impl.person.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 *
 */
public class Main
{

    public static final Logger LOG = LoggerFactory.getLogger(Main.class);

    private static final List<Person> persons = Collections.emptyList();
    private static final List<Person> personArchive = Collections.emptyList();

    public static void main( String[] args )
    {
        LOG.info("Hello World!");
    }

    private static void generatePersons() {

    }

    private static void destroyPersons() {

    }
}
