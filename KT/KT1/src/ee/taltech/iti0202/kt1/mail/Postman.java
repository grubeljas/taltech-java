package ee.taltech.iti0202.kt1.mail;
import java.util.ArrayList;
import java.util.List;

public class Postman {

    String name;
    Integer age, limit;
    List<Letter> letters;
    /**
     * Create a postman with the name and the age.
     */
    public Postman(String name, Integer age) {
        this.age = age;
        this.letters = new ArrayList<>();
        this.name = name;
        if (age < 40) {
            this.limit = age + name.length();
        } else {
            this.limit = age - name.length();
        }
    }

    public String getName() {
        return name;
    }

    public List<Letter> getLetters() {
        return letters;
    }

    /**
     * Adds a letter to postman.
     * The letter can be added if the name of the postman and the name of the letter's address
     * start with the same symbol.
     * Also, each postman has a letter limit.
     * If the age of the postman is 40 or larger, then the limit of the letters is: age - name length
     * If the age of the postman is below 40, the limit is age + name length.
     * If the first letters do not match or the letter limit is reached, returns false.
     * Otherwise returns true and letter is added to postman.
     */
    public boolean addLetter(Letter letter) {
        if (limit == letters.size() || letter.address.charAt(0) != this.name.charAt(0)) {
            return false;
        }
        letters.add(letter);
        return true;
    }
}
