package jp4js.query;

import jp4js.index.node.NodeIterator;

public interface PlanOperator {
    NodeIterator iterator();
}
