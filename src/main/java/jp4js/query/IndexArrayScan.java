package jp4js.query;

import jp4js.index.IndexContext;
import jp4js.index.node.NodeIterator;
import jp4js.query.PlanOperator;
import jp4js.utils.Utils;
import static jp4js.index.node.ArrayNode.ArraySelections;

import java.util.LinkedList;
import java.util.List;

public class IndexArrayScan implements PlanOperator {

    private ArraySelections selections;
    private IndexContext indexContext;

    public IndexArrayScan(IndexContext indexContext,
                          ArraySelections selections) {

        Utils.isTrue(indexContext != null && selections != null, "Index Context must not be null "
            +"and Selections must not be null");
        this.indexContext = indexContext;
        this.selections = selections;
    }

    @Override
    public NodeIterator iterator() {
        // if (this.indexOperation != null) return this.indexContext.openArray(this.indexOperation);
        // else return this.indexContext.openArray(this.sliceOperation);
        return null;
    }
}
