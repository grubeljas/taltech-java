package ee.taltech.iti0202.delivery;

public class ABABstrategy implements Strategy {

    public Action[] actions = new Action[2];
    boolean isFirst = false;

    /**
     * Constructor.
     * @param action
     * @param action1
     */
    public ABABstrategy(Action action, Action action1) {
        this.actions[0] = action;
        this.actions[1] = action1;
    }

    @Override
    public Action getAction() {
        isFirst = !isFirst;
        if (isFirst) {
            return actions[0];
        } else {
            return actions[1];
        }
    }
}
