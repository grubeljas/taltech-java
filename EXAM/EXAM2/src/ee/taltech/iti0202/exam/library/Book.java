package ee.taltech.iti0202.exam.library;
public class Book {

    private String title, isbn;

    /**
     * Creates a new book with the given title and ISBN.
     */
    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

}
