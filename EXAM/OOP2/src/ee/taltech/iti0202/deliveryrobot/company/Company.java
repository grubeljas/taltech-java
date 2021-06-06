package ee.taltech.iti0202.deliveryrobot.company;

import ee.taltech.iti0202.deliveryrobot.World;
import ee.taltech.iti0202.deliveryrobot.delivery.Delivery;
import ee.taltech.iti0202.deliveryrobot.delivery.DeliveryRobot;
import ee.taltech.iti0202.deliveryrobot.delivery.Product;
import ee.taltech.iti0202.deliveryrobot.delivery.Warehouse;
import ee.taltech.iti0202.deliveryrobot.exceptions.NoNameException;
import ee.taltech.iti0202.deliveryrobot.exceptions.NotPositiveNumberException;

import java.util.List;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Optional;

public class Company {

    private String name;
    private int budget, deliveryCoefficient, productPriceCoefficient;
    private Statistics statistics;
    private List<DeliveryRobot> waitingRobotList;
    private List<DeliveryRobot> activeRobotList;
    private List<DeliveryRobot> brokenRobotList;

    /**
     * Constructor of company.
     * @param name cannot be empty.
     * @param budget of company
     * @param deliveryCoefficient per one robot delivery.
     * @param productPriceCoefficient multiply by product price.
     * @throws NoNameException
     * @throws NotPositiveNumberException
     */
    public Company(String name, int budget, int deliveryCoefficient, int productPriceCoefficient)
            throws  NoNameException, NotPositiveNumberException {
        if (name.isEmpty()) {
            throw new NoNameException("Name of" + Company.class + "cannot be empty.");
        }
        if (budget < 0) {
            throw new NotPositiveNumberException("Budget cannot be less than 0.");
        }
        this.budget = budget;
        this.name = name;
        this.statistics = new Statistics(this);
        this.productPriceCoefficient = productPriceCoefficient;
        this.deliveryCoefficient = deliveryCoefficient;
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

    public Statistics getStatistics() {
        return statistics;
    }

    /**
     * Add robot to the company if robot is not already in it.
     * @param robot
     * @return
     */
    public boolean addRobot(DeliveryRobot robot) {
        if (statistics.getDeliveryRobotList().contains(robot)
                && robot.getStatus().equals(DeliveryRobot.StatusOfRobot.DELIVERY)) {
            return false;
        }
        statistics.getDeliveryRobotList().add(robot);
        if (robot.getStatus().equals(DeliveryRobot.StatusOfRobot.WAITING)) {
            waitingRobotList.add(robot);
        } else if (robot.getStatus().equals(DeliveryRobot.StatusOfRobot.BROKEN)) {
            brokenRobotList.add(robot);
        }
        robot.setOwner(this);
        return true;
    }

    /**
     * Add delivery to statistics.
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
     * Find best robot for the delivery according to weight of delivery.
     * @param delivery
     * @return
     */
    public Optional<DeliveryRobot> findSuitableRobot(Delivery delivery) {
        int totalWeight = delivery.getProductList().stream()
                .mapToInt(Product::getWeight).sum();
        Optional<DeliveryRobot> bestOption = waitingRobotList.stream()
                .filter(robot -> robot.getLoadcapacity() >= totalWeight)
                .min(Comparator.comparing(robot -> robot.getLoadcapacity() - totalWeight));
        return bestOption;
    }

    /**
     * Next day.
     */
    public void nextDay() {

    }
}
