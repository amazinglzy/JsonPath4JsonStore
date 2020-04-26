package jp4js.query.join;

import jp4js.utils.Iter;
import jp4js.index.node.LabelNode;
import jp4js.query.PlanOperator;

public class KJoin implements PlanOperator {
    private PlanOperator ancestorPlanOp;
    private PlanOperator decedentPlanOp;
    private int k;

    public KJoin(PlanOperator ancestorPlanOp, PlanOperator decedentPlanOp, int k) {
        this.ancestorPlanOp = ancestorPlanOp;
        this.decedentPlanOp = decedentPlanOp;
        this.k = k;
    }

    @Override
    public Iter<LabelNode> iterator() {
        return new KJoinIter(this.ancestorPlanOp.iterator(), this.decedentPlanOp.iterator(), k);
    }

    static class KJoinIter extends PCJoin.PCJoinIter {
        private int k;

        public KJoinIter(Iter<LabelNode> pIter, Iter<LabelNode> cIter, int k) {
            super(pIter, cIter);
            this.k = k;
        }

        protected void readBuffer() {
            if (this.buffer.size() > 0) return;

            while (this.pIter.hasNext() && this.buffer.size() == 0) {
                while (cIter.hasNext() && cIter.read().getLastVisit() < pIter.read().getFirstVisit())
                    cIter.next();
                Iter<LabelNode> curIter = cIter.cloneCurrentIterator();
                while (curIter.hasNext() && curIter.read().getFirstVisit() < pIter.read().getFirstVisit())
                    curIter.next();
                while (curIter.hasNext() && curIter.read().getLastVisit() < pIter.read().getLastVisit()) {
                   if (curIter.read().getLevel() == pIter.read().getLevel() + this.k)
                    this.buffer.add(curIter.read());
                    curIter.next();
                }
                this.pIter.next();
            }
        }
    }
}
