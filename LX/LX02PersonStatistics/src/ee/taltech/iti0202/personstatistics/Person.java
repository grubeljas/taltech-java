package ee.taltech.iti0202.personstatistics;

public class Person {

    String firstName, lastName, occupation, nation;
    int age;
    double height;
    Gender gender;

    /**
     * Constructor.
     * @param firstName
     * @param lastName
     * @param age
     * @param gender
     * @param height
     * @param occupation
     * @param nation
     */
    public Person(String firstName, String lastName, int age, Gender gender, double height, String occupation,
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

    public String getNationality() {
        return nation;
    }

    public String getOccupation() {
        return occupation;
    }

}
