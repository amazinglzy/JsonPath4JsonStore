package jp4js.query;

import jp4js.utils.iter.Iter;
import java.util.List;
import java.util.LinkedList;

public class GeneralPlanOp {
    public static <T> Concat<T> createConcat(List<PlanOperator<T>> ops) {
        return new Concat<T>(ops);
    }

    public static class Concat<E> implements PlanOperator<E> {
        
        private List<PlanOperator<E>> ops;

        public Concat(List<PlanOperator<E>> ops) {
            this.ops = ops;
        }
        
        @Override
        public Iter<E> iterator() {
            return new SelfIter<E>(new LinkedList<>(){{
                for (PlanOperator<E> op: ops) {
                    add(op.iterator());
                }
            }});

        }

        public static class SelfIter<E> implements Iter<E> {
            private List<Iter<E>> iters;

            public SelfIter(List<Iter<E>> iters) {
                this.iters = iters;
            }

            private void popEmpty() {
                while (this.iters.size() > 0 && !this.iters.get(0).hasNext()) 
                    this.iters.remove(0);
            }

            @Override
            public E read() {
                popEmpty();
                return this.iters.get(0).read();
            }

            @Override
            public void next() {
                popEmpty();
                this.iters.get(0).next();
            }

            @Override
            public boolean hasNext() {
                popEmpty();
                return this.iters.size() > 0;
            }
        }
    }
}