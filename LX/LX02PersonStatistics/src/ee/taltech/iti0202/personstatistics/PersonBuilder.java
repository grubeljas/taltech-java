package ee.taltech.iti0202.personstatistics;
import ee.taltech.iti0202.personstatistics.Person.Gender;

public class PersonBuilder {

    String firstName, lastName, occupation, nation;
    int age;
    double height;
    Gender gender;

    public PersonBuilder withName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PersonBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PersonBuilder withOccupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    public PersonBuilder withNation(String nation) {
        this.nation = nation;
        return this;
    }

    public PersonBuilder withAge(int age) {
        this.age = age;
        return this;
    }

    public PersonBuilder withHeight(double height) {
        this.height = height;
        return this;
    }

    public PersonBuilder withGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public Person build() {
        return new Person(firstName, lastName, age, height, gender, occupation, nation);
    }

}
