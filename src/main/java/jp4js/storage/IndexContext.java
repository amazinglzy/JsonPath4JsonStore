package jp4js.storage;

import jp4js.storage.node.IndexNode;
import jp4js.storage.node.SingularNode;
import jp4js.storage.node.RepeatableNode;
import jp4js.utils.iter.Iter;

import java.util.ArrayList;
import java.util.HashMap;

public class IndexContext {
    private IndexNode rootNode;
    private ArrayList<IndexNode> allNodes;
    private HashMap<String, ArrayList<SingularNode>> singularNodes;
    private ArrayList<ArrayList<SingularNode>> levelSingularNodes;
    private ArrayList<ArrayList<RepeatableNode>> levelRepeatableNodes;

    public IndexContext(IndexNode rootNode, 
            ArrayList<IndexNode> allNodes,
            HashMap<String, ArrayList<SingularNode>> singularNodes,
            ArrayList<ArrayList<SingularNode>> levelSingularNodes,
            ArrayList<ArrayList<RepeatableNode>> levelRepeatableNodes) {
        this.rootNode = rootNode;
        this.allNodes = allNodes;
        this.singularNodes = singularNodes;
        this.levelSingularNodes = levelSingularNodes;
        this.levelRepeatableNodes = levelRepeatableNodes;
    }

    public IndexNode rootNode() {
        return this.rootNode;
    }

    public abstract class RangeIter<E extends IndexNode> implements Iter<E> {
        protected ArrayList<E> nodes;
        protected int rangeL;
        protected int rangeR;
        protected int idx;

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

    public class AllIterator extends RangeIter<IndexNode> {
        public AllIterator() {
            this.nodes = IndexContext.this.allNodes;
            this.rangeL = 0;
            this.rangeR = this.nodes.size();
            this.idx = 0;
        }
    }

    public class SingularIterator extends RangeIter<SingularNode> {
        public SingularIterator(String name) {
            ArrayList<SingularNode> nodes;
            if (IndexContext.this.singularNodes.containsKey(name)) {
                nodes = IndexContext.this.singularNodes.get(name);
            } else {
                nodes = new ArrayList<>();
            }
            this.rangeL = 0;
            this.rangeR = nodes.size();
            this.idx = 0;
            this.nodes = nodes;
        }

        public SingularIterator(int level) {
            ArrayList<SingularNode> nodes;
            if (level >= levelSingularNodes.size()) {
                nodes = new ArrayList<>();
            } else {
                nodes = levelSingularNodes.get(level);
            }
            this.rangeL = 0;
            this.rangeR = nodes.size();
            this.idx = 0;
            this.nodes = nodes;
        }
    }

    public class RepeatableIterator extends RangeIter<RepeatableNode> {
        public RepeatableIterator(int level) {
            ArrayList<RepeatableNode> nodes;
            if (level >= levelRepeatableNodes.size()) {
                nodes = new ArrayList<>();
            } else {
                nodes = levelRepeatableNodes.get(level);
            }
            this.rangeL = 0;
            this.rangeR = nodes.size();
            this.idx = 0;
            this.nodes = nodes;
        }
    }
}
