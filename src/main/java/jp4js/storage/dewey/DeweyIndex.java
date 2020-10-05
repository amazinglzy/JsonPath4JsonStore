package jp4js.storage.dewey;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.TreeSet;

import jp4js.algebra.DType;
import jp4js.algebra.Scalar.DList;
import jp4js.algebra.Scalar.DMapping;
import jp4js.algebra.op.structure.StructureRelation;
import jp4js.algebra.op.structure.StructureSteps;

public class DeweyIndex {
    public static class TreeNode {
        public TreeNode holder;
        public HashMap<String, TreeNode> childs;
        public ArrayList<IndexNode> nodes;
    }

    private TreeNode root;

    public DeweyIndex() {
        this.root = new TreeNode();
    }

    public TreeSet<TreeNode> query(StructureSteps steps) {
        TreeSet<TreeNode> first = new TreeSet<TreeNode>();
        TreeSet<TreeNode> second = new TreeSet<TreeNode>();
        first.add(this.root);

        for (int i = 0; i < steps.size(); ++ i) {
            StructureSteps.Step step = steps.step(i);

            second.clear();
            if (step instanceof StructureSteps.PropertyStep) {
                for (TreeNode node: first) {
                    StructureSteps.PropertyStep pstep = (StructureSteps.PropertyStep)step;
                    if (step.rel == StructureRelation.AD) {
                        addDescendants(node, second, pstep.fieldname);
                    }
                }
            }

            if (step instanceof StructureSteps.IndexStep) {
                for (TreeNode node: first) {
                    if (node.holder != null) {
                        second.add(node.holder);
                    }
                }
            }

            
            TreeSet<TreeNode> tmp = first;
            first = second;
            second = tmp;
        }

        return first;
    }

    public void addDescendants(TreeNode current, TreeSet<TreeNode> nodes, String name) {
        if (current.childs.containsKey(name)) {
            nodes.add(current.childs.get(name));
        }

        if (current.holder != null) {
            addDescendants(current.holder, nodes, name);
        }
        
        for (TreeNode node: current.childs.values()) {
            addDescendants(node, nodes, name);
        }
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
}
