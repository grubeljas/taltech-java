package ee.taltech.iti0202.deliveryrobot.company;

import ee.taltech.iti0202.deliveryrobot.client.Client;
import ee.taltech.iti0202.deliveryrobot.delivery.DeliveryRobot;
import ee.taltech.iti0202.deliveryrobot.delivery.Product;
import ee.taltech.iti0202.deliveryrobot.delivery.Warehouse;
import ee.taltech.iti0202.deliveryrobot.exceptions.NoNameException;
import ee.taltech.iti0202.deliveryrobot.exceptions.NotPositiveNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CompanyTest {

    private Company company;
    private DeliveryRobot robot;

    /**
     * Company for these tests.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @BeforeEach
    public void simpleCompanyAndRobot() throws NotPositiveNumberException, NoNameException {
        this.company = new Company("primitive", 100,
                1, 1, 1);
        this.robot = new DeliveryRobot("First", 10);
    }

    /**
     * Test company with one robot. Check name and lists with the robot.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @Test
    public void simpleCompanyWithOneRobotTest() throws NotPositiveNumberException, NoNameException {
        assert company.addRobot(robot);

        assert company.getStatistics().getDeliveryRobotList().size() == 1;
    }

    /**
     * Second time returns false.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @Test
    public void cannotAddRobotTwiceTest() throws NotPositiveNumberException, NoNameException {
        assert company.addRobot(robot);
        assert !company.addRobot(robot);
    }

    /**
     * Is impossible to add robot from other company.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @Test
    public void cannotAddRobotFromOtherCompanyTest() throws NotPositiveNumberException, NoNameException {
        Company otherCompany = new Company("other", 1, 1, 1, 1);

        otherCompany.addRobot(robot);

        assert !company.addRobot(robot);
    }

    /**
     * Add broken robot too.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @Test
    public void addBrokenRobotTest() throws NotPositiveNumberException, NoNameException {
        robot.drive(10);
        assert company.addRobot(robot);

        assert company.getBrokenRobotList().size() == 1;
    }

    /**
     * Company owns a warehouse and buy cheap products.
     */
    @Test
    public void buyNormalAmountProductsTest() throws NotPositiveNumberException, NoNameException {
        Warehouse warehouse = new Warehouse("n", company);
        Product product = new Product("pen", 1, 10);

        assert company.addWarehouse(warehouse);

        company.buyProductWholeSale(warehouse, product, 3);

        assert company.getBudget() == 70;
        assert warehouse.getProductAmount().get(product) == 3;
    }

    /**
     * Checks delivery profit
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @Test
    public void makeOneDeliveryForProfitTest() throws NotPositiveNumberException, NoNameException {
        Warehouse warehouse = new Warehouse("first", company);
        Product product = new Product("pen", 1, 10);
        Client client = new Client("Tiit");

        company.addRobot(robot);
        company.addWarehouse(warehouse);
        company.stealProduct(warehouse, product, 3);

        assert company.getBudget() == 100;
        assert company.getAllProducts().get(product) == 3;

        client.makeOrder(company, List.of(product, product, product));

        assert company.getWaitingDeliveries().size() == 1;

        company.takeAnOrder();

        assert company.getBudget() == 130;
        assert company.getAllProducts().get(product) == 0;
        assert company.getWarehouseList().get(0).getProductAmount().get(product) == 0;
    }
}
