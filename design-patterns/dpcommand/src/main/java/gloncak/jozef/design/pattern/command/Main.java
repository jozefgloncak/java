package gloncak.jozef.design.pattern.command;

import gloncak.jozef.design.pattern.command.api.command.Archivator;
import gloncak.jozef.design.pattern.command.impl.person.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Demonstrate usage of various implementation imlementation of archivator (design pattern command)
 */
public class Main
{

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    private static List<Person> persons = Collections.emptyList();
    private static final List<Person> personArchive = new ArrayList<>();

    public static void main( String[] args )
    {
        //console archivator
        LOG.info(">>>console archivator START");
        persons = generatePersons();
        persons
            .forEach(person -> person.destroy(person1 ->
                System.out.format("%s %s %n", person1.getFirstName(), person1.getSurname())
            ));
        LOG.info(">>>console archivator END");

        LOG.info(":::::::::::::delimitter::::::::::::");

        //console log archivator
        LOG.info(">>>console log archivator START");
        persons = generatePersons();
        persons
            .forEach(person -> person.destroy(person1 -> LOG.info("{} {}", person1.getFirstName(), person1.getSurname())));
        LOG.info(">>>console log archivator END");

        LOG.info(":::::::::::::delimitter::::::::::::");

        //archivator to standalone array
        LOG.info(">>>other array archivator START");
        LOG.info("Content of archive array BEFORE archivation {}", personArchive);
        persons = generatePersons();
        while (persons.size() > 0) {
            final Person person = persons.get(0);
            person.destroy(person1 -> {
                persons.remove(person1);
                personArchive.add(person1);
            });
        }
        LOG.info("Content of archive array AFTER archivation {}", personArchive);
        LOG.info(">>>other array archivator END");

        LOG.info(":::::::::::::delimitter::::::::::::");

        //file archivator
        LOG.info(">>>file archivator START");
        persons = generatePersons();
        try {
            //just replace file if exists
            new FileWriter("dpcommand/archive.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        persons
            .forEach(person -> person.destroy(person1 -> {
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("dpcommand/archive.txt", true));
                    bw.write(person1.getFirstName());
                    bw.write(",");
                    bw.write(person1.getSurname());
                    bw.newLine();
                    bw.flush();
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
        LOG.info(">>>file archivator END");
    }

    private static List<Person> generatePersons() {
        ArrayList<Person> newPersons = new ArrayList<>();
        newPersons.add(new Person("Andrej", "Andrejovic"));
        newPersons.add(new Person("Beata", "Blanarovic"));
        return newPersons;
    }
}
