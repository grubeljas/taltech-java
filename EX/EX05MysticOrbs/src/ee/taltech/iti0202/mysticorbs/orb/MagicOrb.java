package ee.taltech.iti0202.mysticorbs.orb;

public class MagicOrb extends Orb {

    /**
     * Constructor.
     *
     * @param creator oven.
     */
    public MagicOrb(String creator) {
        super(creator);
        int energy = 0;
    }

    @Override
    public void charge(String resource, int amount) {
        super.charge(resource, amount);
        super.charge(resource, amount);
    }

    @Override
    public String toString() {
        return "Magic" + super.toString();
    }
}
