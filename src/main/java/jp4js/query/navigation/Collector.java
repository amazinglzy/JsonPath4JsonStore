package jp4js.query.navigation;

import jp4js.query.PlanOperator;
import jp4js.utils.iter.Iter;
import jp4js.utils.iter.MIter;
import jp4js.utils.iter.EmptyIter;
import java.util.List;

public class Collector implements PlanOperator<Item> {
    private List<PlanOperator<Item>> planOps;

    public Collector(List<PlanOperator<Item>> planOps) {
        this.planOps = planOps;
    }
    
    @Override public Iter<Item> iterator() {
        Iter<Item> ret = null;
        for (PlanOperator<Item> op: this.planOps) {
            if (ret == null) ret = op.iterator();
            else ret = new MIter<>(ret, op.iterator(), Item.comparator());
        }
        if (ret == null) return new EmptyIter<>();
        return ret;
    }
}