package ee.taltech.iti0202.deliveryrobot.delivery;

import ee.taltech.iti0202.deliveryrobot.client.Client;
import ee.taltech.iti0202.deliveryrobot.company.Company;
import ee.taltech.iti0202.deliveryrobot.exceptions.NoNameException;
import ee.taltech.iti0202.deliveryrobot.exceptions.NotPositiveNumberException;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

public class DeliveryRobotTest {

    /**
     * Simple id checks.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @Test
    public void simpleRobotTest() throws NotPositiveNumberException, NoNameException {
        DeliveryRobot.resetIDCounter();
        DeliveryRobot robot1 = new DeliveryRobot("PiBot", 10);
        DeliveryRobot robot2 = new DeliveryRobot("2", 1);

        assert robot1.getId() == 1;
        assert robot2.getId() == 2;
        assert robot1.getName().equals("PiBot");
    }

    /**
     * During the delivery status changes.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @Test
    public void statusChangingTest() throws NotPositiveNumberException, NoNameException {
        Company company = new Company("primitive", 100,
                1, 1, 1);
        DeliveryRobot robot = new DeliveryRobot("First", 10);
        Warehouse warehouse = new Warehouse("warehouse", company);
        Product product = new Product("kali", 2, 12);
        Client client = new Client("Toot");

        assert company.addRobot(robot);
        assert company.addWarehouse(warehouse);
        company.stealProduct(warehouse, product, 2);

        assert robot.getStatus().equals(DeliveryRobot.StatusOfRobot.WAITING);
        assert company.getWaitingRobotList().contains(robot);
        assert !company.getActiveRobotList().contains(robot);

        client.makeOrder(company, List.of(product, product));
        company.takeAnOrder();

        assert robot.getStatus().equals(DeliveryRobot.StatusOfRobot.DELIVERY);
        assert company.getActiveRobotList().contains(robot);

        company.returnRobots();

        assert robot.getStatus().equals(DeliveryRobot.StatusOfRobot.WAITING);
        assert company.getWaitingRobotList().contains(robot);
    }

    /**
     * Check if company can see broken robots.
     */
    @Test
    public void brokenRobotTest() throws NotPositiveNumberException, NoNameException {
        Company company = new Company("primitive", 100,
                1, 1, 1);
        DeliveryRobot robot1 = new DeliveryRobot("PiBot", 10);
        Warehouse warehouse = new Warehouse("warehouse", company);
        Product product = new Product("kali", 2, 12);
        Client client = new Client("Toot");

        company.addRobot(robot1);
        company.addWarehouse(warehouse);
        company.stealProduct(warehouse, product, 2);

        client.makeOrder(company, List.of(product, product));
        company.takeAnOrder();
        robot1.drive(10);

        assert robot1.getStatus().equals(DeliveryRobot.StatusOfRobot.BROKEN);
        assert company.getActiveRobotList().contains(robot1);

        company.returnRobots();

        assert company.getBrokenRobotList().contains(robot1);
    }
}
