package ee.taltech.iti0202.personstatistics;

import java.util.List;
import java.util.OptionalDouble;
import java.util.Optional;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * For calculating and finding statistical info based on persons.
 */
public class PersonStatistics {

    private final List<Person> persons;

    /**
     * Constructor which stores the given list.
     */
    public PersonStatistics(List<Person> persons) {
        this.persons = persons;
    }

    /**
     * Return the number of people in the list.
     */
    public long countPersons() {
        long number = persons.stream()
                .count();
        return number;
    }

    /**
     * Find the average height of the persons.
     */
    public OptionalDouble findAverageHeight() {
        double sumOfAll = 0.0;
        Double sum = persons.stream()
                .mapToDouble(Person::getAge)
                .sum();
        OptionalDouble average = OptionalDouble.of(sum / persons.size());
        return average;
    }

    /**
     * Return the person with the lowest age.
     */
    public Optional<Person> findYoungestPerson() {
        Optional<Person> person = persons.stream()
                .min(Comparator.comparing(Person::getAge));
        return person;
    }

    /**
     * Return the person with the highest age.
     */
    public Optional<Person> findOldestPerson() {
        Optional<Person> person = persons.stream()
                .max(Comparator.comparing(Person::getAge));
        return person;
    }

    /**
     * Return the longest last name.
     */
    public Optional<String> findLongestLastName() {
        Optional<Person> person = persons.stream()
                .max(Comparator.comparing(Person::getLastNameSize));
        return Optional.of(person.get().getLastName());
    }

    /**
     * Return a list of nationalities.
     */
    public List<String> getNationalityData() {
        List<String> nations = persons.stream()
                .map(Person::getNationality)
                .collect(Collectors.toList());
        return nations;
    }

    /**
     * Converts persons heights from m to cm.
     *
     * @return list of heights in cm
     */
    public List<Double> getHeightInCm() {
        List<Double> heights = persons.stream()
                .map(Person::getHeightInMeters)
                .map(p -> p * 100)
                .collect(Collectors.toList());
        return heights;
    }

    /**
     * Return a sublist with the given size.
     */
    public List<Person> findSamples(int sampleSize) {
        if (sampleSize < 0) {
            return new LinkedList<>();
        }
        List<Person> sample = persons.stream()
                .limit(sampleSize)
                .collect(Collectors.toList());
        return sample;
    }

    /**
     * Find first person matching provided parameters criterias.
     *
     * @return first matching person
     */
    public Optional<Person> findSamplePerson(String nationality, Gender gender, int age) {
        Optional<Person> person = persons.stream()
                .filter(person1 -> person1.getNationality().equals(nationality))
                //.filter(person1 -> person1.getGender().equals(gender))
                .filter(person1 -> person1.getAge() == age)
                .findFirst();
        return person;
    }

    /**
     * Find unique first names.
     */
    public Set<String> getDistinctFirstNames() {
        Set<String> names = persons.stream()
                .map(Person::getFirstName)
                .collect(Collectors.toSet());
        return null;
    }

    /**
     * Order persons from tallest to shortest.
     *
     * @return ordered list of persons
     */
    public List<Person> getReverseOrderedByHeight() {
        List<Person> sorted = persons.stream()
                .sorted(Comparator.comparingDouble(Person::getHeightInMeters)
                .reversed())
                .collect(Collectors.toList());
        return sorted;
    }

    /**
     * Return list of people whose age is between ageFrom to ageTo (inclusive).
     */
    public List<Person> findBetweenAge(int ageFrom, int ageTo) {
        List<Person> between = persons.stream()
                .filter(person -> person.getAge() <= ageTo)
                .filter(person -> person.getAge() >= ageFrom)
                .collect(Collectors.toList());
        return between;
    }

    /**
     * Find persons whose first name first letter is same as his/her nationality first letter.
     *
     * @return list of matching persons
     */
    public List<Person> findSameLetterNameAndNationality() {
        List<Person> people = persons.stream()
                .filter(person -> person.getFirstName().substring(0, 1).equals(person.getNationality().substring(0, 1)))
                .collect(Collectors.toList());
        return people;
    }

    /**
     * Create map where each occupation has list of persons who have that occupation.
     *
     * @return map of occupations with persons
     */
    public Map<String, List<Person>> mapOccupationToPersons() {
        persons.stream()
                .collect(Collectors.groupingBy(Person::getOccupation));
        return mapOccupationToPersons();
    }

    public static void main(String[] args) {
        CsvPersonMapper mapper = new CsvPersonMapper();
        List<Person> persons = mapper.mapToPersons("persons.csv");
        PersonStatistics statistics = new PersonStatistics(persons);
        System.out.println(statistics.countPersons());
        Person petr = new PersonBuilder()
                .withAge(1)
                    .withHeight(1.5)
                    .withLastName("TOM")
                    .withName("TOMAS")
                    .withOccupation("Builder")
                    .withNation("estonia")
                .build();
        System.out.println(statistics.findAverageHeight());
    }

}
