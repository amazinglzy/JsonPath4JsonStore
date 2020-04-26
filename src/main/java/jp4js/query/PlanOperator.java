package jp4js.query;

import jp4js.utils.Iter;
import jp4js.index.node.LabelNode;

public interface PlanOperator {
    Iter<LabelNode> iterator();
}
