package ee.taltech.iti0202.deliveryrobot.company;

import ee.taltech.iti0202.deliveryrobot.delivery.Delivery;
import ee.taltech.iti0202.deliveryrobot.delivery.DeliveryRobot;
import ee.taltech.iti0202.deliveryrobot.delivery.Product;
import ee.taltech.iti0202.deliveryrobot.delivery.Warehouse;
import ee.taltech.iti0202.deliveryrobot.exceptions.NoNameException;
import ee.taltech.iti0202.deliveryrobot.exceptions.NotPositiveNumberException;
import ee.taltech.iti0202.deliveryrobot.strategy.Strategy;
import ee.taltech.iti0202.deliveryrobot.strategy.UsualStrategy;

import java.util.LinkedList;
import java.util.List;
import java.util.Comparator;
import java.util.Optional;

public class Company {

    private String name;
    private int budget, deliveryCoefficient, productPriceCoefficient, oneRidePrice;
    private Statistics statistics;
    private Strategy robotStrategy, companyStrategy;
    private List<DeliveryRobot> waitingRobotList = new LinkedList<>();
    private List<DeliveryRobot> activeRobotList = new LinkedList<>();
    private List<DeliveryRobot> brokenRobotList = new LinkedList<>();

    /**
     * Constructor of company.
     * @param name cannot be empty.
     * @param budget of company
     * @param deliveryCoefficient per one robot delivery.
     * @param productPriceCoefficient multiply by product price.
     * @param oneRidePrice price for one robot delivery.
     * @throws NoNameException
     * @throws NotPositiveNumberException
     */
    public Company(String name, int budget, int deliveryCoefficient, int productPriceCoefficient, int oneRidePrice)
            throws  NoNameException, NotPositiveNumberException {
        if (name.isEmpty()) {
            throw new NoNameException("Name of" + Company.class + "cannot be empty.");
        }
        if (budget < 0) {
            throw new NotPositiveNumberException("Budget cannot be less than 0.");
        }
        if (deliveryCoefficient < 0) {
            throw new NotPositiveNumberException("DeliveryCoefficient cannot be less than 0.");
        }
        if (productPriceCoefficient < 0) {
            throw new NotPositiveNumberException("ProductPriceCoefficient cannot be less than 0.");
        }
        if (oneRidePrice < 0) {
            throw new NotPositiveNumberException("OneRidePrice cannot be less than 0.");
        }
        this.budget = budget;
        this.name = name;
        this.statistics = new Statistics(this);
        this.productPriceCoefficient = productPriceCoefficient;
        this.deliveryCoefficient = deliveryCoefficient;
        this.oneRidePrice = oneRidePrice;
        this.robotStrategy = new UsualStrategy();
        this.companyStrategy = new UsualStrategy();
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

    public int getOneRidePrice() {
        return oneRidePrice;
    }

    /**
     * Add robot to the company if robot is not already in it.
     * @param robot
     * @return
     */
    public boolean addRobot(DeliveryRobot robot) {
        if (statistics.getDeliveryRobotList().contains(robot)
                || robot.getStatus().equals(DeliveryRobot.StatusOfRobot.DELIVERY)) {
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

    public void setRobotStrategy(Strategy robotStrategy) {
        this.robotStrategy = robotStrategy;
    }

    public void setCompanyStrategy(Strategy companyStrategy) {
        this.companyStrategy = companyStrategy;
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
     * Make the delivery.
     * @param delivery
     * @param robot
     */
    public int deliver(Delivery delivery, DeliveryRobot robot) {
        robot.setStatus(DeliveryRobot.StatusOfRobot.DELIVERY);
        waitingRobotList.remove(robot);
        activeRobotList.add(robot);
        List<Product> productList = robotStrategy.makeSort(delivery.getProductList());
        int numberOfRides = 1;
        int loadWeight = robot.getLoadcapacity();
        for (int i = 0; i < productList.size(); i++) {
            if (loadWeight - productList.get(i).getWeight() < 0) {
                numberOfRides += 1;
                loadWeight = robot.getLoadcapacity();
            }
            loadWeight -= productList.get(i).getWeight();
        }
        int profit = numberOfRides * (deliveryCoefficient - oneRidePrice)
                + delivery.findTotalPrice() * productPriceCoefficient;
        budget += profit;
        robot.makeDelivery(delivery, numberOfRides);
        statistics.getCurrentDeliveries().remove(delivery);
        statistics.getDeliveryHistory().add(delivery);
        return profit;
    }

    /**
     * Call back all robots to the base.
     */
    public void returnRobots() {
        for (DeliveryRobot robot: activeRobotList) {
            if (robot.getStatus().equals(DeliveryRobot.StatusOfRobot.BROKEN)) {
                brokenRobotList.add(robot);
            } else {
                waitingRobotList.add(robot);
            }
            activeRobotList.remove(robot);
        }
    }

    /**
     * Fix robots if enough money(price of fixing 2x of ride price).
     */
    public void fixRobots() {
        for (DeliveryRobot robot: brokenRobotList) {
            if (budget - 2 * oneRidePrice >= 0) {
                robot.fix();
                budget -= 2 * oneRidePrice;
            } else {
                break;
            }
        }
    }

    /**
     * Take an order.
     */
    public void takeAnOrder() {
        Optional<DeliveryRobot> deliveryRobot;
        List<Delivery> delivery = (List<Delivery>) companyStrategy.makeSort(statistics.getCurrentDeliveries());
        for (int i = 0; i < delivery.size(); i++) {
            deliveryRobot = findSuitableRobot(delivery.get(i));
            if (deliveryRobot.isEmpty()) {
                deliveryRobot = findAnyRobot(delivery.get(i));
            }
            if (deliveryRobot.isPresent()) {
                deliver(delivery.get(i), deliveryRobot.get());
                break;
            }
        }
    }

    /**
     * Find best robot for one delivery according to weight of delivery.
     * @param delivery
     * @return
     */
    public Optional<DeliveryRobot> findSuitableRobot(Delivery delivery) {
        int totalWeight = delivery.getProductList().stream()
                .mapToInt(Product::getWeight).sum();
        return findRobot(totalWeight);
    }

    /**
     * Find any robot for delivery (can carry the most heavy product in delivery).
     * @param delivery
     * @return
     */
    public Optional<DeliveryRobot> findAnyRobot(Delivery delivery) {
        int maxWeight = delivery.getProductList().stream()
                .max(Comparator.comparing(Product::getWeight)).get().getWeight();
        return findRobot(maxWeight);
    }

    /**
     * Find robot according this weight.
     * @param weight
     * @return
     */
    public Optional<DeliveryRobot> findRobot(int weight) {
        Optional<DeliveryRobot> bestOption = waitingRobotList.stream()
                .filter(robot -> robot.getLoadcapacity() >= weight)
                .min(Comparator.comparing(robot -> robot.getLoadcapacity() - weight));
        return bestOption;
    }
}
