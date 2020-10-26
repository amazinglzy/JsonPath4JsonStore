package jp4js.storage.dewey;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import jp4js.algebra.DType;
import jp4js.algebra.Scalar.DList;
import jp4js.algebra.Scalar.DMapping;
import jp4js.algebra.op.structure.StructureList;
import jp4js.algebra.op.structure.StructureRelation;
import jp4js.algebra.op.structure.StructureSteps;
import jp4js.algebra.op.structure.StructureSteps.IndexStep;
import jp4js.algebra.op.structure.StructureSteps.PropertyStep;
import jp4js.algebra.op.structure.StructureSteps.Step;
import jp4js.algebra.tpl.DBody;
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

    public List<DBody> query(StructureList lst) {
        StructureSteps filter = new StructureSteps();
        NodeOrderedList data = query(this.root, lst, filter);
        return new LinkedList<>() {{
            for (IndexNode node: data) {
                add(node.data);
            }
        }};
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

    private NodeOrderedList query(TreeNode n, StructureList lst, StructureSteps filter) {
        if (lst == null) {
            return new NodeOrderedList(
                filter.size(),
                new StepsIterator(filter, n.nodes)
            );
        }

        if (lst.isSingular()) {
            return querySingular(n, lst, filter);
        } else {
            return queryRepeatable(n, (StructureList)lst, filter);
        }
    }

    private NodeOrderedList queryRepeatable(TreeNode n, StructureList lst, StructureSteps filter) {
        NodeOrderedList nodes = iterate(n, lst.steps().listIterator(), lst.elemType(), filter);
        // NodeOrderedList ret = new NodeOrderedList(filter.size(), nodes.iterator());
        NodeOrderedList ret = new NodeOrderedList(filter.size(), nodes);
        ret.shrink();
        return ret;
    }

    private NodeOrderedList querySingular(TreeNode n, StructureList lst, StructureSteps filter) {
        if (lst.size() == 0) {
            return new NodeOrderedList(
                filter.size(),
                new StepsIterator(filter, n.nodes)
            );
        }

        NodeOrderedList ret = new NodeOrderedList(filter.size());
        for (StructureList.StructureItem item: lst) {
            NodeOrderedList colnodes = iterate(n, item.steps.listIterator(), item.lst, filter);
            ret.crossProduct(colnodes);
        }
        return ret;
    }

    private NodeOrderedList iterate(TreeNode n, ListIterator<Step> iter, StructureList lst,
                                    StructureSteps filter) {
        if (iter.hasNext()) {
            NodeOrderedList ret = null;
            Step step = iter.next();
            if (step instanceof PropertyStep) {
                ret = iteratePropertyStep(n, (PropertyStep)step, iter, lst, filter);
            }

            if (step instanceof IndexStep) {
                ret = iterateIndexStep(n, (IndexStep)step, iter, lst, filter);
            }
            iter.previous();
            return ret;
        } else {
            return query(n, lst, filter);
        }
    }

    private NodeOrderedList iterateIndexStep(TreeNode n, IndexStep step, ListIterator<Step> iter,
                                             StructureList lst, StructureSteps filter) {
        NodeOrderedList ret = null;
        if (n.holder == null) {
            return ret;
        }
    
        filter.addStep(step);
        if (iter.hasNext()) {
            ret = iterate(n.holder, iter, lst, filter);
        } else {
            ret = query(n.holder, lst, filter);
        }
    
        filter.pop();
        return ret;
    }

    private NodeOrderedList iteratePropertyStep(TreeNode n, PropertyStep step, ListIterator<Step> iter,
                                                StructureList lst, StructureSteps filter) {
        if (step.rel == StructureRelation.PC) {
            return iteratePCPropertyStep(n, step, iter, lst, filter);
        } else {
            return iterateADPropertyStep(n, step, iter, lst, filter);
        }
    }

    private NodeOrderedList iteratePCPropertyStep(TreeNode n, PropertyStep step, ListIterator<Step> iter,
                                                  StructureList lst, StructureSteps filter) {
        NodeOrderedList ret = new NodeOrderedList(filter.size());
        if (iter.hasNext()) {
            if (step.fieldname != "*") {
                if (n.childs.containsKey(step.fieldname)) {
                    return iterate(n.childs.get(step.fieldname), iter, lst, filter);
                }
                return null;
            }
    
            for (TreeNode child: n.childs.values()) {
                NodeOrderedList childRet = iterate(child, iter, lst, filter);
                if (childRet != null) {
                    ret.addAll(childRet);
                }
            }
    
            if (n.holder != null) {
                filter.addStep(step);
                NodeOrderedList childRet = iterate(n.holder, iter, lst, filter);
                filter.pop();
    
                if (childRet != null) {
                    ret.addAll(childRet);
                }
            }
        } else {
            if (step.fieldname != "*") {
                if (n.childs.containsKey(step.fieldname)) {
                    return query(n.childs.get(step.fieldname), lst, filter);
                }
                return null;
            }
    
    
            for (TreeNode child: n.childs.values()) {
                NodeOrderedList childRet = query(child, lst, filter);
                if (childRet != null) {
                    ret.addAll(childRet);
                }
            }
    
            if (n.holder != null) {
                filter.addStep(step);
                NodeOrderedList childRet = query(n.holder, lst, filter);
                if (childRet != null) {
                    ret.addAll(childRet);
                }
                filter.pop();
            }
        }
        
        return ret;
    }

    private NodeOrderedList iterateADPropertyStep(TreeNode n, PropertyStep step, ListIterator<Step> iter,
                                                  StructureList lst, StructureSteps filter) {
        NodeOrderedList ret = new NodeOrderedList(filter.size());

        if (n.childs.containsKey(step.fieldname)) {
            NodeOrderedList childRet = null;
            if (iter.hasNext()) {
                childRet = iterate(n.childs.get(step.fieldname), iter, lst, filter);
            } else {
                childRet = query(n.childs.get(step.fieldname), lst, filter);
            }
            if (childRet != null) {
                ret.addAll(childRet);
            }
        }
    
        for (TreeNode node: n.childs.values()) {
            NodeOrderedList childRet = iterateADPropertyStep(node, step, iter, lst, filter);
            if (childRet != null) {
                ret.addAll(childRet);
            }
        }
    
        if (n.holder != null) {
            filter.addStep(StructureRelation.PC, "*");
            NodeOrderedList childRet = iterateADPropertyStep(n.holder, step, iter, lst, filter);
            if (childRet != null) {
                ret.addAll(childRet);
            }
            filter.pop();
        }
    
        return ret;
    }

    /*
    Private Helper Functions below
    */
    private DeweyIndex(TreeNode root) {
        this.root = root;
    }

    public static DeweyIndex build(DType.Instance ins) {
        TreeNode root = new TreeNode();
        build(ins, root, new LinkedList<>());
        root.build();
        return new DeweyIndex(root);
    }

    public static void build(DType.Instance ins, TreeNode c, LinkedList<Integer> index) {
        c.mutableNodes.add(new IndexNode(new LinkedList<>(index), ins));
        if (ins instanceof DList.Instance) {
            if (c.holder == null) {
                c.holder = new TreeNode();
            }

            DList.Instance lst = (DList.Instance)ins;
            for (int i = 0; i < lst.size(); ++ i) {
                index.add(i);
                build(lst.get(i), c.holder, index);
                index.removeLast();
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
        public LinkedList<IndexNode> mutableNodes;

        public TreeNode() {
            this.childs = new HashMap<>();
            this.mutableNodes = new LinkedList<>();
        }

        public void build() {
            this.nodes = new ArrayList<>(this.mutableNodes);
            for (TreeNode node: childs.values()) {
                node.build();
            }
            if (this.holder != null) {
                this.holder.build();
            }
        }

        @Override
        public int compareTo(TreeNode o) {
            return hashCode() - o.hashCode();
        }
    }

    private TreeNode root;
}
