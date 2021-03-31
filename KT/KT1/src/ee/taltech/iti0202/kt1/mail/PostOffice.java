package ee.taltech.iti0202.kt1.mail;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostOffice {

    String location;
    List<Letter> letters;
    List<Postman> postmen;
    /**
     * Create a post office with the location.
     */
    public PostOffice(String location) {
        this.location = location;
        this.letters = new ArrayList<>();
        this.postmen = new ArrayList<>();
    }

    /**
     * Adds a letter to the post office.
     * Letter is added only if the letter's destination city matches the location of the office.
     */
    public void addLetter(Letter letter) {
        if (location.equals(letter.destination)) {
            getAllLetters().add(letter);
        }
    }

    /**
     * Adds a postman to the office.
     * If there is a postman with the same first letter already in the office,
     * then this postman is not added.
     * Otherwise postman is added to the office.
     */
    public void addPostman(Postman postman) {
        Optional<Postman> impostor = getPostmen().stream()
                .filter(postman1 -> postman1.name.charAt(0) == postman.name.charAt(0))
                .findFirst();
        if (impostor.isEmpty()) {
            postmen.add(postman);
        }
    }

    /**
     * Returns all the letter in the post office.
     */
    public List<Letter> getAllLetters() {
        return letters;
    }

    /**
     * Returns all the postmen in the office.
     */
    public List<Postman> getPostmen() {
        return postmen;
    }

    /**
     * Divide letter in the office to postmen.
     * The division algorithm is as follows:
     * Each letter is assigned to each postman.
     * If the postman can take this letter (addLetter method), then this letter
     * is added to the postman and removed from the office.
     * If no postman can take the letter, then this letter remains in the office.
     */
    public void divideLetters() {
        for (Letter letter: List.copyOf(letters)) {
            for (Postman postman: postmen) {
                if (postman.addLetter(letter)) {
                    letters.remove(letter);
                }
            }
        }
    }

    public static void main(String[] args) {
        PostOffice postOffice = new PostOffice("Tallinn");

        Postman postman = new Postman("Artin", 1);

        postOffice.addPostman(postman);
        System.out.println(postman.limit);

        postOffice.addLetter(new Letter("Toomas", "Tartu", "Rahu tn"));
        postOffice.addLetter(new Letter("Erki", "Tallinn", "Aänni tee"));

        postOffice.divideLetters();

        System.out.println(postman.getLetters());   // [City: Tallinn, Address: Männi tee, Recipient: Erki]
    }
}
