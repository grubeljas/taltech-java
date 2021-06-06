package ee.taltech.iti0202.deliveryrobot.delivery;

import ee.taltech.iti0202.deliveryrobot.company.Company;
import ee.taltech.iti0202.deliveryrobot.exceptions.NotPositiveNumberException;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    private String name;
    private Company property;
    private Map<Product, Integer> productAmount = new HashMap<>();

    /**
     * Constructor with name.
     * @param name
     * @param property
     */
    public Warehouse(String name, Company property) {
        this.name = name;
        this.property = property;
    }

    /**
     * Place product in the warehouse
     * @param product
     * @return
     */
    public void loadPackage(Product product, Integer count) throws NotPositiveNumberException {
        if (count <= 0) {
            throw new NotPositiveNumberException("Amount must be more than 0.");
        }
        productAmount.put(product, productAmount.getOrDefault(product, 0) + count);
    }

    public Company getProperty() {
        return property;
    }

    public Map<Product, Integer> getProductAmount() {
        return productAmount;
    }
}
