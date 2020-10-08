package jp4js.storage.dewey;

import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.TreeSet;

import jp4js.algebra.DType;
import jp4js.algebra.Scalar.DList;
import jp4js.algebra.Scalar.DMapping;
import jp4js.algebra.op.structure.StructureRelation;
import jp4js.algebra.op.structure.StructureSteps;
import jp4js.algebra.op.structure.StructureSteps.IndexStep;
import jp4js.algebra.op.structure.StructureSteps.PropertyStep;
import jp4js.algebra.op.structure.StructureSteps.Step;
import jp4js.storage.region.IndexScan;
import jp4js.utils.Utils;
import jp4js.utils.iter.ArrayIter;
import jp4js.utils.iter.EmptyIter;
import jp4js.utils.iter.Iter;
import jp4js.utils.iter.MultiIter;

public class DeweyIndex {
    public DeweyIndex() {
        this.root = new TreeNode();
    }

    public Iter<IndexNode> query(ListIterator<Step> iter, TreeNode u, int level) {
        Step step = iter.next();
        if (step instanceof PropertyStep) {
            PropertyStep ps = (PropertyStep)step;
            return query(ps, iter, u, level);
        } else if (step instanceof IndexStep) {
            IndexStep is = (IndexStep)step;
            return query(is, iter, u, level);
        }
        Utils.CanNotBeHere("Unknown Step");
        return null;
    }

    public Iter<IndexNode> query(PropertyStep step, ListIterator<Step> iter, TreeNode u, int level) {
        if (step.rel == StructureRelation.PC) {
            if (step.fieldname == "*") {
                ArrayList<Iter<IndexNode>> iters = new ArrayList<Iter<IndexNode>>() {{
                    for (TreeNode child: u.childs.values()) {
                        add(new ArrayIter<>(child.nodes));
                    }
                    if (u.holder != null) {
                        add(new ArrayIter<>(u.holder.nodes));
                    }
                }};
                return new MultiIter<IndexNode>(iters, IndexNode.comparator(level));
            } else {
                if (u.childs.containsKey(step.fieldname)) {
                    return new ArrayIter<>(u.childs.get(step.fieldname).nodes);
                }
                return null;
            }
        } else {
            ArrayList<Iter<IndexNode>> iters = new ArrayList<Iter<IndexNode>>() {{
                if (u.childs.containsKey(step.fieldname)) {
                    add(new ArrayIter<>(u.childs.get(step.fieldname).nodes));
                }

                for (TreeNode node: u.childs.values()) {
                    Iter<IndexNode> res = query(step, iter, node, level);
                    if (res != null) {
                        add(res);
                    }
                }

                if (u.holder != null) {
                    Iter<IndexNode> res = query(step, iter, u.holder, level+1);
                    if (res != null) {
                        add(res);
                    }
                }
            }};

            if (iters.size() == 0) {
                return null;
            }

            return new MultiIter<IndexNode>(iters, IndexNode.comparator(level));
        }
    }

    public Iter<IndexNode> query(IndexStep step, ListIterator<Step> iter, TreeNode u, int level) {
        return null;
    }

    private DeweyIndex(TreeNode root) {
        this.root = root;
    }

    public static DeweyIndex build(DType.Instance ins) {
        TreeNode root = new TreeNode();
        build(ins, root, new LinkedList<>());
        return new DeweyIndex(root);
    }

    public static void build(DType.Instance ins, TreeNode c, LinkedList<Integer> index) {
        c.nodes.add(new IndexNode(new LinkedList<>(index), ins));
        if (ins instanceof DList.Instance) {
            if (c.holder == null) {
                c.holder = new TreeNode();
            }

            DList.Instance lst = (DList.Instance)ins;
            for (int i = 0; i < lst.size(); ++ i) {
                index.add(i);
                build(lst.get(i), c.holder, index);
                index.pop();
            }

            return;
        }

        if (ins instanceof DMapping.Instance) {
            DMapping.Instance mapping = (DMapping.Instance)ins;
            for (String property: mapping) {
                if (!c.childs.containsKey(property)) {
                    c.childs.put(property, new TreeNode());
                }

                build(mapping.get(property), c.childs.get(property), index);
            }
        }
    }

    public static class TreeNode implements Comparable<TreeNode> {
        public TreeNode holder;
        public HashMap<String, TreeNode> childs;
        public ArrayList<IndexNode> nodes;

        public TreeNode() {
            this.childs = new HashMap<>();
            this.nodes = new ArrayList<>();
        }

        @Override
        public int compareTo(TreeNode o) {
            return hashCode() - o.hashCode();
        }
    }

    private TreeNode root;
}
