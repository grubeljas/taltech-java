package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class World {

    private List<Courier> couriers;
    private List<Location> locations;

    public World() {
        this.couriers = new ArrayList<>();
        this.locations = new ArrayList<>();
    }

    public Optional<Location> findSameLocation(String name) {
        for (Location location: locations) {
            if (location.getName().equals(name)) {
                return Optional.of(location);
            }
        }
        return Optional.empty();
    }

    public Optional<Courier> findSameCourier(String name) {
        for (Courier courier: couriers) {
            if (courier.getName().equals(name)) {
                return Optional.of(courier);
            }
        }
        return Optional.empty();
    }

    public Optional<Location> addLocation(String name, List<String> otherLocations, List<Integer> distances) {
        if (findSameLocation(name).isPresent() || otherLocations.size() != distances.size()) {
            if (findSameLocation(name).isPresent()) {
                for (int i = 0; i < otherLocations.size(); i++) {
                    findSameLocation(name).get().addDistance(otherLocations.get(i), distances.get(i));
                }
            }
            return Optional.empty();
        } else {
            Location newLocation = new Location(name);
            for (int i = 0; i < otherLocations.size(); i++) {
                newLocation.addDistance(otherLocations.get(i), distances.get(i));
            }
            locations.add(newLocation);
            return Optional.of(newLocation);
        }
    }

    public Optional<Courier> addCourier(String name, String to) {
        if (findSameLocation(to).isPresent() || findSameCourier(name).isPresent()) {
            return Optional.empty();
        } else {
            Courier courier = new Courier(name);
            courier.setLocation(findSameLocation(to).get());
            couriers.add(courier);
            return Optional.of(courier);
        }
    }

    public boolean giveStrategy(String name, Strategy strategy) {
        if (findSameCourier(name).isPresent()) {
            findSameCourier(name).get().setStrategy(strategy);
            return true;
        }
        return false;
    }

    public void tick() {
        for (Courier courier: couriers) {
            courier.tick();
        }

    }
}
