package ee.taltech.iti0202.delivery;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Location {

    private String name;
    HashMap<String, Integer> neighbours;
    List<Packet> packets;

    /**
     * Location.
     * @param name
     */
    public Location(String name) {
        this.name = name;
        this.packets = new LinkedList<>();
        this.neighbours = new HashMap<String, Integer>();
    }

    public String getName() {
        return name;
    }

    /**
     * To string name.
     * @return
     */
    public String toString() {
        return getName();
    }

    /**
     * Add packet to location.
     * @param packet
     */
    public void addPacket(Packet packet) {
        packets.add(packet);
    }

    /**
     * Check if it has it.
     * @param name
     * @return
     */
    public Optional<Packet> getPacket(String name) {
        for (Packet packet: packets) {
            if (packet.getName().equals(name)) {
                return Optional.of(packet);
            }
        }
        return Optional.empty();
    }

    /**
     * Add distance.
     * @param location
     * @param distance
     */
    public void addDistance(String location, int distance) {
        neighbours.put(location, distance);
    }

    /**
     * Get distance to location.
     * @param name
     * @return
     */
    public Integer getDistanceTo(String name) {
        if (neighbours.containsKey(name)) {
            return neighbours.get(name);
        }
        return Integer.MAX_VALUE;
    }
}
