package ee.taltech.iti0202.coffee;

import java.util.LinkedList;
import java.util.List;

public class Kitchen {

    public WaterContainer water;

    public Kitchen(WaterContainer water) {

        this.water = water;
        List<CoffeeMachine> machines = new LinkedList<>();
    }
}
