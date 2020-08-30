package jp4js.storage;

import jp4js.nf2.op.structure.StructureSteps;
import jp4js.storage.node.IndexNode;
import jp4js.storage.node.SingularNode;
import jp4js.storage.node.RepeatableNode;
import jp4js.utils.iter.Iter;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.TreeMap;

public class IndexContext {
    private IndexNode rootNode;
    private ArrayList<SingularNode> allSingularNodes;
    private ArrayList<RepeatableNode> allRepeatableNodes;
    private ArrayList<SingularNode> singularNodes;
    private ArrayList<RepeatableNode> repeatableNodes;

    public IndexContext(IndexNode rootNode, 
            ArrayList<SingularNode> allSingularNodes,
            ArrayList<RepeatableNode> allRepeatableNodes,
            TreeMap<String, LinkedList<SingularNode>> objectPartitions,
            TreeMap<String, TreeMap<Long, LinkedList<RepeatableNode>>> arrayPartitions) {
        this.rootNode = rootNode;
        this.allSingularNodes = allSingularNodes;
        this.allRepeatableNodes = allRepeatableNodes;
        this.singularNodes = new ArrayList<>();
        this.repeatableNodes = new ArrayList<>();
        for (String partition: objectPartitions.keySet()) {
            this.singularNodes.addAll(objectPartitions.get(partition));
        }
        for (String partition: arrayPartitions.keySet()) {
            for (LinkedList<RepeatableNode> nodes: arrayPartitions.get(partition).values()) {
                this.repeatableNodes.addAll(nodes);
            }
        }
    }

    public IndexNode rootNode() {
        return this.rootNode;
    }

    public abstract class RangeIter<E extends IndexNode> implements Iter<E> {
        protected ArrayList<E> nodes;
        protected int rangeL;
        protected int rangeR;
        protected int idx;

        public RangeIter(ArrayList<E> nodes) {
            this.nodes = nodes;
        }

        public E read() {
            return this.nodes.get(this.idx);
        }

        public void next() {
            this.idx ++;
        }

        public boolean valid() {
            return this.idx < this.rangeR;
        }

        public void seek(long visit) {
            int left = this.rangeL - 1;
            int right = this.rangeR;
            while (right - left > 1) {
                int mid = (left + right) / 2;
                if (this.nodes.get(mid).first_visit < visit) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            this.idx = right;
        }
    }

    public class SingularIterator extends RangeIter<SingularNode> {
        private String name;

        public SingularIterator() {
            super(IndexContext.this.allSingularNodes);
            this.name = "*";
            this.rangeL = 0;
            this.rangeR = IndexContext.this.allSingularNodes.size();
            this.idx = 0;
        }

        public SingularIterator(String name) {
            super(IndexContext.this.singularNodes);
            this.name = name;

            int left, right;

            left = -1;
            right = IndexContext.this.singularNodes.size();
            while (right - left > 1) {
                int mid = (left + right) / 2;
                if (IndexContext.this.singularNodes.get(mid).name.compareTo(this.name) < 0) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            this.rangeL = right;

            left = -1;
            right = IndexContext.this.singularNodes.size();
            while (right - left > 1) {
                int mid = (left + right) / 2;
                if (IndexContext.this.singularNodes.get(mid).name.compareTo(this.name) > 0) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            this.rangeR = right;

            this.idx = this.rangeL;
        }
    }

    public class RepeatableIterator extends RangeIter<RepeatableNode> {
        private String name;
        private StructureSteps.IndexStep step;

        public RepeatableIterator() {
            super(IndexContext.this.allRepeatableNodes);
            this.name = "*";
            this.rangeL = 0;
            this.rangeR = IndexContext.this.allRepeatableNodes.size();
            this.idx = 0;
        }

        public RepeatableIterator(String name, StructureSteps.IndexStep step) {
            super(IndexContext.this.repeatableNodes);
            this.name = name;
            this.step = step;

            int left, right;

            left = -1;
            right = IndexContext.this.repeatableNodes.size();
            while (right - left > 1) {
                int mid = (left + right) / 2;
                int diff = IndexContext.this.repeatableNodes.get(mid).name.compareTo(this.name);
                if (diff < 0) {
                    left = mid;
                } else if (diff == 0) {
                    if (IndexContext.this.repeatableNodes.get(mid).index < this.step.from) {
                        left = mid;
                    } else {
                        right = mid;
                    }
                } else {
                    right = mid;
                }
            }
            this.rangeL = right;

            left = -1;
            right = IndexContext.this.repeatableNodes.size();
            while (right - left > 1) {
                int mid = (left + right) / 2;
                int diff = IndexContext.this.repeatableNodes.get(mid).name.compareTo(this.name);
                if (diff > 0) {
                    right = mid;
                } else if (diff == 0) {
                    if (IndexContext.this.repeatableNodes.get(mid).index >= this.step.to) {
                        right = mid;
                    } else {
                        left = mid;
                    }
                } else {
                    left = mid;
                }
            }
            this.rangeR = right;

            this.idx = this.rangeL;
        }
    }
}
