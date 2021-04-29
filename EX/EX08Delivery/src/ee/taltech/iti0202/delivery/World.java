package ee.taltech.iti0202.delivery;

import java.util.*;

public class World {

    private Map<String, Location> locationMap = new HashMap<>();
    private Map<String, Courier> courierMap = new HashMap<>();

    public Optional<Location> findSameLocation(String name) {
        if (locationMap.containsKey(name)) {
            return Optional.of(locationMap.get(name));
        }
        return Optional.empty();
    }

    public Optional<Courier> findSameCourier(String name) {
        if (courierMap.containsKey(name)) {
            return Optional.of(courierMap.get(name));
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
                locationMap.getOrDefault(otherLocations.get(i), new Location(otherLocations.get(i)));
                locationMap.get(otherLocations.get(i)).addDistance(name, distances.get(i));
            }
            locationMap.put(name, newLocation);
            return Optional.of(newLocation);
        }
    }

    public Optional<Courier> addCourier(String name, String to) {
        if (!locationMap.containsKey(to) || courierMap.containsKey(name)) {
            return Optional.empty();
        } else {
            Courier courier = new Courier(name, this);
            courier.setLocation(locationMap.get(to));
            courierMap.put(name, courier);
            return Optional.of(courier);
        }
    }

    public boolean giveStrategy(String name, Strategy strategy) {
        if (courierMap.containsKey(name)) {
            findSameCourier(name).get().setStrategy(strategy);
            return true;
        }
        return false;
    }

    public void tick() {
        for (Courier courier: courierMap.values()) {
            courier.tick();
        }

    }
}
