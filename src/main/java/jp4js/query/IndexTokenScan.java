package jp4js.query;

import jp4js.utils.iter.Iter;
import jp4js.storage.IndexContext;
import jp4js.storage.node.LabelNode;

public class IndexTokenScan implements PlanOperator<LabelNode> {
    private IndexContext indexContext;

    public IndexTokenScan(IndexContext indexContext) {
        this.indexContext = indexContext;
    }

    @Override
    public Iter<LabelNode> iterator() {
        return this.indexContext.openAll();
    }
}
