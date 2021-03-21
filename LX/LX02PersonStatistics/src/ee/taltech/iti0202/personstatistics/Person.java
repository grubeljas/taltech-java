package ee.taltech.iti0202.personstatistics;

public class Person {

    String firstName, lastName, occupation, nation;
    int age;
    double height;
    Gender gender;

    public enum Gender {
        MALE, FEMALE
    }

    public Person(String firstName, String lastName, int age, double height, Gender gender, String occupation,
                  String nation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.occupation = occupation;
        this.nation = nation;
        this.age = age;
        this.height = height;
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public Gender getGender() {
        return gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getLastNameSize() {
        return getLastName().length();
    }

    public String getNation() {
        return nation;
    }

    public String getOccupation() {
        return occupation;
    }

}
