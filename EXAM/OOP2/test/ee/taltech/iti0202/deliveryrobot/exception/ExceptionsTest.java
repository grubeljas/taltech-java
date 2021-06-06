package ee.taltech.iti0202.deliveryrobot.exception;

import ee.taltech.iti0202.deliveryrobot.company.Company;
import ee.taltech.iti0202.deliveryrobot.delivery.Delivery;
import ee.taltech.iti0202.deliveryrobot.delivery.DeliveryRobot;
import ee.taltech.iti0202.deliveryrobot.delivery.Product;
import ee.taltech.iti0202.deliveryrobot.delivery.Warehouse;
import ee.taltech.iti0202.deliveryrobot.exceptions.NoNameException;
import ee.taltech.iti0202.deliveryrobot.exceptions.NotPositiveNumberException;
import org.junit.jupiter.api.Test;

public class ExceptionsTest {

    @Test
    public void robotWithEmptyNameTest() throws NotPositiveNumberException, NoNameException {
        try {
            DeliveryRobot robot = new DeliveryRobot("", 5);
            assert false;
        } catch (NoNameException e) { }
    }

    @Test
    public void robotWithNegativeLoadCapacityTest() throws NotPositiveNumberException, NoNameException {
        try {
            DeliveryRobot robot = new DeliveryRobot("not okay", -1);
            assert false;
        } catch (NotPositiveNumberException e) { }
    }

    @Test
    public void productWithNoNameTest() throws NotPositiveNumberException, NoNameException {
        try {
            Product product = new Product("", 1, 1);
            assert false;
        } catch (NoNameException e) { }
    }

    @Test
    public void productWithNegativeWeightTest() throws NotPositiveNumberException, NoNameException {
        try {
            Product product = new Product("test", -1, 1);
            assert false;
        } catch (NotPositiveNumberException e) { }
    }

    @Test
    public void productWithNegativePriceTest() throws NotPositiveNumberException, NoNameException {
        try {
            Product product = new Product("test", 1, -1);
            assert false;
        } catch (NotPositiveNumberException e) { }
    }

    @Test
    public void companyWithNoNameTest() throws NotPositiveNumberException {
        try {
            Company company = new Company("", 100, 2, 2, 3);
            assert false;
        } catch (NoNameException e) { }
    }

    @Test
    public void companiesWithNonPositiveBudgetTest() throws NotPositiveNumberException, NoNameException {
        try {
            Company company = new Company("negative budget",
                    -1, 2, 2, 3);
            assert false;
        } catch (NotPositiveNumberException e) { }
    }

    @Test
    public void companiesWithNonPositiveDeliveryCoffTest() throws NotPositiveNumberException, NoNameException {
        try {
            Company company = new Company("negative budget",
                    1, -2, 2, 3);
            assert false;
        } catch (NotPositiveNumberException e) { }
    }

    @Test
    public void companiesWithNonPositivePriceCoffTest() throws NotPositiveNumberException, NoNameException {
        try {
            Company company = new Company("negative budget",
                    1, 2, -2, 3);
            assert false;
        } catch (NotPositiveNumberException e) { }
    }

    @Test
    public void companiesWithNonPositiveRidePriceTest() throws NotPositiveNumberException, NoNameException {
        try {
            Company company = new Company("negative budget",
                    1, 2, 2, -3);
            assert false;
        } catch (NotPositiveNumberException e) { }
    }

    @Test
    public void buyTooMuchProductsTest() throws NotPositiveNumberException, NoNameException {
        Company company = new Company("poor comapny", 100, 2,
                1, 2);
        Warehouse warehouse = new Warehouse("warehouse", company);
        Product mixer = new Product("mixer", 2, 30);

        try {
            company.buyProductWholeSale(warehouse, mixer, 4);
            assert false;
        } catch (NotPositiveNumberException e) { }
    }

    @Test
    public void buyNegativeAmountProductsTest() throws NotPositiveNumberException, NoNameException {
        Company company = new Company("poor comapny", 100, 2,
                1, 2);
        Warehouse warehouse = new Warehouse("warehouse", company);
        Product mixer = new Product("mixer", 2, 30);

        try {
            company.buyProductWholeSale(warehouse, mixer, -1);
            assert false;
        } catch (NotPositiveNumberException e) { }
    }
}
