package ee.taltech.iti0202.lotr;

public class Ring {

    private Type name; private Material madeof;

    public enum Type {
        THE_ONE, GOLDEN, NENYA, OTHER
    }

    public enum Material {
        GOLD, SILVER, BRONZE, PLASTIC, DIAMOND
    }

    /**
     * Ring construct.
     *
     * @param type of ring
     * @param material of ring
     */
    public Ring(Type type, Material material) {
        this.name = type;
        this.madeof = material;
    }

    /**
     * Get type.
     *
     * @return type of ring
     */
    public Type getType() {
        return name;
    }

    /**
     * Get material.
     *
     * @return material of ring
     */
    public Material getMaterial() {
        return madeof;
    }

    /**
     * Main.
     *
     * @param args of main
     */
    public static void main(String[] args) {

// LOTR simplified play through
        Ring theRing = new Ring(Type.THE_ONE, Material.GOLD);
        Person sauron = new Person("Maiar", "Sauron");
        sauron.setRing(theRing);
// after some 4000 years, Gollum got the ring
        Person gollum = new Person("Hobbit", "Gollum");
// let's remove ring from Sauron
        sauron.setRing(null);
        gollum.setRing(theRing);
// after about 500 years, Bilbo got the ring
        Person bilbo = new Person("Hobbit", "Bilbo Baggins");
        gollum.setRing(null);
        bilbo.setRing(theRing);
// after 60 years, Frodo got the ring
        Person frodo = new Person("Hobbit", "Frodo Baggins");
        bilbo.setRing(null);
        frodo.setRing(theRing);
// check Sauron
        Ring fakeOne = new Ring(Type.THE_ONE, Material.PLASTIC);
        sauron.setRing(fakeOne);
        System.out.println(sauron.isSauron()); // No, the ring is fake!
        System.out.println(frodo.isSauron()); // No, he just stole the ring
        Ring nenya = new Ring(Type.NENYA, Material.DIAMOND);
        sauron.setRing(nenya);
        System.out.println(sauron.isSauron()); // No, but he's claiming to be
        frodo.setRing(nenya);
        System.out.println(frodo.isSauron()); // No
        sauron.setRing(theRing);
        System.out.println(sauron.isSauron()); // Affirmative
        //System.out.println(gollum.isSauron());
        System.out.println(null == null);
        System.out.println(gollum.getRing() == null);

    }
}
