package ee.taltech.iti0202.deliveryrobot.strategy;

import java.util.List;

public interface Strategy {
    /**
     * Sort products or deliveries in necessary order.
     * @param list
     * @return
     */
    List makeSort(List list);
}
