package jp4js.query;

import jp4js.index.IndexContext;
import jp4js.index.node.NodeIterator;
// import jp4js.path.ArrayIndexOperation;
// import jp4js.path.ArraySliceOperation;
import jp4js.query.PlanOperator;


public class IndexArrayScan implements PlanOperator {
    // private ArrayIndexOperation indexOperation;
    // private ArraySliceOperation sliceOperation;
    // private IndexContext indexContext;

    // public IndexArrayScan(IndexContext indexContext,
    //                       ArrayIndexOperation indexOperation,
    //                       ArraySliceOperation sliceOperation) {
    //     isTrue(indexOperation != null || sliceOperation != null, "one of indexOperation " +
    //             "and sliceOperation must not be null");

    //     this.indexContext = indexContext;
    //     this.indexOperation = indexOperation;
    //     this.sliceOperation = sliceOperation;
    // }

    @Override
    public NodeIterator iterator() {
        // if (this.indexOperation != null) return this.indexContext.openArray(this.indexOperation);
        // else return this.indexContext.openArray(this.sliceOperation);
        return null;
    }
}
