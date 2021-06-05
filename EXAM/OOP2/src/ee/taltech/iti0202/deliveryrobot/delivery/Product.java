package ee.taltech.iti0202.deliveryrobot.delivery;

import ee.taltech.iti0202.deliveryrobot.exceptions.NoNameException;
import ee.taltech.iti0202.deliveryrobot.exceptions.NotPositiveNumberException;

public class Product {

    private String name;
    private int weight, price;

    /**
     * Constructor with positive weight and name.
     * @param name
     * @param weight
     */
    public Product(String name, int weight, int price) throws NoNameException, NotPositiveNumberException {
        if (name.isEmpty()) {
            throw new NoNameException("Name of" + DeliveryRobot.class + "cannot be empty.");
        }
        if (weight <= 0) {
            throw new NotPositiveNumberException("Weight of product mustn't be 0 or less.");
        }
        if (price <= 0) {
            throw new NotPositiveNumberException("Price mustn't be 0 or less.");
        }
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }
}
