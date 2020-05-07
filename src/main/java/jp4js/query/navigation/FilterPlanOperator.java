package jp4js.query.navigation;

import jp4js.query.PlanOperator;
import jp4js.utils.iter.FilterIter;
import jp4js.utils.iter.Iter;
import jp4js.utils.filter.Filter;

public class FilterPlanOperator implements PlanOperator<Item> {
    private PlanOperator<Item> op;
    private Filter<Item> filter;
    public FilterPlanOperator(PlanOperator<Item> op, Filter<Item> filter) {
        this.op = op;
        this.filter = filter;
    }
    
    @Override
    public Iter<Item> iterator() {
        return new FilterIter<>(this.op.iterator(), filter);
    }
}