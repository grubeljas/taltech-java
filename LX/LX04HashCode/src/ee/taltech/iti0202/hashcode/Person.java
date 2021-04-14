package ee.taltech.iti0202.hashcode;

import java.util.Objects;

public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private int age;

    public Person(String firstName, String middleName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age / 10 == person.age / 10 && Objects.equals(firstName.charAt(0), person.firstName.charAt(0))
                && Objects.equals(lastName, person.lastName)
                && (Objects.equals(middleName, person.middleName)
                || middleName.isEmpty() || person.middleName.isEmpty());
    }

    @Override
    public int hashCode() {
        return (firstName.length() * 100 + lastName.length() * 10) * (1 + age / 10);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", age=" + age +
                '}';
    }
}
