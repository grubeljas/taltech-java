package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Courier {

    String name;
    World world;
    Optional<Location> location;
    List<Packet> packetList;
    Strategy strategy;
    Integer timeToDestination;

    /**
     * Construstor.
     * @param name
     * @param world
     */
    public Courier(String name, World world) {
        this.name = name;
        this.world = world;
        this.location = Optional.empty();
        this.packetList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    /**
     * Set location.
     * @param location
     */
    public void setLocation(Location location) {
        this.location = Optional.of(location);
    }

    /**
     * Get current location.
     * @return
     */
    public Optional<Location> getLocation() {
        if (location.isPresent()) {
            return location;
        }
        return Optional.empty();
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    /**
     * Tick.
     */
    public void tick() {
        if (timeToDestination > 0) {
            timeToDestination--;
        } else {
            Action action = getStrategy().getAction();
            action.getDeposit();
            action.getTake();
            setLocation(action.getGoTo());
        }

    }
}
