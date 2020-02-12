package gloncak.jozef.jsp.integratespringjsp2.service;

import gloncak.jozef.jsp.integratespringjsp2.api.PersonGeneratorService;
import gloncak.jozef.jsp.integratespringjsp2.model.Person;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Service for generation of pseudo random persons.
 */
public class PersonGeneratorServiceImpl implements PersonGeneratorService {
    private static final List<String> NAMES = Arrays.asList("Martin", "Karol", "Marian", "Adam", "Zita", "Gregor",
            "Eva", "Jozef", "Zuzana", "Jana", "Kamila", "Sergej", "Zita", "Zoana", "Natanael", "Stefan", "Martina",
            "Jolana", "Lubos", "Ivana", "Maria", "Anna", "Monika", "Lubomir", "Hana", "Milan", "Milena", "Stano",
            "Samuel", "Henrich", "Zdena", "Lili", "Emily");
    private static final long NAMES_SEED = 20;
    private static final long AGE_SEED = 200;
    private static final long JOB_START_SEED = 17;

    @Override
    public List<Person> generatePersons(int numOfPersons) {
        final List<Person> persons = new ArrayList<>();
        Random namesGenerator = new Random(NAMES_SEED);
        Random ageGenerator = new Random(AGE_SEED);
        Random jobStartGenerator = new Random(JOB_START_SEED);

        for (int i = 0; i < numOfPersons; i++) {
            int namesIdx = namesGenerator.nextInt(NAMES.size());
            Person newPerson = new Person(NAMES.get(namesIdx), generateAge(ageGenerator),
                    (int) (Math.round(ageGenerator.nextGaussian() * 8) + 150), getDate(jobStartGenerator));
            persons.add(newPerson);
        }
        return persons;
    }

    private int generateAge(Random ageGenerator) {
        while (true) {
            int ageCandidate = (int) (Math.round(ageGenerator.nextGaussian() * 13) + 35);
            if (ageCandidate > 18) {
                return ageCandidate;
            }
        }
    }

    private LocalDate getDate(Random dateGenerator) {
        long epochSecond = Instant.now().getEpochSecond();

        //10 years
        int past = Math.abs(Math.round(dateGenerator.nextInt(315360000)));
        Instant instant = Instant.ofEpochSecond(epochSecond - past);
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
