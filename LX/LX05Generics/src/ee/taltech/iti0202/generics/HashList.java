package ee.taltech.iti0202.generics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HashList<T> {

    HashMap<Integer, T> hashMap = new HashMap<>();

    void add(T element) {
        hashMap.put(hashMap.size(), element);
    }

    public T get(int index) {
        return hashMap.get(index);
    }

    public int size() {
        return hashMap.size();
    }

    public void remove(int index) {
        hashMap.remove(index);
    }

    public void addAll(List<T> list) {
        for (T t: list) {
            add((T) t);
        }
    }

    public boolean contains(T element) {
        return hashMap.containsValue(element);
    }

    public static void main(String[] args) {
        class Animal {}
        class Dog extends Animal {}
        class Cat extends Animal {}
        HashList<Animal> animalHashList = new HashList<>();
        Animal animal = new Animal();
        Dog dog1 = new Dog();
        Dog dog2 = new Dog();
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        List<Dog> dogs = new ArrayList<>();
        dogs.add(dog1);
        dogs.add(dog2);
        List<Cat> cats = new ArrayList<>(List.of(cat1, cat2));
        animalHashList.add(animal);
        System.out.println(animalHashList.size()); // 1
        System.out.println(animalHashList.size()); // 5
        System.out.println(animalHashList.get(2));  // Dog@b4c966a
        animalHashList.remove(2);
        System.out.println(animalHashList.get(2));  // Cat@2f4d3709
        System.out.println(animalHashList.contains(dog1)); // true
        System.out.println(animalHashList.contains(dog2)); // false
    }
}
