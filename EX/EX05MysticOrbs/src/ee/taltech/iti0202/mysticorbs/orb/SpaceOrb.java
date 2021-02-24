package ee.taltech.iti0202.mysticorbs.orb;

public class SpaceOrb extends Orb {

    public int energy;

    /**
     * Constructor.
     *
     * @param creator oven.
     */
    public SpaceOrb(String creator) {
        super(creator);
        energy = 100;
    }

    @Override
    public void charge(String resource, int amount) {
    }

    @Override
    public String toString() {
        return "Space" + super.toString();
    }

    /**
     * Absorb another orb.
     *
     * @param orb orb.
     * @return bool
     */
    public boolean absorb(Orb orb) {
        if (getEnergy() > orb.getEnergy()) {
            this.energy += orb.getEnergy();
            if (orb instanceof SpaceOrb) {
                ((SpaceOrb) orb).zeroEnrgy();
            } else {
                orb.energy = 0;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    /**
     * Make energy zero.
     *
     */
    public void zeroEnrgy() {
        this.energy = 0;
    }
}
