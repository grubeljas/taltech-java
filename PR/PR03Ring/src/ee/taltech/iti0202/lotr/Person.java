package ee.taltech.iti0202.lotr;

public class Person {

    private String race, name; private Ring ring = null;

    /**
     * Constructor full.
     *
     * @param race
     * @param name
     * @param ring
     */
    public Person(String race, String name, Ring ring) {
        this.race = race;
        this.name = name;
        this.ring = ring;
    }

    /**
     * Constructor not full.
     *
     * @param race
     * @param name
     */
    public Person(String race, String name) {
        this.race = race;
        this.name = name;
    }

    /**
     * Set ring.
     *
     * @param ring
     */
    public void setRing(Ring ring) {
        this.ring = ring;
    }

    /**
     * Check is this Sauron and what ring is this.
     *
     * @return
     */
    public String isSauron() {

        if (getName().equals("Sauron")) {
            if (getRing().equals(null)) {
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
        } else if (getRing().getType().equals(Ring.Type.THE_ONE)
                && getRing().getMaterial().equals(Ring.Material.GOLD)) {
            return "No, he just stole the ring";
        } else {
            return "No";
        }
    }

    /**
     * Get race.
     *
     * @return
     */
    public String getRace() {
        return race;
    }

    /**
     * Get name.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Get ring.
     *
     * @return
     */
    public Ring getRing() {
        return ring;
    }
}
