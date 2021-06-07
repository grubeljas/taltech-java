package ee.taltech.iti0202.deliveryrobot.strategy;

import ee.taltech.iti0202.deliveryrobot.client.Client;
import ee.taltech.iti0202.deliveryrobot.company.Company;
import ee.taltech.iti0202.deliveryrobot.delivery.DeliveryRobot;
import ee.taltech.iti0202.deliveryrobot.delivery.Product;
import ee.taltech.iti0202.deliveryrobot.delivery.Warehouse;
import ee.taltech.iti0202.deliveryrobot.exceptions.NoNameException;
import ee.taltech.iti0202.deliveryrobot.exceptions.NotPositiveNumberException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StrategyTest {

    private Company company;
    private DeliveryRobot robot;
    private Warehouse warehouse;
    private Client client;
    private Product lamp, calc;

    /**
     * Company for these tests.
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @BeforeEach
    public void simpleCompanyAndRobot() throws NotPositiveNumberException, NoNameException {
        this.company = new Company("primitive", 0,
                5, 1, 1);
        this.robot = new DeliveryRobot("First", 10);
        this.warehouse = new Warehouse("1", company);
        this.client = new Client("Mihkel");
        this.lamp = new Product("lamp", 9, 9);
        this.calc = new Product("calc", 5, 5);

        company.addRobot(robot);
        company.addWarehouse(warehouse);
        company.stealProduct(warehouse, calc, 10);
        company.stealProduct(warehouse, lamp, 10);
    }

    /**
     * Usual strategy, order of products is not changed.
     */
    @Test
    public void usualStrategyTest() throws NotPositiveNumberException, NoNameException {
        client.makeOrder(company, List.of(calc, lamp, calc));
        company.takeAnOrder();

        assert company.getBudget() == 31; //(5 | 9 | 5) * 1 + 3 * (5 - 1), 3 rides
        assert robot.getUsage() == 3;
    }

    /**
     * Light strategy, order of products is changed.
     */
    @Test
    public void lightFirstStrategyTest() throws NotPositiveNumberException, NoNameException {
        company.setRobotStrategy(new LightFirstStrategy());
        client.makeOrder(company, List.of(calc, lamp, calc));
        company.takeAnOrder();

        assert company.getBudget() == 27; //(5 + 5 | 9) * 1 + 2 * (5 - 1) , 2 rides
        assert robot.getUsage() == 2;
    }

    /**
     * Popular
     * @throws NotPositiveNumberException
     * @throws NoNameException
     */
    @Test
    public void popularFirstStrategyTest() throws NotPositiveNumberException, NoNameException {
        company.setRobotStrategy(new PopularFirstStrategy(company));
        Product pen = new Product("pastakas", 2, 2);

        company.stealProduct(warehouse, pen, 5);
        company.getStatistics().getProductPopularity().put(lamp, 10);
        company.getStatistics().getProductPopularity().put(pen, 50);
        company.getStatistics().getProductPopularity().put(calc, 100);

        List listOfOrder = List.of(calc, pen, lamp, pen);
        client.makeOrder(company, listOfOrder);
        company.takeAnOrder();
        ;
        assert company.getBudget() == 26; //(5 + 2 + 2 | 9) * 1 + 2 * (5 - 1)
        assert robot.getUsage() == 2;
    }

    /**
     * Test delivery which gives more money.
     */
    @Test
    public void ProfitableDeliveryFirstTest() {
        company.setCompanyStrategy(new ProfitableDeliveryFirstStrategy(company));

        client.makeOrder(company, List.of(calc));
        client.makeOrder(company, List.of(calc, calc, lamp, lamp));
        company.takeAnOrder();

        assert company.getBudget() == 40; //(5 + 5 | 9 | 9) * 1 + 3 * (5 - 1)
        assert robot.getUsage() == 3;
        assert company.getStatistics().getDeliveryHistory().get(0).getProductList().size() == 4;
    }

}
