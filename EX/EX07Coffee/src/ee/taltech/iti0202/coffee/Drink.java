package ee.taltech.iti0202.coffee;

import java.util.Map;
import java.util.HashMap;

public class Drink {

    /**
     * In array first is coffee, second is milk, third is water.
     *
     */
    public static Map<String, int[]> drinksHashMap = new HashMap<>() {{
        put("Coffee", new int[]{1, 0, 1});
        put("Latte", new int[]{3, 3, 3});
        put("Espresso", new int[]{3, 0, 3});
        put("Cappuccino", new int[]{3, 4, 3});
    }};

    /**
     * Add new drink in hashmap with number of ingredients(not changeable).
     *
     * @param drink
     * @param ingredients
     */
    public static void addDrink(String drink, int[] ingredients) {
        drinksHashMap.put(drink, ingredients);
    }

}
