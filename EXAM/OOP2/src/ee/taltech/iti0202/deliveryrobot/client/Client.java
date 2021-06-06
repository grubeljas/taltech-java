package ee.taltech.iti0202.deliveryrobot.client;

import ee.taltech.iti0202.deliveryrobot.company.Company;
import ee.taltech.iti0202.deliveryrobot.delivery.Delivery;
import ee.taltech.iti0202.deliveryrobot.delivery.Product;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Client {

    private String name;
    private List<Product> recievedProducts = new LinkedList();
    private List<Delivery> deliveryHistory = new LinkedList<>();
    private int distance;

    /**
     * Constructor of client with name and distance for delivery in hours.
     * Budget of client doesn't matter in this system.
     * @param name
     */
    public Client(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }

    public List<Product> getRecievedProducts() {
        return recievedProducts;
    }

    /**
     * Make an order any amount of any product.
     * @param company
     * @param productList
     */
    public void makeOrder(Company company, List<Product> productList) {
        HashMap<Product, Integer> productAmount = new HashMap<>();
        Delivery delivery = new Delivery(productList, this);
        deliveryHistory.add(delivery);
        company.getDelivery(delivery);
    }
}
