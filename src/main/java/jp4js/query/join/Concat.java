package jp4js.query.join;

import jp4js.utils.Iter;
import jp4js.index.node.LabelNode;
import jp4js.query.PlanOperator;

import java.util.LinkedList;
import java.util.List;

public class Concat implements PlanOperator<LabelNode> {
    
    private List<PlanOperator<Item>> ops;

    public Concat(List<PlanOperator<Item>> ops) {
        this.ops = ops;
    }
    
    @Override
    public Iter<LabelNode> iterator() {
        return new ConcatNodeIterator(new LinkedList<>(){{
            for (PlanOperator<Item> op: ops) {
                add(op.iterator());
            }
        }});

    }

    static class ConcatNodeIterator implements Iter<LabelNode> {
        private List<Iter<Item>> iters;

        public ConcatNodeIterator(List<Iter<Item>> iters) {
            this.iters = iters;
        }

        private void popEmpty() {
            while (this.iters.size() > 0 && !this.iters.get(0).hasNext()) 
                this.iters.remove(0);
        }

        @Override
        public LabelNode read() {
            popEmpty();
            return this.iters.get(0).read().getData();
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

        @Override
        public ConcatNodeIterator cloneCurrentIterator() {
            List<Iter<Item>> itersCopy = new LinkedList<>() {{
                for (Iter<Item> iter : iters) {
                    add(iter.cloneCurrentIterator());
                }
            }};
            return new ConcatNodeIterator(itersCopy);
        }
    }
}
