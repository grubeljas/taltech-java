package ee.taltech.iti0202.computerstore.store;
import ee.taltech.iti0202.computerstore.Customer;
import ee.taltech.iti0202.computerstore.computer.*;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.database.Database;
import ee.taltech.iti0202.computerstore.exceptions.CannotBuildComputerException;
import ee.taltech.iti0202.computerstore.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Store {

    private String name;
    private double balance, profitMargin;
    private Database database = Database.getInstance();
    ComputerFactory computerFactory;

    /**
     * Constructor.
     *
     * @param name
     * @param balance
     * @param profitMargin
     * @throws IllegalArgumentException
     */
    public Store(String name, double balance, double profitMargin) throws IllegalArgumentException {
        this.name = name;
        this.balance = balance;
        this.computerFactory = new ComputerFactory(this);
        if (profitMargin < 1) {
            throw new IllegalArgumentException();
        } else this.profitMargin = profitMargin;
    }

    /**
     * Buy component if enough money.
     * @param id
     * @param customer
     * @return
     * @throws OutOfStockException
     * @throws ProductNotFoundException
     * @throws NotEnoughMoneyException
     */
    public Component purchaseComponent(int id, Customer customer) throws OutOfStockException,
            ProductNotFoundException,
            NotEnoughMoneyException {
        Component component = database.getComponents().get(id);
        double price = component.getPrice() * profitMargin;
        if (customer.getBalance() < price) {
            throw new NotEnoughMoneyException();
        } else {
            balance += price;
            database.decreaseComponentStock(id, 1);
            customer.setBalance(customer.getBalance() - price);
            customer.getComponents().add(component);
        }
        return component;
    }

    public Computer purchaseComputer(Customer customer, int budget, Preferences preferences, ComputerType type) throws CannotBuildComputerException {
        Computer computer = computerFactory.buildComputer(budget, preferences, type);
        return computer;
    }

    /**
     * Get components.
     * @return
     */
    public List<Component> getAvailableComponents() {
        List<Component> available = new ArrayList<>();
        List<Component> components = database.getComponents().values().stream()
                .filter(component -> component.getAmount() > 0)
                .collect(Collectors.toList());
        for (Component component: components) {
            if (component.getAmount() == 10) {
                available.add(component);
            } else {
                for (int i = 0; i < component.getAmount(); ++i) {
                    available.add(component);
                }
            }
        }
        return components;
    }

    /**
     * Sort by amount.
     * @return
     */
    public List<Component> getComponentsSortedByAmount() {
        List<Component> components = database.getComponents().values().stream()
                .sorted(Comparator.comparing(component -> component.getAmount()))
                .collect(Collectors.toList());
        return components;
    }

    /**
     * Get by name.
     * @return
     */
    public List<Component> getComponentsSortedByName() {
        List<Component> components = database.getComponents().values().stream()
                .sorted(Comparator.comparing(component -> component.getName()))
                .collect(Collectors.toList());
        return components;
    }

    /**
     * Get by price.
     * @return
     */
    public List<Component> getComponentsSortedByPrice() {
        List<Component> components = database.getComponents().values().stream()
                .sorted(Comparator.comparing(component -> component.getPrice()))
                .collect(Collectors.toList());
        return components;
    }

    /**
     * Get by price for EX12.
     * @return
     */
    public List<Component> getComponentsSortedByPrice(List<Component> componentsList) {
        List<Component> components = componentsList.stream()
                .sorted(Comparator.comparing(component -> component.getPrice()))
                .collect(Collectors.toList());
        return components;
    }

    /**
     * Filter by type.
     * @param type
     * @return
     */
    public List<Component> filterByType(Component.Type type) {
        List<Component> components = database.getComponents().values().stream()
                .filter(component -> component.getType().equals(type))
                .collect(Collectors.toList());
        return components;
    }

    /**
     * Get value.
     * @return
     */
    public double getInventoryValue() {
        Double sum = 0.0;
        for (Component component: getDatabase().getComponents().values()) {
            if (component.getAmount() > 0) {
                sum += component.getPrice() * component.getAmount();
            }
        }
        return (sum + balance) * profitMargin;
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

    public double getProfitMargin() {
        return profitMargin;
    }

    public void setProfitMargin(double profitMargin) {
        if (profitMargin < 1) {
            throw new IllegalArgumentException();
        }
        this.profitMargin = profitMargin;
    }

    public Database getDatabase() {
        return database;
    }
}
