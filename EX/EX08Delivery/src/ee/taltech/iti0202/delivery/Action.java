package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;

public class Action {

    private Location location;
    private List<String> deposit, take;

    /**
     * Constructor.
     * @param location
     */
    public Action(Location location) {
        this.location = location;
        this.deposit = new ArrayList<>();
        this.take = new ArrayList<>();
    }

    List<String> getDeposit() {
        return deposit;
    }

    void addDeposit(String packetName) {
        deposit.add(packetName);
    }

    List<String> getTake() {
        return take;
    }

    void addTake(String packetName) {
        take.add(packetName);
    }

    Location getGoTo() {
        return location;
    }
}
