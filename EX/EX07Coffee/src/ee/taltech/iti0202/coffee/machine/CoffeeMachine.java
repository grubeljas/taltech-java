package ee.taltech.iti0202.coffee.machine;

import ee.taltech.iti0202.coffee.Drink;
import ee.taltech.iti0202.coffee.Kitchen;

import java.util.logging.Logger;

public class CoffeeMachine {

    private static Logger log = Logger.getLogger(CoffeeMachine.class.getName());
    public int junk;
    public Kitchen kitchen;

    public CoffeeMachine(Kitchen kitchen) {
        this.kitchen = kitchen;
        this.junk = 0;
    }

    public void cleanMachine() {
        log.info("Machine is cleaned");
        this.junk = 0;
    }

    /**
     * Make drink using water, milk and coffee.
     *
     * @param drink
     * @return true if success
     */
    public boolean makeDrink(String drink) {
        if (!Drink.drinksHashMap.containsKey(drink)) {
            log.info("New drink added.");
            Drink.addDrink(drink, new int[] {1, 2, 3});
        }
        if (junk == 5) {
            cleanMachine();
        }
        while (kitchen.coffee < Drink.drinksHashMap.get(drink)[0]) {
            kitchen.buyCoffee();
        }
        while (kitchen.milk < Drink.drinksHashMap.get(drink)[1]) {
            kitchen.buyMilk();
        }
        try {
            log.info(drink + " was made.");
            junk++;
            kitchen.milk -= Drink.drinksHashMap.get(drink)[1];
            kitchen.coffee -= Drink.drinksHashMap.get(drink)[0];
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
