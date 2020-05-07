package jp4js.query.navigation;

import jp4js.utils.iter.Iter;
import jp4js.query.PlanOperator;

public class KJoin implements PlanOperator<Item> {
    private PlanOperator<Item> ancestorPlanOp;
    private PlanOperator<Item> decedentPlanOp;
    private int k;

    public KJoin(PlanOperator<Item> ancestorPlanOp, PlanOperator<Item> decedentPlanOp, int k) {
        this.ancestorPlanOp = ancestorPlanOp;
        this.decedentPlanOp = decedentPlanOp;
        this.k = k;
    }

    @Override
    public Iter<Item> iterator() {
        return new KJoinIter(this.ancestorPlanOp.iterator(), this.decedentPlanOp.iterator(), k);
    }

    static class KJoinIter extends PCJoin.PCJoinIter {
        private int k;

        public KJoinIter(Iter<Item> pIter, Iter<Item> cIter, int k) {
            super(pIter, cIter);
            this.k = k;
        }

        protected void readBuffer() {
            if (this.buffer.size() > 0) return;

            while (this.pIter.hasNext() && this.buffer.size() == 0) {
                while (cIter.hasNext() && cIter.read().getData().getLastVisit() < pIter.read().getData().getFirstVisit())
                    cIter.next();
                Iter<Item> curIter = cIter.cloneCurrentIterator();
                while (curIter.hasNext() && curIter.read().getData().getFirstVisit() < pIter.read().getData().getFirstVisit())
                    curIter.next();
                while (curIter.hasNext() && curIter.read().getData().getLastVisit() < pIter.read().getData().getLastVisit()) {
                   if (curIter.read().getData().getLevel() == pIter.read().getData().getLevel() + this.k)
                        this.buffer.add(merge(pIter.read(), curIter.read()));
                    curIter.next();
                }
                this.pIter.next();
            }
        }
    }
}
