package ee.taltech.iti0202.deliveryrobot.company;

import ee.taltech.iti0202.deliveryrobot.delivery.DeliveryRobot;
import ee.taltech.iti0202.deliveryrobot.delivery.Product;
import ee.taltech.iti0202.deliveryrobot.delivery.Warehouse;
import ee.taltech.iti0202.deliveryrobot.exceptions.NoNameException;
import ee.taltech.iti0202.deliveryrobot.exceptions.NotPositiveNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompanyTest {

    private Company company;

    /**
     * Company for these tests.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @BeforeEach
    public void simpleCompany() throws NotPositiveNumberException, NoNameException {
        this.company = new Company("primitive", 100,
                1, 1, 1);
    }

    /**
     * Test company with one robot. Check name and lists with the robot.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @Test
    public void simpleCompanyWithOneRobotTest() throws NotPositiveNumberException, NoNameException {
        DeliveryRobot robot = new DeliveryRobot("First", 10);

        assert company.addRobot(robot);

        assert company.getStatistics().getDeliveryRobotList().size() == 1;
    }

    @Test
    public void addBrokenRobotTest() throws NotPositiveNumberException, NoNameException {
        DeliveryRobot robot = new DeliveryRobot("First", 10);

        robot.drive(10);
        assert company.addRobot(robot);

        assert company.getBrokenRobotList().size() == 1;
    }

    /**
     * Company owns a warehouse and buy cheap products.
      */
    public void buyNormalAmountProductsTest() throws NotPositiveNumberException, NoNameException {
        Warehouse warehouse = new Warehouse("n", company);
        Product product = new Product("pen", 1, 10);

        assert company.addWarehouse(warehouse);

        company.buyProductWholeSale(warehouse, product, 3);

        assert company.getBudget() == 70;
        assert warehouse.getProductAmount().get(product) == 3;
    }
}
