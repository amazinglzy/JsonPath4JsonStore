package jp4js.query.join;

import jp4js.index.node.LabelNode;
import jp4js.utils.Iter;
import jp4js.query.PlanOperator;

import java.util.LinkedList;
import java.util.List;

public class PCJoin implements PlanOperator {
    private PlanOperator parentPlanOperator;
    private PlanOperator childPlanOperator;

    public PCJoin(PlanOperator parentPlanOperator, PlanOperator childPlanOperator) {
        this.parentPlanOperator = parentPlanOperator;
        this.childPlanOperator = childPlanOperator;
    }

    @Override
    public Iter<LabelNode> iterator() {
        return new PCJoinIter(this.parentPlanOperator.iterator(),
                this.childPlanOperator.iterator());
    }

    static class PCJoinIter implements Iter<LabelNode> {
        protected Iter<LabelNode> pIter;
        protected Iter<LabelNode> cIter;
        protected List<LabelNode> buffer;

        public PCJoinIter(Iter<LabelNode> pIter, Iter<LabelNode> cIter) {
            this.pIter = pIter;
            this.cIter = cIter;

            this.buffer = new LinkedList<LabelNode>();
        }

        private PCJoinIter(Iter<LabelNode> pIter, Iter<LabelNode> cIter, List<LabelNode> buffer) {
            this.pIter = pIter;
            this.cIter = cIter;
            this.buffer = buffer;
        }

        protected void readBuffer() {
            if (this.buffer.size() > 0) return;

            while (this.pIter.hasNext() && this.buffer.size() == 0) {
                while (cIter.hasNext() && cIter.read().getLastVisit() <= pIter.read().getFirstVisit())
                    cIter.next();
                Iter<LabelNode> curIter = cIter.cloneCurrentIterator();
                while (curIter.hasNext() && curIter.read().getFirstVisit() <= pIter.read().getFirstVisit())
                    curIter.next();
                while (curIter.hasNext() && curIter.read().getLastVisit() < pIter.read().getLastVisit()) {
                    if (curIter.read().getLevel() == pIter.read().getLevel() + 1)
                        this.buffer.add(curIter.read());
                    curIter.next();
                }
                this.pIter.next();
            }
        }

        private boolean checkEmpty(boolean throwable) {
            if (this.buffer.size() == 0)
                readBuffer();
            if (this.buffer.size() == 0) {
                if (throwable) throw new IllegalArgumentException();
                return false;
            }
            return true;
        }

        @Override
        public LabelNode read() {
            this.checkEmpty(true);
            return this.buffer.get(0);
        }

        @Override
        public void next() {
            this.checkEmpty(true);
            this.buffer.remove(0);
        }

        @Override
        public boolean hasNext() {
            return checkEmpty(false);
        }

        @Override
        public Iter<LabelNode> cloneCurrentIterator() {
            List<LabelNode> buffer = new LinkedList<LabelNode>(this.buffer);
            return new PCJoinIter(this.pIter.cloneCurrentIterator(), this.cIter.cloneCurrentIterator(), buffer);
        }
    }
}
