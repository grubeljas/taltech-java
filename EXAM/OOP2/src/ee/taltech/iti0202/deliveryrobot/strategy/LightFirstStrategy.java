package ee.taltech.iti0202.deliveryrobot.strategy;

import ee.taltech.iti0202.deliveryrobot.delivery.Product;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LightFirstStrategy implements Strategy {
    @Override
    public List makeSort(List list) {
        return (List) list.stream().sorted(Comparator.comparing(Product::getWeight)).collect(Collectors.toList());
    }
}
