package ee.taltech.iti0202.mysticorbs.oven;
import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.orb.SpaceOrb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;
import java.util.Optional;

public class SpaceOven extends Oven implements Fixable {

    public int timesFixed, i;
    static final int STARFRAGMENT = 15;

    /**
     * Constructor.
     *
     * @param name oven.
     * @param resourceStorage resstor.
     */
    public SpaceOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        timesFixed = 0;
        i = 1;
        usualOven = false;
    }

    @Override
    public boolean isBroken() {
        return getCreatedOrbsAmount() >= 5 * 5 * i;
    }

    @Override
    public Optional<Orb> craftOrb() {
        if (!isBroken()) {
            if (getResourceStorage().takeResource("meteorite stone", 1)) {
                if (getResourceStorage().takeResource("star fragment", STARFRAGMENT)) {
                    ++orbs;
                    Orb orb = new SpaceOrb(getName());
                    orb.charge("meteorite stone", 1);
                    orb.charge("star fragment", STARFRAGMENT);
                    return Optional.of(orb);
                } else {
                    getResourceStorage().addResource("meteorite stone", 1); //return stone back,cause out of star
                }
            }
        }
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
        return Optional.empty();
    }

    @Override
    public void fix() throws CannotFixException {
        if (isBroken()) {
            if (getTimesFixed() < 5) {
                if (storage.takeResource("liquid silver", 10 * 4)) {
                    fixOven();
                } else if (storage.takeResource("star essence", 10)) {
                    fixOven();
                } else {
                    throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
                }
            } else {
                throw new CannotFixException(this, CannotFixException.Reason.FIXED_MAXIMUM_TIMES);
            }
        } else {
            throw new CannotFixException(this, CannotFixException.Reason.IS_NOT_BROKEN);
        }
    }

    private void fixOven() {
        timesFixed++;
        i++;
    }

    @Override
    public int getTimesFixed() {
        return timesFixed;
    }
}
