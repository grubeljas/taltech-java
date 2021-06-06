package ee.taltech.iti0202.deliveryrobot.strategy;

import ee.taltech.iti0202.deliveryrobot.company.Company;
import ee.taltech.iti0202.deliveryrobot.delivery.Delivery;

import java.util.Comparator;
import java.util.List;

public class ProfitableDeliveryFirstStrategy implements Strategy {

    private Company company;

    /**
     * Constructor to call statistics.
     * @param company
     */
    public ProfitableDeliveryFirstStrategy(Company company) {
        this.company = company;
    }

    @Override
    public List makeSort(List list) {
        List<Delivery> list1 = (List<Delivery>) list.stream()
                .sorted(Comparator.comparing(Delivery::findTotalPrice));
        return list1;
    }
}
