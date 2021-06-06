package ee.taltech.iti0202.deliveryrobot.strategy;

import ee.taltech.iti0202.deliveryrobot.company.Company;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        List list1 = (List) list.stream()
                .sorted((o1, o2) -> company.getStatistics().getProductPopularity().get(o1).
                        compareTo(company.getStatistics().getProductPopularity().get(o2)))
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        return list1;
    }
}
