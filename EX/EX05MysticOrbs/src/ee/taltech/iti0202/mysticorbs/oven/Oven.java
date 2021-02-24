package ee.taltech.iti0202.mysticorbs.oven;
import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import java.util.Optional;

public class Oven implements Comparable<Oven>, Fixable {

    public final String name;
    public final ResourceStorage storage;
    public int orbs;

    /**
     * Constructor.
     *
     * @param name name
     * @param resourceStorage resStor
     */
    public Oven(String name, ResourceStorage resourceStorage) {
        this.name = name;
        this.storage = resourceStorage;
        this.orbs = 0;
    }

    public String getName() {
        return name;
    }

    public ResourceStorage getResourceStorage() {
        return storage;
    }

    public int getCreatedOrbsAmount() {
        return orbs;
    }

    /**
     * Is broken.
     *
     * @return boolean
     */
    public boolean isBroken() {
        return getCreatedOrbsAmount() == 5 * 3;
    }

    /**
     * Create orb.
     *
     * @return orb of emty
     */
    public Optional<Orb> craftOrb() {
        if (!isBroken()) {
            if (getResourceStorage().takeResource("pearl", 1)) {
                if (getResourceStorage().takeResource("silver", 1)) {
                    ++orbs;
                    Orb orb = new Orb(getName());
                    orb.charge("silver", 1);
                    orb.charge("pearl", 1);
                    return Optional.of(orb);
                } else {
                    getResourceStorage().addResource("pearl", 1); //return pearl back,cause out of silver
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public int compareTo(Oven o) {
        if (isBroken() == o.isBroken()) {
            if (this.getClass().equals(o.getClass())
                    || this instanceof InfinityMagicOven || o instanceof InfinityMagicOven) {
                if (this instanceof MagicOven) {
                    if (getCreatedOrbsAmount() % 2 == 1 && o.getCreatedOrbsAmount() % 2 == 0) {
                        return 1;
                    } else if (getCreatedOrbsAmount() % 2 == 0 && o.getCreatedOrbsAmount() % 2 == 1) {
                        return -1;
                    } else if (getCreatedOrbsAmount() == o.getCreatedOrbsAmount()) {
                        if (this instanceof InfinityMagicOven && o.getClass().equals(MagicOven.class)) {
                            return 1;
                        } else if (getClass().equals(MagicOven.class) && o instanceof InfinityMagicOven) {
                            return  -1;
                        }
                    }
                }
                if (getCreatedOrbsAmount() > o.getCreatedOrbsAmount()) {
                    return -1;
                } else if (getCreatedOrbsAmount() < o.getCreatedOrbsAmount()) {
                    return  1;
                } else if (getCreatedOrbsAmount() == o.getCreatedOrbsAmount()) {
                    return getName().compareTo(o.getName());
                }
            } else if (getClass().equals(SpaceOven.class) || o.getClass().equals(Oven.class)) {
                return 1;
            } else if (getClass().equals(Oven.class) || o.getClass().equals(SpaceOven.class)) {
                return -1;
            }
        } else if (isBroken()) {
            return -1;
        }
        return 1;
    }

    @Override
    public void fix() throws CannotFixException {
        getTimesFixed();
        throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
    }

    @Override
    public int getTimesFixed() {
        return 0;
    }
}
