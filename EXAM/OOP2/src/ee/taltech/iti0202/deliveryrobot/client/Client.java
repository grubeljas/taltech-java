package ee.taltech.iti0202.deliveryrobot.client;

import ee.taltech.iti0202.deliveryrobot.company.Company;
import ee.taltech.iti0202.deliveryrobot.company.Delivery;
import ee.taltech.iti0202.deliveryrobot.company.Product;

import java.util.LinkedList;
import java.util.List;

public class Client {

    private String name;
    private List<Product> productList = new LinkedList();
    private List<Delivery> deliveryHistory = new LinkedList<>();

    /**
     * Constructor of client with name. Budget of client doesn't matter in this system.
     * @param name
     */
    public Client(String name) {
        this.name = name;
    }

    public void makeOrder(Company company, List<Product> productList) {
        Delivery delivery = new Delivery(productList, this);
    }
}
