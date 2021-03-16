package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.Drink;
import ee.taltech.iti0202.coffee.Kitchen;

import java.util.logging.Logger;

public class AutomateCoffeeMachine extends CoffeeMachine {

    private static Logger log = Logger.getLogger(AutomateCoffeeMachine.class.getName());

    public AutomateCoffeeMachine(Kitchen kitchen) {
        super(kitchen);
    }

    @Override
    public boolean makeDrink(String drink) {
        if (!Drink.drinksHashMap.containsKey(drink)) {
            log.info("New drink added.");
            Drink.drinksHashMap.put(drink, new int[]{1, 1, 1});
        }
        if (junk == 5) {
            cleanMachine();
        }
        try {
            log.info(drink + "was made.");
            junk++;
            kitchen.water.useWater(Drink.drinksHashMap.get(drink)[2]);
            return true;
        } catch (CoffeeException e) {
            log.severe(e.toString());
            kitchen.water.fillContainer();
            System.out.println(e.getReason());
        }
        return false;
    }
}
