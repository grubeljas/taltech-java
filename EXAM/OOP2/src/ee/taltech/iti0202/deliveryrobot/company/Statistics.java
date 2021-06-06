package ee.taltech.iti0202.deliveryrobot.company;

import ee.taltech.iti0202.deliveryrobot.delivery.Delivery;
import ee.taltech.iti0202.deliveryrobot.delivery.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Statistics {

    private Company company;
    private HashMap<Product, Integer> productPopularity = new HashMap<>();
    private List<Delivery> currentDeliveries;

    /**
     * Constructor shows company of the statistics.
     * @param company
     */
    public Statistics(Company company) {
        this.company = company;
    }

    public HashMap<Product, Integer> getProductPopularity() {
        return productPopularity;
    }

    public List<Delivery> getCurrentDeliveries() {
        return currentDeliveries;
    }

    /**
     * Add delivery to company statistics.
     * @param delivery
     */
    public void addDelivery(Delivery delivery) {
        currentDeliveries.add(delivery);
        countProductOfDelivery(delivery);
    }

    /**
     * Count products of
     * @param delivery
     */
    public void countProductOfDelivery(Delivery delivery) {
        for (Product product: delivery.getProductList()) {
            productPopularity.put(product, productPopularity.getOrDefault(product, 0) + 1);
        }
    }
}
