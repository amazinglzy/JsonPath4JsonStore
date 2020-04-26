package jp4js.query.join;

import jp4js.utils.iter.Iter;
import jp4js.query.PlanOperator;

import java.util.LinkedList;
import java.util.List;

public class PCJoin implements PlanOperator<Item> {
    private PlanOperator<Item> parentPlanOperator;
    private PlanOperator<Item> childPlanOperator;

    public PCJoin(PlanOperator<Item> parentPlanOperator, PlanOperator<Item> childPlanOperator) {
        this.parentPlanOperator = parentPlanOperator;
        this.childPlanOperator = childPlanOperator;
    }

    @Override
    public Iter<Item> iterator() {
        return new PCJoinIter(this.parentPlanOperator.iterator(),
                this.childPlanOperator.iterator());
    }

    static class PCJoinIter implements Iter<Item> {
        protected Iter<Item> pIter;
        protected Iter<Item> cIter;
        protected List<Item> buffer;

        public PCJoinIter(Iter<Item> pIter, Iter<Item> cIter) {
            this.pIter = pIter;
            this.cIter = cIter;

            this.buffer = new LinkedList<Item>();
        }

        private PCJoinIter(Iter<Item> pIter, Iter<Item> cIter, List<Item> buffer) {
            this.pIter = pIter;
            this.cIter = cIter;
            this.buffer = buffer;
        }

        protected Item merge(Item p, Item q) {
            return new Item(q.getData(), new LinkedList<>(){{
                addAll(p.getArraySelectionIndices());
                addAll(q.getArraySelectionIndices());
            }});
        }

        protected void readBuffer() {
            if (this.buffer.size() > 0) return;

            while (this.pIter.hasNext() && this.buffer.size() == 0) {
                while (cIter.hasNext() && cIter.read().getData().getLastVisit() <= pIter.read().getData().getFirstVisit())
                    cIter.next();
                Iter<Item> curIter = cIter.cloneCurrentIterator();
                while (curIter.hasNext() && curIter.read().getData().getFirstVisit() <= pIter.read().getData().getFirstVisit())
                    curIter.next();
                while (curIter.hasNext() && curIter.read().getData().getLastVisit() < pIter.read().getData().getLastVisit()) {
                    if (curIter.read().getData().getLevel() == pIter.read().getData().getLevel() + 1)
                        this.buffer.add(merge(pIter.read(), curIter.read()));
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
        public Item read() {
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
        public Iter<Item> cloneCurrentIterator() {
            List<Item> buffer = new LinkedList<Item>(this.buffer);
            return new PCJoinIter(this.pIter.cloneCurrentIterator(), this.cIter.cloneCurrentIterator(), buffer);
        }
    }
}

