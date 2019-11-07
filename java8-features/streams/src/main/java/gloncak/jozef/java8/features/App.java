package gloncak.jozef.java8.features;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Eva", "Jozef", "Marian", "Zuzana", "Denisa", "Andrea", "Zuzana", "Kamil",
                "Xaver", "Beata", "Jozef");

        //for each
        //uppercased names.
        LOG.info(">>>stream forEach");
        names.stream().forEach(name -> LOG.info("{}", name.toUpperCase()));
        LOG.info(">>>Direct forEach()");
        names.forEach(name -> LOG.info("{}", name.toLowerCase()));

        //map
        //map every string name to Person object
        LOG.info(">>>map()");
        names.stream().map(Person::new).forEach(person -> LOG.info("{}", person));

        //filter
        //filter names which contains 'e'
        LOG.info(">>>filter() - contains 'e'");
        names.stream().filter(name -> name.contains("e")).forEach(name -> LOG.info("{}", name));

        //filter names which contains 2 'a'
        LOG.info(">>>filter() - contains 2 'a'");
        names.stream().filter(name -> name.matches(".*[a|A].*[a|A].*")).forEach(name -> LOG.info("{}", name));

        //filter names which are 5 characters long
        LOG.info(">>>filter() - has length 5");
        names.stream().filter(name -> name.length() == 5).forEach(name -> LOG.info("{}", name));

        //limit()
        //limit size of stream to 3 elements
        LOG.info(">>>limit() - display first 3 elements of list");
        names.stream().limit(3).forEach(name -> LOG.info("{}", name));

        //sorted()
        LOG.info(">>>sorted() - display elements sorted");
        names.stream().sorted((name1, name2) -> name1.compareTo(name2)).forEach(name -> LOG.info("{}", name));

        //collectors
        LOG.info(">>>collector list");
        List<String> lowerCasedNames = names.stream().map(String::toLowerCase).collect(Collectors.toList());
        lowerCasedNames.forEach(name -> LOG.info(name));

        LOG.info(">>>collector concatenated string");
        String reversedNames = names.stream().map(name -> {
            StringBuilder strBuilder = new StringBuilder(name);
            return strBuilder.reverse().toString();
        }).collect(Collectors.joining(", ", "names:[", "](reversed order)"));
        LOG.info(reversedNames);

        LOG.info(">>>maximum value of collection");
        Optional<String> maxLengthName = names.stream().max(Comparator.comparingInt(String::length));
        LOG.info(maxLengthName.get());

        LOG.info(">>>minimum value of collection");
        Optional<String> minLengthName = names.stream().min(Comparator.comparingInt(String::length));
        LOG.info(minLengthName.get());

        //count()
        LOG.info(">>>count() - count names starting with vowel");
        long numberOfNamesStartingWithVowel = names.stream().filter(name -> name.matches("[AEIOUaeiou].*")).count();
        LOG.info("{}", numberOfNamesStartingWithVowel);

        //returns whatever element of collection
        LOG.info(">>>findAny() - whatever name from collection");
        LOG.info("{}", names.stream().findAny().get());

        //distinct result collection (all duplicities are removed)
        LOG.info(">>>distinct - remove duplicite names");
        names.stream().distinct().forEach(name -> LOG.info("{}", name));

        //peek is operation for debuging it make it possible to see e. g. state of elements during processing of stream
        LOG.info(">>>peak() - just process currently processed stream in particular stage");
        names.stream().peek(name -> LOG.info("{}", name));

        //reduce - elements are gradually reduced - ((1 op 2) op 3) op 4.........
        LOG.info(">>>reduce() - concatenation through reduction of elements");
        Optional<String> reducedString = names.stream().reduce((name1, name2) -> name1 += name2);
        LOG.info(reducedString.get());

        //flat map
        //list of all subjects of all persons
        LOG.info(">>>flatMap() - all subjects of all persons");
        List<Person> persons = Arrays.asList(
                new Person("Martin", Arrays.asList("Math", "English"))
                ,new Person("Juraj", Arrays.asList("Math", "History", "Literature", "Chemistry"))
                ,new Person("Aneta",  Arrays.asList("English", "Literature", "Spanish", "French"))
        );

        persons.stream().flatMap(person -> person.getSubjects().stream()).forEach(subject -> LOG.info("{}", subject));

        //Collectors.counting() - count num of elements through collector
        LOG.info(">>>Collectors.counting() - count num of elements through collector");
        LOG.info("{}", names.stream().collect(Collectors.counting()));

        //Collectors.averagingInt() - returns average length of names
        LOG.info(">>>Collectors.averagingInt() - returns average length of names");
        LOG.info("{}", names.stream().collect(Collectors.averagingInt(String::length)));

        //Collectors.partitioningBy()
        LOG.info(">>>Collectors.partitioningBy() - divide stream to 2 separate lists according starts|no starts with " +
                "vowel");
        Predicate<? super String> startsWithVowel = str -> str.matches("[aeiouAEIOU].*");
        Map<Boolean, List<String>> devidedByVowels = names.stream().collect(Collectors.partitioningBy(startsWithVowel));
        LOG.info("Start with vowel: {}", devidedByVowels.get(true));
        LOG.info("Start with consonant: {}", devidedByVowels.get(false));

        //groupingBy()
        LOG.info(">>>groupingBy() - group by name length. result is map where key is name length and value is list " +
                "of names with this length");
        Map<Integer, List<String>> collect = names.stream().collect(Collectors.groupingBy(String::length));
        LOG.info("{}", collect);

        //groupingBy() with mapped values
        Function<? super String, Integer> sumUpVowels = str -> {
            str = str.toLowerCase();
            int counter = 0;
            for (int i = 0; i<str.length(); i++) {
                if(str.charAt(i) =='a' || str.charAt(i)=='e' || str.charAt(i)=='i' || str.charAt(i)=='o'
                        || str.charAt(i)=='u' || str.charAt(i)=='y') {
                        counter++;
                }
            }
            return counter;
        };
        LOG.info(">>>groupingBy() - group by name length. result is map where key is name length and value is set of " +
                "numbers which represents number of vowels in names (if Eva then 2, if Zuzana then 3...)");
        Map<Integer, Set<Integer>> collect2 = names.stream().collect(Collectors.groupingBy(String::length,
                Collectors.mapping(sumUpVowels, Collectors.toSet())));
        System.out.println(collect2);
    }
}
