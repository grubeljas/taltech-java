package ee.taltech.iti0202.mysticorbs.factory;
import ee.taltech.iti0202.mysticorbs.exceptions.CannotFixException;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;
import ee.taltech.iti0202.mysticorbs.orb.Orb;
import ee.taltech.iti0202.mysticorbs.oven.Oven;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class OrbFactory {

    public List<Oven> ovens;
    public List<Orb> orbs;
    public List<Oven> brokenOvens;

    /**
     * Constructor.
     *
     * @param resourceStorage resstor.
     */
    public OrbFactory(ResourceStorage resourceStorage) {
        ResourceStorage storage = resourceStorage;
        ovens = new LinkedList<>();
        orbs = new LinkedList<>();
        brokenOvens = new LinkedList<>();
    }

    /**
     * Add oven.
     *
     * @param oven
     */
    public void addOven(Oven oven) {
        getOvens().add(oven);
    }

    public List<Oven> getOvens() {
        return ovens;
    }

    public List<Orb> getAndClearProducedOrbsList() {
        List<Orb> produced = new LinkedList<Orb>(orbs);
        orbs.clear();
        return produced;
    }

    /**
     * Produce orbs.
     *
     * @return
     */
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

    /**
     * Produce multiple producing.
     *
     * @param cycles
     * @return
     */
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

    /**
     * Remove broken ovens.
     */
    public void getRidOfOvensThatCannotBeFixed() {
        for (Oven oven: getOvensThatCannotBeFixed()) {
            ovens.remove(oven);
        }
        getOvensThatCannotBeFixed().clear();
    }

    /**
     * Optimize order.
     */
    public void optimizeOvensOrder() {
        getOvens().stream().sorted(Oven::compareTo);
    }
}
