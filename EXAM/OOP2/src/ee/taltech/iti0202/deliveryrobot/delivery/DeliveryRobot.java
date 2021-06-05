package ee.taltech.iti0202.deliveryrobot.delivery;

import ee.taltech.iti0202.deliveryrobot.Company;

import java.util.Optional;

public class DeliveryRobot {

    private String name;
    private Optional<Company> belongsTo;
    private int loadcapacity;
    private static int idCounter = 0;
    private int id = ++idCounter;
    private Status status = Status.WAITING;

    /**
     * Constructor with name only. This is necessary to add company which belongs to.
     * @param name
     */
    public DeliveryRobot(String name) {
        this.name = name;
        this.belongsTo = Optional.empty();
    }

    /**
     * Constructor with name and company.
     * @param name
     * @param company
     */
    public DeliveryRobot(String name, Company company) {
        this.name = name;
        this.belongsTo = Optional.of(company);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getLoadcapacity() {
        return loadcapacity;
    }

    public Optional<Company> getBelongsTo() {
        return belongsTo;
    }

    /**
     * Call only from Company method addRobot(Robot robot).
     * @param belongsTo
     */
    public void setBelongsTo(Optional<Company> belongsTo) {
        this.belongsTo = belongsTo;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
