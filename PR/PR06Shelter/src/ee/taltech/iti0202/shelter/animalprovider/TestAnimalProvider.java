package ee.taltech.iti0202.shelter.animalprovider;
import ee.taltech.iti0202.shelter.animal.Animal;
import ee.taltech.iti0202.shelter.animal.Hirola;
import ee.taltech.iti0202.shelter.animal.Tarantula;
import ee.taltech.iti0202.shelter.animal.Wombat;

import java.util.LinkedList;
import java.util.List;

public class TestAnimalProvider implements AnimalProvider {

    public String color;

    /**
     * Constructor.
     */
    public TestAnimalProvider() {
        this.color = "red";
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public List<Animal> provide(Animal.Type type) {
        List<Animal> animals = new LinkedList<>();
        for (short i = 0; i < 5; i++) {
            Animal animal;
            if (type.equals(Animal.Type.HIROLA)) {
                animal = new Hirola(color);
            } else if (type.equals(Animal.Type.TARANTULA)) {
                animal = new Tarantula(color);
            } else {
                animal = new Wombat(color);
            }
            animals.add(animal);
        }
        return animals;
    }
}