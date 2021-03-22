package ee.taltech.iti0202.personstatistics;

public class PersonBuilder {

    String firstName, lastName, occupation, nation;
    int age;
    double height;
    /**Gender gender;*/

    /**
     * Name.
     *
     * @param firstName
     * @return
     */
    public PersonBuilder withName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Name.
     *
     * @param lastName
     * @return
     */
    public PersonBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Occupation.
     *
     * @param occupation
     * @return
     */
    public PersonBuilder withOccupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    /**
     * Nation.
     *
     * @param nation
     * @return
     */
    public PersonBuilder withNation(String nation) {
        this.nation = nation;
        return this;
    }

    /**
     * Age.
     *
     * @param age
     * @return
     */
    public PersonBuilder withAge(int age) {
        this.age = age;
        return this;
    }

    /**
     * Height.
     *
     * @param height
     * @return
     */
    public PersonBuilder withHeight(double height) {
        this.height = height;
        return this;
    }


    /**public PersonBuilder withGender(Gender gender) {
        this.gender = gender;
        return this;
    }*/

    /**
     * Build person object.
     *
     * @return
     */
    public Person build() {
        return new Person(firstName, lastName, age, height, occupation, nation);
    }

}
