package jp4js.query.navigation;

import jp4js.query.PlanOperator;
import jp4js.utils.iter.Iter;
import jp4js.index.node.LabelNode;
import jp4js.utils.iter.FilterIter;
import jp4js.utils.filter.Filter;
import jp4js.utils.iter.MIter;
import jp4js.utils.iter.EmptyIter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;


public class NavigationPlanOp {
    public static ADJoin createADJoin(PlanOperator<Item> ancestorPlanOp, PlanOperator<Item> decedentPlanOp) {
        return new ADJoin(ancestorPlanOp, decedentPlanOp);
    }
    
    public static PCJoin createPCJoin(PlanOperator<Item> parentPlanOperator, PlanOperator<Item> childPlanOperator) {
        return new PCJoin(parentPlanOperator, childPlanOperator);
    }

    public static KJoin createKJoin(PlanOperator<Item> ancestorPlanOp, PlanOperator<Item> decedentPlanOp, int k) {
        return new KJoin(ancestorPlanOp, decedentPlanOp, k);
    }

    public static Reorder createReorder(PlanOperator<Item> planOp) {
        return new Reorder(planOp);
    }

    public static LabelNode2Item createLabelNode2Item(PlanOperator<LabelNode> planOp) {
        return new LabelNode2Item(planOp);
    }

    public static Item2LabelNode createItem2LabelNode(PlanOperator<Item> planOp) {
        return new Item2LabelNode(planOp);
    }

    public static FilterPlanOperator createFilterPlanOperator(PlanOperator<Item> op, Filter<Item> filter) {
        return new FilterPlanOperator(op, filter);
    }

    public static SelectionWrapper createSelectionWrapper(PlanOperator<LabelNode> scan, Integer selectionIndex) {
        return new SelectionWrapper(scan, selectionIndex);
    }

    public static Collector createCollector(List<PlanOperator<Item>> planOps) {
        return new Collector(planOps);
    }

    public static class ADJoin implements PlanOperator<Item> {
        private PlanOperator<Item> ancestorPlanOp;
        private PlanOperator<Item> decedentPlanOp;

        public ADJoin(PlanOperator<Item> ancestorPlanOp, PlanOperator<Item> decedentPlanOp) {
            this.ancestorPlanOp = ancestorPlanOp;
            this.decedentPlanOp = decedentPlanOp;
        }

        @Override
        public Iter<Item> iterator() {
            return new ADJoinIter(this.ancestorPlanOp.iterator(), this.decedentPlanOp.iterator());
        }

        static class ADJoinIter extends PCJoin.PCJoinIter {

            public ADJoinIter(Iter<Item> pIter, Iter<Item> cIter) {
                super(pIter, cIter);
            }

            protected void readBuffer() {
                if (this.buffer.size() > 0) return;

                while (this.pIter.hasNext() && this.buffer.size() == 0) {
                    while (cIter.hasNext() && cIter.read().getData().getLastVisit() < pIter.read().getData().getFirstVisit())
                        cIter.next();
                    while (cIter.hasNext() && cIter.read().getData().getFirstVisit() < pIter.read().getData().getFirstVisit())
                        cIter.next();
                    while (cIter.hasNext() && cIter.read().getData().getLastVisit() < pIter.read().getData().getLastVisit()) {
                        this.buffer.add(merge(pIter.read(), cIter.read()));
                        cIter.next();
                    }
                    this.pIter.next();
                }
            }
        }
    }

    public static class PCJoin implements PlanOperator<Item> {
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

    public static class KJoin implements PlanOperator<Item> {
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

    public static class Reorder implements PlanOperator<LabelNode> {
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

    public static class LabelNode2Item implements PlanOperator<Item> {
        private PlanOperator<LabelNode> planOp;

        public LabelNode2Item(PlanOperator<LabelNode> planOp) {
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

    public static class Item2LabelNode implements PlanOperator<LabelNode> {
        private PlanOperator<Item> planOp;

        public Item2LabelNode(PlanOperator<Item> planOp) {
            this.planOp = planOp;
        }

        @Override
        public Iter<LabelNode> iterator() {
            return new SelfIter(this.planOp.iterator());
        }

        public static class SelfIter implements Iter<LabelNode> {
            private Iter<Item> iter;
            public SelfIter(Iter<Item> iter) {
                this.iter = iter;
            }

            @Override
            public LabelNode read() {
                return this.iter.read().getData();
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
            public Iter<LabelNode> cloneCurrentIterator() {
                return new SelfIter(iter.cloneCurrentIterator());
            }
        }
    }

    public static class FilterPlanOperator implements PlanOperator<Item> {
        private PlanOperator<Item> op;
        private Filter<Item> filter;
        public FilterPlanOperator(PlanOperator<Item> op, Filter<Item> filter) {
            this.op = op;
            this.filter = filter;
        }
        
        @Override
        public Iter<Item> iterator() {
            return new FilterIter<>(this.op.iterator(), filter);
        }
    }

    public static class SelectionWrapper implements PlanOperator<Item> {
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

    public static class Collector implements PlanOperator<Item> {
        private List<PlanOperator<Item>> planOps;

        public Collector(List<PlanOperator<Item>> planOps) {
            this.planOps = planOps;
        }
        
        @Override public Iter<Item> iterator() {
            Iter<Item> ret = null;
            for (PlanOperator<Item> op: this.planOps) {
                if (ret == null) ret = op.iterator();
                else ret = new MIter<>(ret, op.iterator(), Item.comparator());
            }
            if (ret == null) return new EmptyIter<>();
            return ret;
        }
    }

    public static class Concat implements PlanOperator<LabelNode> {
        
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

        public static class ConcatNodeIterator implements Iter<LabelNode> {
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
}