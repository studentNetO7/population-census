import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alexis", "Carol", "Gale", "Kelly", "Kimberly", "Lynn", "Tracy");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_00; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)]));
        }
        Stream<Person> stream = persons.stream();
        Stream<Person> stream1 = persons.stream();
        Stream<Person> stream2 = persons.stream();
        long count = stream
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(count);
        List<String> conscripts = stream1
                .filter(person -> person.getAge() > 18 && person.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(conscripts);
        Predicate<Person> isWomanWorker = person -> person.getSex().equals(Sex.WOMAN) && person.getAge() < 60;
        Predicate<Person> isManWorker = person -> person.getSex().equals(Sex.MAN) && person.getAge() < 65;
        List<Person> employable = stream2
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> person.getAge() > 18)
                .filter(isManWorker.or(isWomanWorker))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(employable);
    }
}