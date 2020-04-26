package jp4js.query.join;

import jp4js.query.PlanOperator;
import jp4js.index.node.LabelNode;
import jp4js.utils.iter.Iter;

public class NormalWrapper implements PlanOperator<Item> {
    private PlanOperator<LabelNode> planOp;

    public NormalWrapper(PlanOperator<LabelNode> planOp) {
        this.planOp = planOp;
    }

    @Override
    public Iter<Item> iterator() {
        return new ArrayScanWrapperIter(this.planOp.iterator());
    }

    public static class ArrayScanWrapperIter implements Iter<Item> {
        private Iter<LabelNode> iter;
        public ArrayScanWrapperIter(Iter<LabelNode> iter) {
            this.iter = iter;
        }

        @Override
        public Item read() {
            LabelNode node = this.iter.read();
            return new Item(node);
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
            return new ArrayScanWrapperIter(iter.cloneCurrentIterator());
        }
    }
}