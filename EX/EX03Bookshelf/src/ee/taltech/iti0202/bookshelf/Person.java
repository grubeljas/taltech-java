package ee.taltech.iti0202.bookshelf;

//import java.util.TreeMap;

public class Person {

    private String name; private int money;

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
     * Buy book.
     *
     * @param book of deal.
     * @return bool.
     */
    public boolean buyBook(Book book) {
        if (book == null) {
            return false;
        }
        if (getMoney() >= book.getPrice() && book.getOwner() == null) {
            book.buy(this);
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
            book.buy(null);
            return true;
        }
        return false;
    }
}
