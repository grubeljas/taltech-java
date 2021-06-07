package ee.taltech.iti0202.deliveryrobot.delivery;

import ee.taltech.iti0202.deliveryrobot.client.Client;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Delivery implements Comparable<Delivery> {

    private List<Product> productList = new LinkedList<>();
    private Map<Product, Integer> countProduct = new HashMap<>();
    private Client client;
    private int totalPrice;

    /**
     * Constructor with hashmap of products and client.
     * @param productList
     * @param client
     */
    public Delivery(List<Product> productList, Client client) {
        this.client = client;
        this.productList = productList;
        this.totalPrice = findTotalPrice();
        getCountProduct();
    }

    /**
     * Find price of all products.
     * @return
     */
    public int findTotalPrice() {
        return getProductList().stream().mapToInt(Product::getPrice).sum();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Client getClient() {
        return client;
    }

    public Map<Product, Integer> getCountProduct() {
        return countProduct;
    }

    @Override
    public int compareTo(Delivery o) {
        return -1;
    }
}
