package ee.taltech.iti0202.bookshelf;

import java.util.TreeMap;

public class Person {

    private String name; private int money;

    public Person(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

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
