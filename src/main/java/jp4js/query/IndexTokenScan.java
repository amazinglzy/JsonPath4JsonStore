package jp4js.query;

import jp4js.index.IndexContext;
import jp4js.index.node.NodeIterator;
import jp4js.query.PlanOperator;

public class IndexTokenScan implements PlanOperator {
    private IndexContext indexContext;

    public IndexTokenScan(IndexContext indexContext) {
        this.indexContext = indexContext;
    }

    @Override
    public NodeIterator iterator() {
        return this.indexContext.openAll();
    }
}
