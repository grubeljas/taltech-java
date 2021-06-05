package ee.taltech.iti0202.deliveryrobot.delivery;

import ee.taltech.iti0202.deliveryrobot.company.Company;
import ee.taltech.iti0202.deliveryrobot.exceptions.NoNameException;
import ee.taltech.iti0202.deliveryrobot.exceptions.NotPositiveNumberException;

import java.util.Optional;

public class DeliveryRobot {

    public enum StatusOfRobot {
        WAITING, DELIVERY, BROKEN
    }

    private String name;
    private Optional<Company> belongsTo;
    private int loadcapacity;
    private static int idCounter = 0;
    private int id = ++idCounter;
    private StatusOfRobot status = StatusOfRobot.WAITING;

    /**
     * Constructor with name and capacity only. This is necessary to add company which belongs to.
     * @param name
     */
    public DeliveryRobot(String name, int loadcapacity) throws NoNameException, NotPositiveNumberException {
        if (name.isEmpty()) {
            throw new NoNameException("Name of" + DeliveryRobot.class + "cannot be empty.");
        }
        if (loadcapacity <= 0) {
            throw new NotPositiveNumberException("Load capacity mustn't be 0 or less.");
        }
        this.name = name;
        this.loadcapacity = loadcapacity;
        this.belongsTo = Optional.empty();
    }

    /**
     * Constructor with name and company.
     * @param name
     * @param company
     */
    public DeliveryRobot(String name, Company company) {
        this.name = name;
        setBelongsTo(company);
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
     * Add company where robot from.
     * Call from Company method addRobot or Robot constructor.
     * @param belongsTo
     */
    public void setBelongsTo(Company belongsTo) {
        this.belongsTo = Optional.of(belongsTo);
    }

    public StatusOfRobot getStatus() {
        return status;
    }

    public void setStatus(StatusOfRobot status) {
        this.status = status;
    }
}
