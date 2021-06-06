package ee.taltech.iti0202.deliveryrobot.strategy;

import java.util.List;

public class UsualStrategy implements Strategy {
    @Override
    public List makeSort(List list) {
        return list;
    }
}
