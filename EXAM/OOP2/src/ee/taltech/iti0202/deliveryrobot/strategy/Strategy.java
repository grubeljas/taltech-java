package ee.taltech.iti0202.deliveryrobot.strategy;

import java.util.List;

public interface Strategy<T> {
    /**
     * Sort products or deliveries in necessary order.
     * @param list
     * @return
     */
    List<T> makeSort(List<T> list);
}
