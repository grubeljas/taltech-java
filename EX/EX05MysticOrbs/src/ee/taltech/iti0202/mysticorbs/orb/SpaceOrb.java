package ee.taltech.iti0202.mysticorbs.orb;

import ee.taltech.iti0202.mysticorbs.oven.MagicOven;
import ee.taltech.iti0202.mysticorbs.oven.SpaceOven;

public class SpaceOrb extends Orb {

    public int energy;

    /**
     * Constructor.
     *
     * @param creator oven.
     */
    public SpaceOrb(String creator) {
        super(creator);
        this.energy = 100;
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
        if (energy >= orb.getEnergy()) {
            this.energy += orb.getEnergy();
            if (orb instanceof SpaceOrb) {
                ((SpaceOrb)orb).energy = 0;
            } else if (orb instanceof MagicOrb) {
                ((MagicOrb)orb).energy = 0;
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
}
