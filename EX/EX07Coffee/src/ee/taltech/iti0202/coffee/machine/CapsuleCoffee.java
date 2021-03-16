package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.Drink;
import ee.taltech.iti0202.coffee.Kitchen;

import java.util.LinkedList;
import java.util.logging.Logger;

public class CapsuleCoffee extends CoffeeMachine {

    private static Logger log = Logger.getLogger(CapsuleCoffee.class.getName());
    boolean hasFullCapsule;
    public String currentCapsule;
    public LinkedList<String> capsules;

    public CapsuleCoffee(Kitchen kitchen) {
        super(kitchen);
        this.hasFullCapsule = false;
        this.currentCapsule = "";
        this.capsules = new LinkedList<>();
    }

    public void addCapsule(String capsule) {
        log.info(capsule + "is added.");
        capsules.add(capsule);
    }

    @Override
    public boolean makeDrink(String drink) {
        if (!Drink.drinksHashMap.containsKey(drink)) {
            log.info("New drink added.");
            Drink.addDrink(drink, new int[] {1, 2, 3});
        }
        if (!currentCapsule.equals(drink) || !hasFullCapsule) {
            if (hasFullCapsule) {
                addCapsule(currentCapsule);
            }
            if (capsules.contains(drink)) {
                capsules.remove(drink);
                currentCapsule = drink;
                hasFullCapsule = true;
                log.info("Capsule is changed.");
            }
        }
        if (junk == 10) {
            cleanMachine();
        }
        try {
            junk++;
            if (!hasFullCapsule) {
                throw new CoffeeException(CoffeeException.Reason.WATER_IS_LEAKING);
            }
            kitchen.water.useWater(Drink.drinksHashMap.get(drink)[2]);
            hasFullCapsule = false;
            log.info(drink + " was made.");
            return true;
        } catch (CoffeeException e) {
            log.severe(e.getReason().toString());
            if (e.getReason().equals(CoffeeException.Reason.NOT_ENOUGH_WATER)) {
                kitchen.water.fillContainer();
            }
        }
        return false;
    }

}
