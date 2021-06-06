package ee.taltech.iti0202.deliveryrobot.company;

import ee.taltech.iti0202.deliveryrobot.delivery.DeliveryRobot;
import ee.taltech.iti0202.deliveryrobot.exceptions.NoNameException;
import ee.taltech.iti0202.deliveryrobot.exceptions.NotPositiveNumberException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompanyTest {

    @Test
    public void simpleCompanyWithOneRobotTest() throws NotPositiveNumberException, NoNameException {
        Company company = new Company("primitive", 100,
                1, 1, 1);
        DeliveryRobot robot = new DeliveryRobot("First", 10);

        company.addRobot(robot);

        assert company.getStatistics().getDeliveryRobotList().size() == 1;
        assert company.getName().equals("primitive");
        assert company.getOneRidePrice() == 1;
    }


}
