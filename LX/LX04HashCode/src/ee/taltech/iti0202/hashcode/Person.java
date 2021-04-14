package ee.taltech.iti0202.hashcode;

import java.util.Objects;

public class Person {
    private String firstName;
    private String lastName;
    private String middleName;
    private int age;
    private final int dECADE = 10;

    /**
     * Construct.
     * @param firstName
     * @param middleName
     * @param lastName
     * @param age
     */
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
        return age / dECADE == person.age / dECADE && Objects.equals(firstName.substring(0, 1).toLowerCase(),
                person.firstName.substring(0, 1).toLowerCase())
                && Objects.equals(lastName, person.lastName)
                && (Objects.equals(middleName, person.middleName)
                || middleName.isEmpty() || person.middleName.isEmpty());
    }

    @Override
    public int hashCode() {
            return (firstName.length() * dECADE * dECADE + lastName.length() * dECADE) * (1 + age / dECADE);
    }

    @Override
    public String toString() {
        return "Person{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
                + ", middleName='" + middleName + '\'' + ", age=" + age + '}';
    }
}
