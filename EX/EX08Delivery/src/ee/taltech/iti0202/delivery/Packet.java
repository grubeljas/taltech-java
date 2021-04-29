package ee.taltech.iti0202.delivery;

import java.util.ArrayList;
import java.util.List;

public class Packet {

    private String name;
    private Location target;
    static List<Packet> allPackets = new ArrayList<>();

    Packet(String name, Location target) {
        this.name = name;
        this.target = target;
        allPackets.add(this);
    }

    public String getName() {
        return name;
    }

    public Location getTarget() {
        return target;
    }
}
