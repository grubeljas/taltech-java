package ee.taltech.iti0202.deliveryrobot;

import ee.taltech.iti0202.deliveryrobot.delivery.DeliveryRobot;

import java.util.List;

public class Company {

    private String name;
    private List<DeliveryRobot> deliveryRobotList;

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<DeliveryRobot> getDeliveryRobotList() {
        return deliveryRobotList;
    }
}
