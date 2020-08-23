package jp4js.nf2.op;

import jp4js.nf2.op.structure.StructureSteps; 
import java.util.List;
import java.util.LinkedList;
import jp4js.storage.IndexContext;
import jp4js.storage.node.LabelArray;
import jp4js.storage.node.LabelNode;
import jp4js.utils.iter.Iter;


public class IndexScan {
    // public static List<DType.Instance> open(
    //     IndexContext indexContext,
    //     List<LabelNode> sortedNodes,
    //     StructureSteps steps,
    //     int stepIndex) {
    //     if (stepIndex >= steps.size()) {
    //         return new LinkedList<DType.Instance>() {{
    //             for (LabelNode node: sortedNodes) {
    //                 add(node.value);
    //             }
    //         }};
    //     }
        
    //     List<LabelNode> nodes = descendents(indexContext, sortedNodes, steps.step(stepIndex));
    //     return open(indexContext, nodes, steps, stepIndex + 1);
    // }

    public static List<LabelNode> children(IndexContext indexContext, List<LabelNode> sortedNodes) {
        return new LinkedList<>() {{
            Iter<LabelNode> iter = indexContext.openAll();
            for (LabelNode node: sortedNodes) {
                iter.seek(node.first_visit);
                while (iter.hasNext()) {
                    LabelNode inode = iter.read();
                    iter.next();
                    if (inode.first_visit > node.last_visit) {
                        break;
                    } else {
                        if (node.first_visit <= inode.first_visit && inode.last_visit <= node.last_visit) {
                            if (node.level + 1 == inode.level) {
                                add(inode);
                            }
                        }
                    }
                }
            }
        }};
    }

    public static List<LabelNode> children(IndexContext indexContext, List<LabelNode> sortedNodes, StructureSteps.PropertyStep step) {
        return new LinkedList<>() {{
            Iter<LabelNode> iter = indexContext.openObject(new LinkedList<String>(){{add(step.fieldname); }});
            for (LabelNode node: sortedNodes) {
                iter.seek(node.first_visit);
                while (iter.hasNext()) {
                    LabelNode inode = iter.read();
                    iter.next();
                    if (inode.first_visit > node.last_visit) {
                        break;
                    } else {
                        if (node.first_visit <= inode.first_visit && inode.last_visit <= node.last_visit) {
                            if (node.level + 1 == inode.level) {
                                add(inode);
                            }
                        }
                    }
                }
            }
        }};
    }

    public static List<LabelNode> children(IndexContext indexContext, List<LabelNode> sortedNodes, StructureSteps.IndexStep step) {
        return new LinkedList<>() {{
            List<LabelNode> iter = children(indexContext, sortedNodes);
            for (LabelNode node: iter) {
                if (node instanceof LabelArray) {
                    LabelArray anode = (LabelArray)node;
                    if (step.from <= anode.index && anode.index < step.to) {
                        add(node);
                    }
                }
            }
        }};
    }

    public static List<LabelNode> descendents(IndexContext indexContext, List<LabelNode> sortedNodes, StructureSteps.PropertyStep step) {
        return new LinkedList<>() {{
            Iter<LabelNode> iter = indexContext.openObject(new LinkedList<String>(){{add(step.fieldname); }});
            for (LabelNode node: sortedNodes) {
                iter.seek(node.first_visit);
                while (iter.hasNext()) {
                    LabelNode inode = iter.read();
                    iter.next();
                    if (inode.first_visit > node.last_visit) {
                        break;
                    } else {
                        if (node.first_visit <= inode.first_visit && inode.last_visit<= node.last_visit) {
                            add(inode);
                        }
                    }
                }
            }
        }};
    }
}