package ee.taltech.iti0202.mysticorbs.factory;
import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.oven.Oven;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class OrbFactory {

    public List<Oven> ovens;
    public List<Orb> orbs;
    public List<Oven> brokenOvens;

    public OrbFactory(ResourceStorage resourceStorage) {
        ResourceStorage storage = resourceStorage;
        ovens = new LinkedList<>();
        orbs = new LinkedList<>();
        brokenOvens = new LinkedList<>();
    }

    public void addOven(Oven oven) {
        getOvens().add(oven);
    }

    public List<Oven> getOvens() {
        return ovens;
    }

    public List<Orb> getAndClearProducedOrbsList() {
        return orbs;
    }

    public int produceOrbs() {
        int n = 0;
        for (Oven oven: getOvens()) {
            if (oven.isBroken()) {
                try {
                    oven.fix();
                } catch (CannotFixException e) {
                    if (e.reason.equals(CannotFixException.Reason.FIXED_MAXIMUM_TIMES)) {
                        brokenOvens.add(oven);
                        continue;
                    }
                }
            }
            Optional<Orb> orb = oven.craftOrb();
            if (orb.isPresent()) {
                n++;
                orbs.add(orb.get());
            }
        }
        return n;
    }

    public int produceOrbs(int cycles) {
        int n = 0;
        for (int i = 0; i < cycles; i++) {
            n += produceOrbs();
        }
        return n;
    }

    public List<Oven> getOvensThatCannotBeFixed() {
        return brokenOvens;
    }

    public void getRidOfOvensThatCannotBeFixed() {
        for (Oven oven: getOvensThatCannotBeFixed()) {
            ovens.remove(oven);
        }
        getOvensThatCannotBeFixed().clear();
    }

    public void optimizeOvensOrder() {
        getOvens().stream().sorted(Oven::compareTo);
    }
}
