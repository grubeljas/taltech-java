package ee.taltech.iti0202.mysticorbs.orb;

public class Orb {

    public final String creator;
    public int energy;

    /**
     * Constructor.
     *
     * @param creator oven.
     */
    public Orb(String creator) {
        this.creator = creator;
        this.energy = 0;
    }

    /**
     * Charge energy.
     *
     * @param resource res
     * @param amount int
     */
    public void charge(String resource, int amount) {
        if (!resource.equalsIgnoreCase("dust") && !resource.isBlank() && amount > 0) {
            energy += resource.length() * amount;
        }
    }

    public int getEnergy() {
        return energy;
    }

    /**
     * Convert.
     *
     * @return "Orb by {author}"
     */
    public String toString() {
        return String.format("Orb by %1$s", creator);
    }
}
