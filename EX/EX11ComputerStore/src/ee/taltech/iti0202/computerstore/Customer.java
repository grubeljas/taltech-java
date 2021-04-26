package ee.taltech.iti0202.computerstore;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.computer.Computer;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private double balance;
    private final List<Component> components = new ArrayList<>();
    private List<Computer> computers = new ArrayList<>();

    /**
     * Constructor.
     * @param name
     * @param balance
     */
    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Component> getComponents() {
        return components;
    }

    public List<Computer> getComputers() {
        return computers;
    }
}
