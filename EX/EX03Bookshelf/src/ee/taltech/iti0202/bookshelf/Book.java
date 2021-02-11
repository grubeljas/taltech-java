package ee.taltech.iti0202.bookshelf;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Book {

    private final String title;
    private final String author;
    private final int year, price;
    static int id = -1;
    int number;
    Person owner = null;
    static List<Book> data = new ArrayList<>();
    static String previousAuthor;
    static HashMap<String, Author> authors = new HashMap<>();
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
        Author newauthor = Author.of(author.toUpperCase());
        newauthor.addBook(book);
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
            return Book.of(title, previousAuthor, prevYear, price);
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
            authors.get(book.getAuthor().toUpperCase()).removeBook(book);
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
        List<Book> books = new LinkedList<>();
        if (author == null) {
            return books;
        } else if (!authors.containsKey(author.toUpperCase())) {
            return books;
        }
        return authors.get(author.toUpperCase()).getData();
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
                owner.sellBook(this);
            }
            return true;
        }
        if (getPrice() <= buyer.getMoney() && owner != buyer) {
            if (owner != null) {
                owner.sellBook(this);
            }
            buyer.buyBook(this);
            return true;
        }
        return false;
    }

}

class Author {

    private String author;
    public List<Book> data;
    static List<Author> all = new LinkedList<>();

    /**
     * Constructor.
     *
     * @param author who wrote this book
     */
    Author(String author) {
        this.author = author;
        this.data = new LinkedList<>();
    }

    static public Author of(String name) {
        for (Author el: all) {
            if (el.author.equals(name)) {
                return el;
            }
        }
        Author newby = new Author(name);
        return newby;
    }

    /**
     * Add book.
     *
     * @param book of his
     * @return true of false
     */
    public boolean addBook(Book book) {
        if (book != null) {
            data.add(book);
            return true;
        }
        return false;
    }

    /**
     * Remove book.
     *
     * @param book of book
     * @return f or t
     */
    public boolean removeBook(Book book) {
        if (book != null) {
            if (data.contains(book)) {
                data.remove(book);
                return true;
            }
        }
        return false;
    }

    /**
     * Get data.
     *
     * @return list of books.
     */
    public List<Book> getData() {
        return data;
    }
}
