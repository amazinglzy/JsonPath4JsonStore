package jp4js.query.navigation;

import jp4js.query.PlanOperator;
import jp4js.index.node.LabelNode;
import jp4js.utils.iter.Iter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Reorder implements PlanOperator<LabelNode> {
    private PlanOperator<Item> planOp;

    public Reorder(PlanOperator<Item> planOp) {
        this.planOp = planOp;
    }

    @Override
    public Iter<LabelNode> iterator() {
        return new ReorderIter(this.planOp.iterator());
    }

    public static class ReorderIter implements Iter<LabelNode> {
        private Iter<Item> iter;
        private List<Item> data;
        private int currentIndex;

        public ReorderIter(Iter<Item> iter) {
            this.iter = iter;
            this.data = new ArrayList<>();
            while (this.iter.hasNext()) {
                this.data.add(this.iter.read());
                this.iter.next();
            }
            this.data.sort(
                new Comparator<Item>() {
                    @Override
                    public int compare(Item i1, Item i2) {
                        List<Integer> indices1 = i1.getArraySelectionIndices();
                        List<Integer> indices2 = i2.getArraySelectionIndices();
                        assert(indices1.size() == indices2.size());
                        for (int i = 0; i < indices1.size(); i++) {
                            if (indices1.get(i) < indices2.get(i)) return -1;
                            else if (indices1.get(i) > indices2.get(i)) return 1;
                        }
                        return 0;
                    }
                }
            );
            this.currentIndex = 0;
        }

        public ReorderIter(Iter<Item> iter, List<Item> data, int currentIndex) {
            this.iter = iter;
            this.data = data;
            this.currentIndex = currentIndex;
        }

        @Override
        public LabelNode read() {
            if (this.currentIndex >= this.data.size()) 
                throw new IllegalArgumentException();
            return this.data.get(this.currentIndex).getData();
        }

        @Override
        public void next() {
            this.currentIndex ++;
        }

        @Override
        public boolean hasNext() {
            return this.currentIndex < this.data.size();
        }

        @Override
        public ReorderIter cloneCurrentIterator() {
            return new ReorderIter(this.iter, this.data, this.currentIndex);
        }
    }
}