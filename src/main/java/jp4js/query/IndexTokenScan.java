package jp4js.query;

import jp4js.index.IndexContext;
import jp4js.index.node.Node;
import jp4js.utils.Iter;
import jp4js.query.PlanOperator;

public class IndexTokenScan implements PlanOperator {
    private IndexContext indexContext;

    public IndexTokenScan(IndexContext indexContext) {
        this.indexContext = indexContext;
    }

    @Override
    public Iter<Node> iterator() {
        return this.indexContext.openAll();
    }
}
