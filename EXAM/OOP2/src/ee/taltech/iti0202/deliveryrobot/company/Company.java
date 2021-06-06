package ee.taltech.iti0202.deliveryrobot.company;

import ee.taltech.iti0202.deliveryrobot.World;
import ee.taltech.iti0202.deliveryrobot.delivery.Delivery;
import ee.taltech.iti0202.deliveryrobot.delivery.DeliveryRobot;
import ee.taltech.iti0202.deliveryrobot.delivery.Product;
import ee.taltech.iti0202.deliveryrobot.delivery.Warehouse;
import ee.taltech.iti0202.deliveryrobot.exceptions.NoNameException;
import ee.taltech.iti0202.deliveryrobot.exceptions.NotPositiveNumberException;

import java.util.List;

public class Company {

    private String name;
    private int budget;
    private Statistics statistics;
    private List<DeliveryRobot> deliveryRobotList;
    private List<DeliveryRobot> waitingRobotList;
    private List<DeliveryRobot> activeRobotList;
    private List<DeliveryRobot> brokenRobotList;

    /**
     * Constructor with name and budget.
     * @param name
     */
    public Company(String name, int budget) throws  NoNameException, NotPositiveNumberException {
        if (name.isEmpty()) {
            throw new NoNameException("Name of" + Company.class + "cannot be empty.");
        }
        if (budget <= 0) {
            throw new NotPositiveNumberException("Budget cannot be less than 1.");
        }
        this.budget = budget;
        this.name = name;
        this.statistics = new Statistics(this);
        World.getInstance().addCompany(this);
    }

    /**
     * Buy amount of product and load in warehouse. Decrease budget.
     * @param warehouse
     * @param product
     * @param amount
     * @throws NotPositiveNumberException
     */
    public void buyProductWholeSale(Warehouse warehouse, Product product, int amount)
            throws NotPositiveNumberException {
        if (product.getPrice() * amount > budget) {
            throw new NotPositiveNumberException("Price of " + product.getName() + " is too high.");
        }
        stealProduct(warehouse, product, amount);
        budget -= product.getPrice() * amount;
    }

    /**
     * Get products for free.
     * @param warehouse
     * @param product
     * @param amount
     * @throws NotPositiveNumberException
     */
    public void stealProduct(Warehouse warehouse, Product product, int amount) throws NotPositiveNumberException {
        warehouse.loadPackage(product, amount);
    }

    public String getName() {
        return name;
    }

    public List<DeliveryRobot> getDeliveryRobotList() {
        return deliveryRobotList;
    }

    /**
     * Add robot to the company if robot is not already in it.
     * @param robot
     * @return
     */
    public boolean addRobot(DeliveryRobot robot) {
        if (deliveryRobotList.contains(robot)) {
            return false;
        }
        deliveryRobotList.add(robot);
        robot.setBelongsTo(this);
        return true;
    }

    /**
     * Add delivery to
     * @param delivery
     * @return
     */
    public boolean getDelivery(Delivery delivery) {
        if (statistics.getCurrentDeliveries().contains(delivery)) {
            return false;
        }
        statistics.addDelivery(delivery);
        return true;
    }

    /**
     * Next day.
     */
    public void nextDay() {

    }
}
