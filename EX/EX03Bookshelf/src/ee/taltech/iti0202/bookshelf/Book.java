package ee.taltech.iti0202.bookshelf;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Book {

    private String title;
    private String author;
    private int year, price;
    static int id = -1;
    int number;
    private Person owner = null;
    static List<Book> data = new ArrayList<>();
    static String previousAuthor;
    static int prevYear;

    /**
     * Make id.
     *
     * @return number of book
     */
    public static int getAndIncrementNextId() {
        return ++id;
    }

    /**
     * Constructor.
     *
     * @param title of book
     * @param author who wrote this book
     * @param yearOfPublishing when book was written
     * @param price why so expensive
     */
    public Book(String title, String author, int yearOfPublishing, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.year = yearOfPublishing;
        this.number = getAndIncrementNextId();
    }

    /**
     * Create book.
     *
     * @param title of book
     * @param author yeah
     * @param yearOfPublishing amazing
     * @param price omg
     * @return masterpiece
     */
    public static Book of(String title, String author, int yearOfPublishing, int price) {
        for (Book el: data) {
            if (el.title.equals(title) && el.author.equals(author) && el.year == yearOfPublishing) {
                return el;
            }
        }
        previousAuthor = author;
        prevYear = yearOfPublishing;
        Book book = new Book(title, author, yearOfPublishing, price);
        data.add(book);
        return book;
    }

    /**
     * Create book with same author like aa previous one.
     *
     * @param title new
     * @param price chipper i hope
     * @return boook
     */
    public static Book of(String title, int price) {
        if (data.isEmpty()) {
            return null;
        } else {
            return of(title, previousAuthor, prevYear, price);
        }
    }

    /**
     * All books of that person.
     *
     * @param owner not me
     * @return collection.
     */
    public static List<Book> getBooksByOwner(Person owner) {
        return owner.getBooks();
    }

    /**
     * Remove book from list.
     *
     * @param book of book.
     * @return true if book exist.
     */
    public static boolean removeBook(Book book) {
        if (book == null) {
            return false;
        }
        if (data.contains(book)) {
            book.buy(null);
            data.remove(book);
            return true;
        }
        return false;
    }

    /**
     * Get all harry potter books.
     *
     * @param author of .
     * @return alllll.
     */
    public static List<Book> getBooksByAuthor(String author) {
        List<Book> collection = new LinkedList<>();
        for (Book book: data) {
            if (book.getAuthor().length() == author.length()) {
                if (book.getAuthor().toLowerCase().equals(author.toLowerCase())) {
                    collection.add(book);
                }
            }
        }
        return collection;
    }

    /**
     * Get title.
     *
     * @return title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Author.
     *
     * @return author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Get year.
     *
     * @return year.
     */
    public int getYearOfPublishing() {
        return year;
    }

    /**
     * Get owner.
     *
     * @return owner.
     */
    public Person getOwner() {
        return owner;
    }

    /**
     * Get price.
     *
     * @return price.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Get id.
     *
     * @return id.
     */
    public int getId() {
        return number;
    }

    /**
     * Buy book.
     *
     * @param buyer or null.
     * @return boolean success of deal.
     */
    public boolean buy(Person buyer) {
        if (buyer == null) {
            if (owner != null) {
                owner.setMoney(owner.getMoney() + getPrice());
            }
            owner = null;
            return true;
        }
        if (getPrice() <= buyer.getMoney() && owner != buyer) {
            if (owner != null) {
                owner.setMoney(owner.getMoney() + getPrice());
            }
            owner = buyer;
            owner.setMoney(owner.getMoney() - getPrice());
            return true;
        }
        return false;
    }

}
