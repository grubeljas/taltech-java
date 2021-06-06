package ee.taltech.iti0202.deliveryrobot.exception;

import ee.taltech.iti0202.deliveryrobot.company.Company;
import ee.taltech.iti0202.deliveryrobot.delivery.DeliveryRobot;
import ee.taltech.iti0202.deliveryrobot.delivery.Product;
import ee.taltech.iti0202.deliveryrobot.delivery.Warehouse;
import ee.taltech.iti0202.deliveryrobot.exceptions.NoNameException;
import ee.taltech.iti0202.deliveryrobot.exceptions.NotPositiveNumberException;
import org.junit.jupiter.api.Test;

public class ExceptionsTest {

    /**
     * Empty name of robot.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @Test
    public void robotWithEmptyNameTest() throws NotPositiveNumberException, NoNameException {
        try {
            DeliveryRobot robot = new DeliveryRobot("", 5);
            assert false;
        } catch (NoNameException e) { }
    }

    /**
     * Negative load capacity.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @Test
    public void robotWithNegativeLoadCapacityTest() throws NotPositiveNumberException, NoNameException {
        try {
            DeliveryRobot robot = new DeliveryRobot("not okay", -1);
            assert false;
        } catch (NotPositiveNumberException e) { }
    }

    /**
     * No name product.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @Test
    public void productWithNoNameTest() throws NotPositiveNumberException, NoNameException {
        try {
            Product product = new Product("", 1, 1);
            assert false;
        } catch (NoNameException e) { }
    }

    /**
     * NEgative Weight.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @Test
    public void productWithNegativeWeightTest() throws NotPositiveNumberException, NoNameException {
        try {
            Product product = new Product("test", -1, 1);
            assert false;
        } catch (NotPositiveNumberException e) { }
    }

    /**
     * Negative price test.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @Test
    public void productWithNegativePriceTest() throws NotPositiveNumberException, NoNameException {
        try {
            Product product = new Product("test", 1, -1);
            assert false;
        } catch (NotPositiveNumberException e) { }
    }

    /**
     * No name company.
     * @throws NotPositiveNumberException
     */
    @Test
    public void companyWithNoNameTest() throws NotPositiveNumberException {
        try {
            Company company = new Company("", 100, 2, 2, 3);
            assert false;
        } catch (NoNameException e) { }
    }

    /**
     * Negative budget test.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @Test
    public void companiesWithNonPositiveBudgetTest() throws NotPositiveNumberException, NoNameException {
        try {
            Company company = new Company("negative budget",
                    -1, 2, 2, 3);
            assert false;
        } catch (NotPositiveNumberException e) { }
    }

    /**
     * Negative delivery coff test.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @Test
    public void companiesWithNonPositiveDeliveryCoffTest() throws NotPositiveNumberException, NoNameException {
        try {
            Company company = new Company("negative budget",
                    1, -2, 2, 3);
            assert false;
        } catch (NotPositiveNumberException e) { }
    }

    /**
     * Negative price coff test.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @Test
    public void companiesWithNonPositivePriceCoffTest() throws NotPositiveNumberException, NoNameException {
        try {
            Company company = new Company("negative budget",
                    1, 2, -2, 3);
            assert false;
        } catch (NotPositiveNumberException e) { }
    }

    /**
     * Negative ride price test.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @Test
    public void companiesWithNonPositiveRidePriceTest() throws NotPositiveNumberException, NoNameException {
        try {
            Company company = new Company("negative budget",
                    1, 2, 2, -3);
            assert false;
        } catch (NotPositiveNumberException e) { }
    }

    /**
     * Buy too much products test.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
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

    /**
     * Negative amount of products test.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
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
