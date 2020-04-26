package jp4js.query.join;

import jp4js.query.PlanOperator;
import jp4js.utils.iter.Iter;
import java.util.List;

public class Collector{
    private List<PlanOperator<Item>> planOps;

    public Collector(List<PlanOperator<Item>> planOps) {
        this.planOps = planOps;
    }
}