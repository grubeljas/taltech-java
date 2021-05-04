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

    public Courier(String name, World world) {
        this.name = name;
        this.world = world;
        this.location = Optional.empty();
        this.packetList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setLocation(Location location) {
        this.location = Optional.of(location);
    }

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
