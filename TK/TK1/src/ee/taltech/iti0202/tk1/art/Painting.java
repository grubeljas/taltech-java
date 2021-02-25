package ee.taltech.iti0202.tk1.art;

public class Painting {

    String name, author;

    /**
     * Constructor if we know the author.
     *
     * @param name paintings name
     * @param author authors name
     */
    public Painting(String name, String author) {
        this.name = name;
        this.author = author;
    }

    /**
     * Constructor if we dont know the author.
     *
     * @param name name
     */
    public Painting(String name) {
        this.name = name;
        this.author = "";
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String toString() {
        if (getAuthor().equals("")) {
            return "This is a painting named " + getName() + " and made by an unknown artist.";
        } else {
            return "This is a painting named " + getName() + " and made by " + getAuthor() + ".";
        }
    }
}
