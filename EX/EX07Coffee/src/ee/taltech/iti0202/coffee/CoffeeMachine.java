package ee.taltech.iti0202.coffee;

public class CoffeeMachine {

    public int coffee, junk, milk;
    public Kitchen kitchen;

    public CoffeeMachine(Kitchen kitchen) {
        this.kitchen = kitchen;
        this.coffee = 0;
        this.junk = 0;
        this.milk = 0;
    }

    public void setCoffee(int coffee) {
        this.coffee = coffee;
    }

    public void cleanMachine() {
        this.junk = 0;
    }

    public boolean makeDrink(Drink.Drinks type) {
        if (junk == 5) {
            cleanMachine();
        }
        junk++;
        return true;
    }
}
