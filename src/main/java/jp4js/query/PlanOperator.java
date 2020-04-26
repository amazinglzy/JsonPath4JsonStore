package jp4js.query;

import jp4js.utils.iter.Iter;

public interface PlanOperator<E> {
    Iter<E> iterator();
}
