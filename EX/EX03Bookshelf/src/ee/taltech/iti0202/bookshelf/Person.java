package ee.taltech.iti0202.bookshelf;

import java.util.LinkedList;
import java.util.List;

public class Person {

    private String name; private int money;
    private List<Book> collection = new LinkedList<>();
    static List<Author> authors = new LinkedList<>();

    /**
     * Constructor.
     *
     * @param name of human.
     * @param money right now.
     */
    public Person(String name, int money) {
        this.name = name;
        this.money = money;
    }

    /**
     * Get money.
     *
     * @return amount of money.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Set money.
     *
     * @param money new amount.
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * Get name.
     *
     * @return name of person.
     */
    public String getName() {
        return name;
    }

    /**
     * Get all.
     *
     * @return collects.
     */
    public List<Book> getBooks() {
        return collection;
    }

    /**
     * Buy book.
     *
     * @param book of deal.
     * @return bool.
     */
    public boolean buyBook(Book book) {
        if (book == null) {
            return false;
        }
        if (getMoney() >= book.getPrice()) {
            setMoney(getMoney() - book.getPrice());
            collection.add(book);
            book.owner = this;
            return true;
        }
        return false;
    }

    /**
     * Sell book.
     *
     * @param book of deal and sell.
     * @return bool of success
     */
    public boolean sellBook(Book book) {
        if (book == null) {
            return false;
        }
        if (book.getOwner() == this) {
            setMoney(getMoney() + book.getPrice());
            collection.remove(book);
            book.owner = null;
            return true;
        }
        return false;
    }
}
