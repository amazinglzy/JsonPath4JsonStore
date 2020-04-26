package jp4js.query;

import jp4js.utils.Iter;
import jp4js.index.node.Node;

public interface PlanOperator {
    Iter<Node> iterator();
}
