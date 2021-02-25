package ee.taltech.iti0202.mysticorbs.oven;
import ee.taltech.iti0202.mysticorbs.storage.ResourceStorage;

public class InfinityMagicOven extends MagicOven {

    /**
     * Constructor.
     *
     * @param name oven.
     * @param resourceStorage res stor of factory.
     */
    public InfinityMagicOven(String name, ResourceStorage resourceStorage) {
        super(name, resourceStorage);
        usualOven = false;
    }

    @Override
    public boolean isBroken() {
        return false;
    }
}
