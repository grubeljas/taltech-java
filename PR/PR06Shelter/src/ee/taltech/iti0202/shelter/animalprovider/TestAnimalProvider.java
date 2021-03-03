package ee.taltech.iti0202.shelter.animalprovider;
import ee.taltech.iti0202.shelter.animal.Animal;
import ee.taltech.iti0202.shelter.animal.Hirola;
import ee.taltech.iti0202.shelter.animal.Tarantula;
import ee.taltech.iti0202.shelter.animal.Wombat;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TestAnimalProvider implements AnimalProvider {

    public Set<Animal> animals;
    public int i;

    /**
     * Constructor.
     */
    public TestAnimalProvider() {
        i = 0;
        this.animals = new HashSet<>();
        animals.add(new Hirola("red"));
        animals.add(new Hirola("red"));
        animals.add(new Hirola("white"));
        animals.add(new Tarantula("red"));
        animals.add(new Tarantula("green"));
        animals.add(new Tarantula("green"));
        animals.add(new Tarantula("red"));
        animals.add(new Wombat("red"));
        animals.add(new Wombat("green"));
        animals.add(new Wombat("green"));
        animals.add(new Wombat("red"));
    }


    @Override
    public List<Animal> provide(Animal.Type type) {
        Set<Animal> providedAnimalsSet = animals.stream()
                .filter(animal1 -> animal1.type.equals(type))
                .collect(Collectors.toSet());
        List<Animal> providedAnimals = new LinkedList<>(providedAnimalsSet);
        try {
            providedAnimals = providedAnimals.subList(i, i + 3);
            i += 2;
        } catch (IndexOutOfBoundsException e) {
            try {
                providedAnimals = providedAnimals.subList(i, i + 2);
                i += 2;
            } catch (IndexOutOfBoundsException e1) {
                providedAnimals.clear();
            }
        }
        return providedAnimals;
    }
}
