package ee.taltech.iti0202.computerstore.store;
import ee.taltech.iti0202.computerstore.Customer;
import ee.taltech.iti0202.computerstore.components.Component;
import ee.taltech.iti0202.computerstore.database.Database;
import ee.taltech.iti0202.computerstore.exceptions.NotEnoughMoneyException;
import ee.taltech.iti0202.computerstore.exceptions.OutOfStockException;
import ee.taltech.iti0202.computerstore.exceptions.ProductNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Store {

    private String name;
    private double balance, profitMargin;
    private Database database = Database.getInstance();

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
        if (profitMargin < 1) {
            throw new IllegalArgumentException();
        } else this.profitMargin = profitMargin;
    }

    public Component purchaseComponent(int id, Customer customer) throws OutOfStockException,
            ProductNotFoundException,
            NotEnoughMoneyException {
        Component component = database.getComponents().get(id);
        double price = component.getPrice() * profitMargin;
        if (customer.getBalance() < price) {
            throw new NotEnoughMoneyException();
        } else {
            component.setAmount(component.getAmount() - 1);
            if (component.getAmount() == 0) {
                database.deleteComponent(id);
            }
            balance += price;
            customer.getComponents().add(component);
        }
        return component;
    }

    public List<Component> getAvailableComponents() {
        return (List<Component>) database.getComponents().values();
    }

    public List<Component> getComponentsSortedByAmount() {
        List<Component> components = database.getComponents().values().stream()
                .sorted(Comparator.comparing(component -> component.getAmount()))
                .collect(Collectors.toList());
        return components;
    }

    public List<Component> getComponentsSortedByName() {
        List<Component> components = database.getComponents().values().stream()
                .sorted(Comparator.comparing(component -> component.getName()))
                .collect(Collectors.toList());
        return components;
    }

    public List<Component> getComponentsSortedByPrice() {
        List<Component> components = database.getComponents().values().stream()
                .sorted(Comparator.comparing(component -> component.getPrice()))
                .collect(Collectors.toList());
        return components;
    }

    public List<Component> filterByType(Component.Type type) {
        List<Component> components = database.getComponents().values().stream()
                .filter(component -> component.getType().equals(type))
                .collect(Collectors.toList());
        return components;
    }

    public double getInventoryValue() {
        Double sum = database.getComponents().values().stream()
                .collect(Collectors.summingDouble(Component::getPrice));
        return sum * profitMargin + balance;
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
}
