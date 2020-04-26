package jp4js.query.join;

import jp4js.query.PlanOperator;
import jp4js.index.node.LabelNode;
import jp4js.utils.Iter;

public class SelectionWrapper implements PlanOperator<Item> {
    private PlanOperator<LabelNode> scan;
    private Integer selectionIndex;

    public SelectionWrapper(PlanOperator<LabelNode> scan, Integer selectionIndex) {
        this.scan = scan;
        this.selectionIndex = selectionIndex;
    }

    public Iter<Item> iterator() {
        return new ArrayScanWrapperIter(scan.iterator(), selectionIndex);
    }

    public static class ArrayScanWrapperIter implements Iter<Item> {
        private Iter<LabelNode> iter;
        private Integer selectionIndex;
        public ArrayScanWrapperIter(Iter<LabelNode> iter, Integer selectionIndex) {
            this.iter = iter;
            this.selectionIndex = selectionIndex;
        }

        @Override
        public Item read() {
            LabelNode node = this.iter.read();
            return new Item(node, selectionIndex);
        }

        @Override
        public void next() {
            this.iter.next();
        }

        @Override
        public boolean hasNext() {
            return this.iter.hasNext();
        }

        @Override
        public Iter<Item> cloneCurrentIterator() {
            return new ArrayScanWrapperIter(iter.cloneCurrentIterator(), selectionIndex);
        }
    }
}