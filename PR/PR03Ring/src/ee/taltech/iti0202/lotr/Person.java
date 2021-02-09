package ee.taltech.iti0202.lotr;

public class Person {

    String race, name; Ring ring = null;

    public Person(String race, String name, Ring ring) {
        this.race = race;
        this.name = name;
        this.ring = ring;
    }

    public Person(String race, String name) {
        this.race = race;
        this.name = name;
    }

    public void setRing(Ring ring) {
        this.ring = ring;
    }

    public String isSauron() {
        if (getName().equals("Sauron")) {
            if (getRing().getType().equals(Ring.Type.THE_ONE)) {
                if (getRing().getMaterial().equals(Ring.Material.GOLD)) {
                    return "Affirmative";
                } else {
                    return "No, the ring is fake!";
                }
            } else {
                return "No, but he's claiming to be";
            }
        } else if (getRing().getType().equals(Ring.Type.THE_ONE) && getRing().getMaterial().equals(Ring.Material.GOLD)) {
            return "No, he just stole the ring";
        } else {
            return "No";
        }
    }

    public String getRace() {
        return race;
    }

    public String getName() {
        return name;
    }

    public Ring getRing() {
        return ring;
    }
}
