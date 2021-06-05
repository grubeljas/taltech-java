package ee.taltech.iti0202.deliveryrobot.company;

import ee.taltech.iti0202.deliveryrobot.delivery.Product;
import ee.taltech.iti0202.deliveryrobot.exceptions.NoNameException;
import ee.taltech.iti0202.deliveryrobot.exceptions.NotPositiveNumberException;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    private String name;
    private Company property;
    private Map<Product, Integer> productAmount = new HashMap<>();

    public Warehouse(String name, Company property) throws NoNameException {
        if (name.isEmpty()) {
            throw new NoNameException("Name of" + Warehouse.class + "cannot be empty.");
        }
        this.name = name;
        this.property = property;
    }

    /**
     * Place product in the warehouse
     * @param product
     * @return
     */
    protected void loadPackage(Product product, Integer count) throws NotPositiveNumberException {
        if (count <= 0) {
            throw new NotPositiveNumberException("Amount must be more than 0.");
        }
        productAmount.put(product, productAmount.getOrDefault(product, 0) + count);
    }
}
