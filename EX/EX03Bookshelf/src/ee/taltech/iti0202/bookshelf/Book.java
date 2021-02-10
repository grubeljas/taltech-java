package ee.taltech.iti0202.bookshelf;

public class Book {

    private String title;
    private String author;
    private int year, price;
    static int id = -1;
    int number;
    private Person owner;

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
        if (getPrice() <= buyer.getMoney()) {
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
