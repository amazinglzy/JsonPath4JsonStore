package jp4js.query.merge;

import jp4js.query.PlanOperator;
import jp4js.index.node.LabelNode;
import jp4js.utils.iter.Iter;

import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class MergePlanOp {

    public static PCJoin createPCJoin(PlanOperator<Item> parentPlanOperator, PlanOperator<Item> childPlanOperator) {
        return new PCJoin(parentPlanOperator, childPlanOperator);
    }

    public static ADJoin createADJoin(PlanOperator<Item> parentPlanOperator, PlanOperator<Item> childPlanOperator) {
        return new ADJoin(parentPlanOperator, childPlanOperator);
    }

    public static LabelNode2Item createLabelNode2Item(PlanOperator<LabelNode> planOp) {
        return new LabelNode2Item(planOp);
    }

    public static Item2LabelNode createItem2LabelNode(PlanOperator<Item> planOp) {
        return new Item2LabelNode(planOp);
    }

    public static OrderedRootJoin createOrderedJoin(PlanOperator<Item> main, PlanOperator<Item> filter) {
        return new OrderedRootJoin(main, filter);
    }

    public static HashJoin createHashJoin(PlanOperator<Item> parentPlanOp, PlanOperator<Item> childPlanOp) {
        return new HashJoin(parentPlanOp, childPlanOp);
    }

    public static abstract class TreeMergeJoin implements Iter<Item> {
        protected Iter<Item> pIter;
        protected Iter<Item> cIter;
        protected List<Item> buffer;

        public TreeMergeJoin(final Iter<Item> pIter, final Iter<Item> cIter) {
            this.pIter = pIter;
            this.cIter = cIter;

            this.buffer = new LinkedList<Item>();
        }

        private TreeMergeJoin(final Iter<Item> pIter, final Iter<Item> cIter, final List<Item> buffer) {
            this.pIter = pIter;
            this.cIter = cIter;
            this.buffer = buffer;
        }

        protected Item join(final Item p, final Item q) {
            return new Item(p.getRoot(), q.getData());
        }
        
        protected abstract boolean condition(Item p, Item q);

        protected void readBuffer() {
            if (this.buffer.size() > 0) return;

            while (this.pIter.hasNext() && this.buffer.size() == 0) {
                while (cIter.hasNext() && cIter.read().compareTo(pIter.read()) <= 0)
                    cIter.next();
                final Iter<Item> curIter = cIter.cloneCurrentIterator();
                while (curIter.hasNext() && curIter.read().isDecendentOf( pIter.read() ) ) {
                    if (this.condition(curIter.read(), pIter.read()))
                        this.buffer.add(join(pIter.read(), curIter.read()));
                    curIter.next();
                }
                this.pIter.next();
            }
        }

        private boolean checkEmpty(final boolean throwable) {
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
        public abstract Iter<Item> cloneCurrentIterator(); 
    }

    public static class PCJoin implements PlanOperator<Item> {
        private final PlanOperator<Item> parentPlanOperator;
        private final PlanOperator<Item> childPlanOperator;

        public PCJoin(final PlanOperator<Item> parentPlanOperator, final PlanOperator<Item> childPlanOperator) {
            this.parentPlanOperator = parentPlanOperator;
            this.childPlanOperator = childPlanOperator;
        }

        @Override
        public Iter<Item> iterator() {
            return new SelfIter(this.parentPlanOperator.iterator(),
                    this.childPlanOperator.iterator());
        }

        static class SelfIter extends TreeMergeJoin {
            public SelfIter(final Iter<Item> pIter, final Iter<Item> cIter) {
                super(pIter, cIter);
            }

            private SelfIter(final Iter<Item> pIter, final Iter<Item> cIter, final List<Item> buffer) {
                super(pIter, cIter, buffer);
            }

            @Override
            public boolean condition(final Item p, final Item q) {
                return p.getRoot().getLevel() == q.getRoot().getLevel() + 1;
            }

            public Iter<Item> cloneCurrentIterator() {
                final List<Item> buffer = new LinkedList<Item>(this.buffer);
                return new SelfIter(this.pIter.cloneCurrentIterator(), this.cIter.cloneCurrentIterator(), buffer);
            }
        }
    }

    public static class ADJoin implements PlanOperator<Item> {
        private final PlanOperator<Item> parentPlanOperator;
        private final PlanOperator<Item> childPlanOperator;

        public ADJoin(final PlanOperator<Item> parentPlanOperator, final PlanOperator<Item> childPlanOperator) {
            this.parentPlanOperator = parentPlanOperator;
            this.childPlanOperator = childPlanOperator;
        }

        @Override
        public Iter<Item> iterator() {
            return new SelfIter(this.parentPlanOperator.iterator(),
                    this.childPlanOperator.iterator());
        }

        static class SelfIter extends TreeMergeJoin {
            public SelfIter(final Iter<Item> pIter, final Iter<Item> cIter) {
                super(pIter, cIter);
            }

            private SelfIter(final Iter<Item> pIter, final Iter<Item> cIter, final List<Item> buffer) {
                super(pIter, cIter, buffer);
            }

            @Override
            public boolean condition(final Item p, final Item q) {
                return p.getRoot().getLevel() > q.getRoot().getLevel();
            }

            public Iter<Item> cloneCurrentIterator() {
                final List<Item> buffer = new LinkedList<Item>(this.buffer);
                return new SelfIter(this.pIter.cloneCurrentIterator(), this.cIter.cloneCurrentIterator(), buffer);
            }
        }
    }

    public static class OrderedRootJoin implements PlanOperator<Item> {
        private PlanOperator<Item> main;
        private PlanOperator<Item> filter;
        public OrderedRootJoin(PlanOperator<Item> main, PlanOperator<Item> filter) {
            this.main = main;
            this.filter = filter;
        }
        
        @Override
        public Iter<Item> iterator() {
            return new SelfIter(main.iterator(), filter.iterator());
        }

        public static class SelfIter implements Iter<Item> {
            private Iter<Item> main;
            private Iter<Item> filter;
            private List<Item> output;
            private int cur;
            public SelfIter(Iter<Item> main, Iter<Item> filter) {
                this.main = main;
                this.filter = filter;
                this.output = new ArrayList<>();
                
                this.cur = 0;
                while (this.main.hasNext() && this.filter.hasNext()) {
                    if (this.main.read().getRoot().compareTo(this.filter.read().getRoot()) < 0) 
                        this.main.next();
                    else if (this.main.read().getRoot().compareTo(this.filter.read().getData()) > 0) 
                        this.filter.next();
                    else {
                        this.output.add(this.main.read());
                        this.main.next();
                    }
                }
            }

            public SelfIter(Iter<Item> main, Iter<Item> filter, List<Item> output, int cur) {
                this.main = main;
                this.filter = filter;
                this.output = output;
                this.cur = cur;
            }

            public Item read() {
                return this.output.get(this.cur);
            }

            public void next() {
                this.cur ++;
            }

            public boolean hasNext() {
                return this.cur < this.output.size();
            }

            public Iter<Item> cloneCurrentIterator() {
                return new SelfIter(main, filter, output, cur);
            }
        }
    }

    public static class HashJoin implements PlanOperator<Item> {
        private PlanOperator<Item> parentPlanOp;
        private PlanOperator<Item> childPlanOp;

        public HashJoin(PlanOperator<Item> parentPlanOp, PlanOperator<Item> childPlanOp) {
            this.parentPlanOp = parentPlanOp;
            this.childPlanOp = childPlanOp;
        }

        @Override
        public Iter<Item> iterator() {
            return new SelfIter(this.parentPlanOp.iterator(), this.childPlanOp.iterator());
        }

        public static class SelfIter implements Iter<Item> {
            private Iter<Item> parentIter;
            private Iter<Item> childIter;
            private List<Item> output;
            private int currentIndex;

            public SelfIter(Iter<Item> parentIter, Iter<Item> childIter) {
                this.parentIter = parentIter;
                this.childIter = childIter;
                Map<Long, List<LabelNode>> mapping = new HashMap<>();
                currentIndex = 0;


                while (this.parentIter.hasNext()) {
                    Item item = this.parentIter.read();
                    if (mapping.containsKey(item.getData().getFirstVisit())) {
                        mapping.get(item.getData().getFirstVisit()).add(item.getRoot());
                    }
                }

                while (this.childIter.hasNext()) {
                    Item item = this.childIter.read();
                    if (mapping.containsKey(item.getRoot().getFirstVisit())) {
                        for (LabelNode root: mapping.get(item.getRoot().getFirstVisit())) {
                            output.add(new Item(root, item.getData()));
                        }
                    }
                }
            }

            private SelfIter(
                    Iter<Item> parentIter, 
                    Iter<Item> childIter, 
                    List<Item> output,
                    int currentIndex) {
                this.parentIter = parentIter;
                this.childIter = childIter;
                this.output = output;
                this.currentIndex = currentIndex;
            }

            @Override
            public Item read() {
                return this.output.get(this.currentIndex);
            }

            @Override
            public void next() {
                this.currentIndex ++;
            }

            @Override
            public boolean hasNext() {
                return this.currentIndex < this.output.size();
            }

            @Override
            public Iter<Item> cloneCurrentIterator() {
                return new SelfIter(parentIter, childIter, output, currentIndex);
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
            return new SelfIter(this.planOp.iterator());
        }

        public static class SelfIter implements Iter<Item> {
            private Iter<LabelNode> iter;
            public SelfIter(Iter<LabelNode> iter) {
                this.iter = iter;
            }

            @Override
            public Item read() {
                return new Item(this.iter.read(), this.iter.read());
            }

            @Override
            public void next() {
                this.iter.next();
            }

            @Override
            public boolean hasNext() {
                return this.hasNext();
            }

            @Override
            public Iter<Item> cloneCurrentIterator() {
                return new SelfIter(this.iter.cloneCurrentIterator());
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
                return this.hasNext();
            }

            @Override
            public Iter<LabelNode> cloneCurrentIterator() {
                return new SelfIter(this.iter.cloneCurrentIterator());
            }
            
        }
    }

}