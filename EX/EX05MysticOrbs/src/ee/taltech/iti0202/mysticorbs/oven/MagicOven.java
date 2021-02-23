package ee.taltech.iti0202.mysticorbs.oven;

import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.orb.MagicOrb;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;
import java.util.Optional;

public class MagicOven extends Oven implements Fixable{

    public int timesFixed, i;

    public MagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        timesFixed = 0;
        i = 1;
    }

    @Override
    public boolean isBroken() {
        return getCreatedOrbsAmount() == 5 * i;
    }

    @Override
    public Optional<Orb> craftOrb() {
        if (!isBroken()) {
            if (getResourceStorage().takeResource("gold", 1)) {
                if (getResourceStorage().takeResource("dust", 3)) {
                    ++orbs;
                    Orb orb;
                    if (getCreatedOrbsAmount() % 2 == 0) {
                        orb = new MagicOrb(getName());
                    } else {
                        orb = new Orb(getName());
                    }
                    orb.charge("gold", 1);
                    orb.charge("dust", 3);
                    return Optional.of(orb);
                } else {
                    getResourceStorage().addResource("gold", 1); //return pearl back,cause out of silver
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void fix() throws CannotFixException {
        if (isBroken()) {
            if (getTimesFixed() < 10) {
                if (storage.takeResource("clay", 25 * (getTimesFixed() + 1))) {
                    if (storage.takeResource("freezing powder", 100 * (getTimesFixed() + 1))) {
                        timesFixed++;
                        i++;
                    } else {
                        storage.addResource("clay", 25 * (getTimesFixed() + 1));
                        throw new CannotFixException(this, CannotFixException.Reason.NOT_ENOUGH_RESOURCES);
                    }
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

    @Override
    public int getTimesFixed() {
        return timesFixed;
    }
}