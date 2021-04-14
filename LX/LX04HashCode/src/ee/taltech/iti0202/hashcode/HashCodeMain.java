package ee.taltech.iti0202.hashcode;
import java.util.HashMap;
import java.util.Map;

class HashCodeMain {
    public static void main(String[] args) {

        //
        Map<Person, Integer> numbers = new HashMap<>();
        Person person1 = new Person("Ago", "von", "Luberg", 20);
        Person person2 = new Person("Ago", "von", "Luberg", 20);
        Person person3 = new Person("Ago", "", "Luberg", 20);
        Person person4 = new Person("Ago", "blah", "Luberg", 20);
        Person person5 = new Person("Ago", "von", "Luberg", 25);
        Person person6 = new Person("Ago", "von", "Luberg", 35);
        numbers.put(person1, 123);
        numbers.put(person2, 345);
        numbers.put(person3, 3);
        numbers.put(person4, 6);
        numbers.put(person5, 7);
        numbers.put(person6, 8);
        System.out.println(person1.equals(person3));  // true
        System.out.println(person1.equals(person4));  // false
        System.out.println(person1.equals(person5));  // true
        System.out.println(person1.equals(person6));  // false
        System.out.println(numbers);
        // {Person{firstName='Ago', lastName='Luberg', middleName='von', age=35}=8, Person{firstName='Ago', lastName='Luberg', middleName='von', age=20}=7, Person{firstName='Ago', lastName='Luberg', middleName='blah', age=20}=6}
    }
}
