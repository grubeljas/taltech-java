package ee.taltech.iti0202.lotr;

public class Person {

    private final String race, name; private Ring ring = null;

    /**
     * Constructor full.
     *
     * @param race of person.
     * @param name of person nimi.
     * @param ring which ring he has.
     */
    public Person(String race, String name, Ring ring) {
        this.race = race;
        this.name = name;
        this.ring = ring;
    }

    /**
     * Constructor not full.
     *
     * @param race from fantasy.
     * @param name nimi.
     */
    public Person(String race, String name) {
        this.race = race;
        this.name = name;
    }

    /**
     * Set ring.
     *
     * @param ring or null.
     */
    public void setRing(Ring ring) {
        this.ring = ring;
    }

    /**
     * Check is this Sauron and what ring is this.
     *
     * @return string which describes all info.
     */
    public String isSauron() {
        if (getName().equals("Sauron")) {
            if (getRing() == null) {
                return "No, but he's claiming to be";
            } else if (getRing().getType().equals(Ring.Type.THE_ONE)) {
                if (getRing().getMaterial().equals(Ring.Material.GOLD)) {
                    return "Affirmative";
                } else {
                    return "No, the ring is fake!";
                }
            } else {
                return "No, but he's claiming to be";
            }
        } if (getRing() != null) {
            if (getRing().getType().equals(Ring.Type.THE_ONE)
                    && getRing().getMaterial().equals(Ring.Material.GOLD)) {
                return "No, he just stole the ring";
            }
        }
        return "No";
    }

    /**
     * Get race.
     *
     * @return race of this person
     */
    public String getRace() {
        return race;
    }

    /**
     * Get name.
     *
     * @return name of person.
     */
    public String getName() {
        return name;
    }

    /**
     * Get ring.
     *
     * @return ring or null.
     */
    public Ring getRing() {
        return ring;
    }
}
