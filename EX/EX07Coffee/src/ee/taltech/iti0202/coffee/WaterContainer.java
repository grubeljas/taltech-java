package ee.taltech.iti0202.coffee;

import ee.taltech.iti0202.coffee.machine.CoffeeException;

import java.util.logging.Logger;

public class WaterContainer {

    private static Logger log = Logger.getLogger(WaterContainer.class.getName());
    public int water;
    public int max;

    public WaterContainer(int maximum) {
        this.water = maximum;
        this.max = maximum;
    }

    /**
     * Take water.
     *
     * @param usage number of portions.
     * @throws CoffeeException if not enough water.
     */
    public void useWater(int usage) throws CoffeeException {
        if (water < usage) {
            throw new CoffeeException(CoffeeException.Reason.NOT_ENOUGH_WATER);
        } else {
            log.info(Integer.toString(usage) + " ml was used.");
            water -= usage;
        }
    }

    public void fillContainer() {
        log.info("refilled.");
        water = max;
    }
}
