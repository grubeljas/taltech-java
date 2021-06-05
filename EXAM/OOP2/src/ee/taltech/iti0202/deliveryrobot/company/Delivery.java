package ee.taltech.iti0202.deliveryrobot.company;

import ee.taltech.iti0202.deliveryrobot.client.Client;

import java.util.List;

public class Delivery {

    private List<Product> deliveryProducts;
    private Client client;

    public Delivery(List<Product> deliveryProducts, Client client) {
        this.client = client;
        this.deliveryProducts = deliveryProducts;
    }
}
