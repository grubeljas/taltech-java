package ee.taltech.iti0202.bookshelf;

public class Book {

    private String title;
    private String author;
    private int year, price;
    static int id = -1;
    int number;
    private Person owner;

    public static int getAndIncrementNextId() {
        return ++id;
    }

    public Book(String title, String author, int yearOfPublishing, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.year = yearOfPublishing;
        this.number = getAndIncrementNextId();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearOfPublishing() {
        return year;
    }

    public Person getOwner() {
        return owner;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return number;
    }

    public boolean buy(Person buyer) {
        if (buyer == null) {
            owner.setMoney(owner.getMoney() + getPrice());
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
        return (getPrice() <= buyer.getMoney());
    }

}
