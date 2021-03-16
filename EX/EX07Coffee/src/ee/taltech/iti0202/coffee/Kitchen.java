package ee.taltech.iti0202.coffee;

import ee.taltech.iti0202.coffee.machine.AutomateCoffeeMachine;
import ee.taltech.iti0202.coffee.machine.CapsuleCoffee;
import ee.taltech.iti0202.coffee.machine.CoffeeMachine;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class Kitchen {

    private static Logger log = Logger.getLogger(Kitchen.class.getName());
    public WaterContainer water;
    public int coffee, milk;
    public List<CoffeeMachine> machines;

    public Kitchen(WaterContainer water) {
        this.water = water;
        this.machines = new LinkedList<>();
        this.coffee = 10;
        this.milk = 10;
    }

    /**
     * Buy milk.
     * (One packet is 10 portions.)
     */
    public void buyMilk() {
        log.info("Milk is bought.");
        this.milk += 10;
    }

    /**
     * Buy coffee.
     * (One package is 10 portions)
     */
    public void buyCoffee() {
        log.info("Milk is coffee.");
        this.coffee += 10;
    }

    public void addMachine(CoffeeMachine machine) {
        machines.add(machine);
    }

    public static class Builder {
        private Kitchen kitchen;

        public Builder withKitchen(Kitchen kitchen) {
            this.kitchen = kitchen;
            return this;
        }

        public CoffeeMachine buildUsual() {
            log.info("Usual is created");
            CoffeeMachine e = new CoffeeMachine(kitchen);
            kitchen.addMachine(e);
            return e;
        }

        public AutomateCoffeeMachine buildAutomate() {
            log.info("Automate is created");
            AutomateCoffeeMachine e = new AutomateCoffeeMachine(kitchen);
            kitchen.addMachine(e);
            return e;
        }

        public CapsuleCoffee buildCapsule() {
            log.info("Capsule is created");
            CapsuleCoffee e = new CapsuleCoffee(kitchen);
            kitchen.addMachine(e);
            return e;
        }
    }

}
