package jp4js.storage.dewey;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import jp4js.algebra.DType;
import jp4js.algebra.Scalar.DList;
import jp4js.algebra.Scalar.DMapping;
import jp4js.algebra.op.structure.StructureRelation;
import jp4js.algebra.op.structure.StructureSteps;
import jp4js.algebra.op.structure.StructureSteps.IndexStep;
import jp4js.algebra.op.structure.StructureSteps.PropertyStep;
import jp4js.algebra.op.structure.StructureSteps.Step;
import jp4js.utils.Utils;
import jp4js.utils.iter.EmptyIter;
import jp4js.utils.iter.Iter;
import jp4js.utils.iter.MultiIter;

public class DeweyIndex {
    public DeweyIndex() {
        this.root = new TreeNode();
    }

    public Iter<IndexNode> query(StructureSteps steps) {
        StructureSteps filter = new StructureSteps();
        ListIterator<Step> iter = steps.listIterator();
        Iter<IndexNode> ret = query(iter, this.root, filter);
        if (ret == null) {
            ret = new EmptyIter<>();
        }
        return ret;
    }

    private Iter<IndexNode> query(ListIterator<Step> iter, TreeNode u, StructureSteps filter) {
        Iter<IndexNode> ret = null;
        Step step = iter.next();
        if (step instanceof PropertyStep) {
            PropertyStep ps = (PropertyStep)step;
            ret = queryPropertyStep(ps, iter, u, filter);
        } else if (step instanceof IndexStep) {
            IndexStep is = (IndexStep)step;
            ret = queryIndexStep(is, iter, u, filter);
        } else {
            Utils.CanNotBeHere("Unknown Step");
        }
        iter.previous();
        return ret;
    }

    private Iter<IndexNode> queryPropertyStep(PropertyStep step, ListIterator<Step> iter, TreeNode u, StructureSteps filter) {
        if (step.rel == StructureRelation.PC) {
            return queryPCProperty(step, iter, u, filter);
        } else {
            return queryADProperty(step, iter, u, filter);
        }
    }

    private Iter<IndexNode> queryPCProperty(PropertyStep step, ListIterator<Step> iter, TreeNode u, StructureSteps filter) {
        ArrayList<Iter<IndexNode>> iters = new ArrayList<Iter<IndexNode>>();
        if (iter.hasNext()) {
            if (step.fieldname != "*") {
                if (u.childs.containsKey(step.fieldname)) {
                    return query(iter, u.childs.get(step.fieldname), filter);
                }
                return null;
            }

            for (TreeNode child: u.childs.values()) {
                Iter<IndexNode> childIter = query(iter, child, filter);
                if (childIter != null) {
                    iters.add(childIter);
                }
            }

            if (u.holder != null) {
                filter.addStep(step);
                Iter<IndexNode> childIter = query(iter, u.holder, filter);
                filter.pop();

                if (childIter != null) {
                    iters.add(childIter);
                }
            }
        } else {
            if (step.fieldname != "*") {
                if (u.childs.containsKey(step.fieldname)) {
                    return new StepsIterator(filter, u.childs.get(step.fieldname).nodes);
                }
                return null;
            }


            for (TreeNode child: u.childs.values()) {
                iters.add(new StepsIterator(filter, child.nodes));
            }

            if (u.holder != null) {
                filter.addStep(step);
                iters.add(new StepsIterator(filter, u.holder.nodes));
                filter.pop();
            }
        }
        
        return new MultiIter<IndexNode>(iters, IndexNode.comparator(filter.size()));
    }

    private Iter<IndexNode> queryADProperty(PropertyStep step, ListIterator<Step> iter, TreeNode u, StructureSteps filter) {
        ArrayList<Iter<IndexNode>> iters = new ArrayList<Iter<IndexNode>>();

        if (u.childs.containsKey(step.fieldname)) {
            if (iter.hasNext()) {
                Iter<IndexNode> childIter = query(iter, u.childs.get(step.fieldname), filter);
                if (childIter != null) {
                    iters.add(childIter);
                }
            } else {
                iters.add(new StepsIterator(filter, u.childs.get(step.fieldname).nodes));
            }
        }

        for (TreeNode node: u.childs.values()) {
            Iter<IndexNode> childIter = queryPropertyStep(step, iter, node, filter);
            if (childIter != null) {
                iters.add(childIter);
            }
        }

        if (u.holder != null) {
            filter.addStep(StructureRelation.PC, "*");
            Iter<IndexNode> childIter = queryPropertyStep(step, iter, u.holder, filter);
            if (childIter != null) {
                iters.add(childIter);
            }
            filter.pop();
        }

        if (iters.size() == 0) {
            return null;
        }

        return new MultiIter<IndexNode>(iters, IndexNode.comparator(filter.size()));
    }

    private Iter<IndexNode> queryIndexStep(IndexStep step, ListIterator<Step> iter, TreeNode u, StructureSteps filter) {
        Iter<IndexNode> ret = null;
        if (u.holder == null) {
            return ret;
        }

        filter.addStep(step);
        if (iter.hasNext()) {
            ret = query(iter, u.holder, filter);
        } else {
            ret = new StepsIterator(filter, u.holder.nodes);
        }

        filter.pop();
        return ret;
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
