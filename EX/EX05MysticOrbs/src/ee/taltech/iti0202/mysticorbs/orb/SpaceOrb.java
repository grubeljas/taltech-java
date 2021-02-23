package ee.taltech.iti0202.mysticorbs.orb;

public class SpaceOrb extends Orb{

    public int energy;

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

    public boolean absorb(Orb orb) {
        if (getEnergy() > orb.getEnergy()) {
            this.energy += orb.energy;
            orb.energy = 0;
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
