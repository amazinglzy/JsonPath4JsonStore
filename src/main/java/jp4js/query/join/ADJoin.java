package jp4js.query.join;

import jp4js.utils.Iter;
import jp4js.index.node.Node;
import jp4js.query.PlanOperator;

public class ADJoin implements PlanOperator {
    private PlanOperator ancestorPlanOp;
    private PlanOperator decedentPlanOp;

    public ADJoin(PlanOperator ancestorPlanOp, PlanOperator decedentPlanOp) {
        this.ancestorPlanOp = ancestorPlanOp;
        this.decedentPlanOp = decedentPlanOp;
    }

    @Override
    public Iter<Node> iterator() {
        return new ADJoinIter(this.ancestorPlanOp.iterator(), this.decedentPlanOp.iterator());
    }

    static class ADJoinIter extends PCJoin.PCJoinIter {

        public ADJoinIter(Iter<Node> pIter, Iter<Node> cIter) {
            super(pIter, cIter);
        }

        protected void readBuffer() {
            if (this.buffer.size() > 0) return;

            while (this.pIter.hasNext() && this.buffer.size() == 0) {
                while (cIter.hasNext() && cIter.read().getLastVisit() < pIter.read().getFirstVisit())
                    cIter.next();
                Iter<Node> curIter = cIter.cloneCurrentIterator();
                while (curIter.hasNext() && curIter.read().getFirstVisit() < pIter.read().getFirstVisit())
                    curIter.next();
                while (curIter.hasNext() && curIter.read().getLastVisit() < pIter.read().getLastVisit()) {
                    this.buffer.add(curIter.read());
                    curIter.next();
                }
                this.pIter.next();
            }
        }
    }
}
