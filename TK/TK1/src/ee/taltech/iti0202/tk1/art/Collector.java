package ee.taltech.iti0202.tk1.art;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Collector {

    private List<Painting> collection = new LinkedList<>();

    /**
     * Add painting to the collection.
     *
     * @param painting painting
     * @return bool
     */
    public boolean addPainting(Painting painting) {
        List<String> names = collection.stream().map(Painting::getTitle)
                .collect(Collectors.toList());
        if (collection.contains(painting) || names.contains(painting.getTitle())) {
            return false;
        } else {
            collection.add(painting);
            return true;
        }
    }

    /**
     * Sell painting.
     *
     * @param painting painting
     * @param fellowCollector another collector
     * @return bool
     */
    public boolean sellPainting(Painting painting, Collector fellowCollector) {
        if (collection.contains(painting) && fellowCollector != this) {
            if (fellowCollector.addPainting(painting)) {
                collection.remove(painting);
                return true;
            }
        }
        return false;
    }

    /**
     * Get collection.
     *
     * @return list
     */
    public List<Painting> getPaintings() {
        return collection;
    }

}
