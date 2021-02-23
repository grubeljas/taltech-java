package ee.taltech.iti0202.mysticorbs.storage;

import java.util.HashMap;
import java.util.Map;

public class ResourceStorage {

    public Map<String, Integer> resources;

    /**
     * Constructor.
     */
    public ResourceStorage() {
        resources = new HashMap<>();
    }

    /**
     * Check if empty hashmap.
     *
     * @return boolean.
     */
    public boolean isEmpty() {
        for (String resource: resources.keySet()) {
            if (resources.get(resource) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Add resource.
     *
     * @param resource res
     * @param amount number of
     */
    public void addResource(String resource, int amount) {
        if (resources.containsKey(resource.toLowerCase()) && amount > 0) {
            resources.put(resource.toLowerCase(), resources.get(resource.toLowerCase()) + amount);
        } else if (amount > 0) {
            resources.put(resource.toLowerCase(), amount);
        }
    }

    /**
     * Get number of the res.
     *
     * @param resource res
     * @return n.
     */
    public int getResourceAmount(String resource) {
        if (resources.containsKey(resource.toLowerCase()) && resources.get(resource.toLowerCase()) > 0) {
            return resources.get(resource.toLowerCase());
        }
        return 0;
    }

    /**
     * Check enough n of res.
     *
     * @param resource res
     * @param amount n
     * @return bool
     */
    public boolean hasEnoughResource(String resource, int amount) {
        return amount > 0 && getResourceAmount(resource) >= amount;
    }

    /**
     * Take resource from storage.
     *
     * @param resource res.
     * @param amount number.
     * @return bool.
     */
    public boolean takeResource(String resource, int amount) {
        if (hasEnoughResource(resource, amount)) {
            resources.put(resource.toLowerCase(), resources.get(resource.toLowerCase()) - amount);
            return true;
        }
        return false;
    }

}
