package ee.taltech.iti0202.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class KittenStatistics {

    private List<Kitten> kittens;

    public void setKittens(List<Kitten> kittens) {
        this.kittens = kittens;
    }

    /**
     * Find average age.
     *
     * @return
     */
    public OptionalDouble findKittensAverageAge() {
        double sumOfAges = kittens.stream()
                .mapToInt(kitten -> kitten.getAge())
                .sum();
        double average = sumOfAges / kittens.size();
        return OptionalDouble.of(average);
    }

    /**
     * Find oldest kitten.
     *
     * @return
     */
    public Optional<Kitten> findOldestKitten() {
        Optional<Kitten> oldest = kittens.stream()
                .max(Comparator.comparing(kitten -> kitten.getAge()));
        return oldest;
    }

    /**
     * Find all kittens with smallest age.
     *
     * @return
     */
    public List<Kitten> findYoungestKittens() {
        Optional<Kitten> youngest = kittens.stream()
                .min(Comparator.comparing(kitten -> kitten.getAge()));
        List<Kitten> youngests = kittens.stream()
                .filter(kitten -> kitten.getAge() == youngest.get().getAge())
                .collect(Collectors.toList());
        return youngests;
    }

    /**
     * Find kittens with that gender.
     *
     * @param gender
     * @return
     */
    public List<Kitten> findKittensAccordingToGender(Kitten.Gender gender) {
        List<Kitten> filtered = kittens.stream()
                .filter(kitten -> kitten.getGender().equals(gender))
                .collect(Collectors.toList());
        return filtered;
    }

    /**
     * Find kittens within the age gap.
     *
     * @param minAge
     * @param maxAge
     * @return
     */
    public List<Kitten> findKittensBetweenAges(int minAge, int maxAge) {
        List<Kitten> answer = kittens.stream()
                .filter(kitten -> kitten.getAge() <= maxAge && kitten.getAge() >= minAge)
                .collect(Collectors.toList());
        return answer;
    }

    /**
     * Find kitten with given name.
     *
     * @param givenName
     * @return
     */
    public Optional<Kitten> findFirstKittenWithGivenName(String givenName) {
        Optional<Kitten> first = Optional.empty();
        List<Kitten> answer = kittens.stream()
                .filter(kitten -> kitten.getName().equals(givenName))
                .collect(Collectors.toList());
        if (answer.isEmpty()) {
            return first;
        }
        first = Optional.of(answer.get(0));
        return first;
    }

    /**
     * Sort list.
     *
     * @return
     */
    public List<Kitten> kittensSortedByAgeYoungerFirst() {
        List<Kitten> answer = kittens.stream()
                .sorted(Comparator.comparing(Kitten::getAge))
                .collect(Collectors.toList());
        return answer;
    }

    /**
     * Sort list.
     *
     * @return
     */
    public List<Kitten> kittensSortedByAgeOlderFirst() {
        List<Kitten> answer = kittens.stream()
                .sorted(Comparator.comparing(Kitten::getAge))
                .collect(Collectors.toList());
        return answer;
    }

}
