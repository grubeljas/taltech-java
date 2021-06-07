package ee.taltech.iti0202.deliveryrobot.strategy;

import ee.taltech.iti0202.deliveryrobot.company.Company;
import ee.taltech.iti0202.deliveryrobot.delivery.Product;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.reverseOrder;

public class PopularFirstStrategy implements Strategy {
    private Company company;

    /**
     * Constructor to call statistics.
     * @param company
     */
    public PopularFirstStrategy(Company company) {
        this.company = company;
    }

    @Override
    public List makeSort(List list) {
        list = (List) list.stream()
                .sorted(Comparator.comparing(o -> company.getStatistics().getProductPopularity().get(o)))
                .collect(Collectors.toList());
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
}
