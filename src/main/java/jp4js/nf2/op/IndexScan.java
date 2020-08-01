package jp4js.nf2.op;

import jp4js.nf2.op.structure.StructureSteps; 
import java.util.List;
import java.util.LinkedList;
import jp4js.nf2.DType;
import jp4js.storage.IndexContext;
import jp4js.storage.node.LabelNode;
import jp4js.utils.iter.Iter;


public class IndexScan {
    public static List<DType.Instance> open(
        IndexContext indexContext,
        List<LabelNode> sortedNodes,
        StructureSteps steps,
        int stepIndex) {
        if (stepIndex >= steps.size()) {
            return new LinkedList<DType.Instance>() {{
                for (LabelNode node: sortedNodes) {
                    add(node.getValue());
                }
            }};
        }
        
        List<LabelNode> nodes = descendents(indexContext, sortedNodes, steps.fieldname(stepIndex));
        return open(indexContext, nodes, steps, stepIndex + 1);
    }

    public static List<LabelNode> children(IndexContext indexContext, List<LabelNode> sortedNodes) {
        return new LinkedList<>() {{
            Iter<LabelNode> iter = indexContext.openAll();
            for (LabelNode node: sortedNodes) {
                while (iter.hasNext()) {
                    LabelNode inode = iter.read();
                    if (inode.getLastVisit() < node.getFirstVisit()) {
                        iter.next();
                    } else {
                        break;
                    }
                }
                while (iter.hasNext()) {
                    LabelNode inode = iter.read();
                    if (inode.getFirstVisit() > node.getFirstVisit()) {
                        break;
                    } else {
                        if (node.getFirstVisit() <= inode.getFirstVisit() && inode.getLastVisit() <= node.getLastVisit()) {
                            if (node.getLevel() + 1 == inode.getLevel()) {
                                add(inode);
                            }
                        }
                    }
                }
            }
        }};
    }

    public static List<LabelNode> children(IndexContext indexContext, List<LabelNode> sortedNodes, String fieldname) {
        return new LinkedList<>() {{
            Iter<LabelNode> iter = indexContext.openObject(new LinkedList<String>(){{add(fieldname); }});
            for (LabelNode node: sortedNodes) {
                while (iter.hasNext()) {
                    LabelNode inode = iter.read();
                    if (inode.getLastVisit() < node.getFirstVisit()) {
                        iter.next();
                    } else {
                        break;
                    }
                }
                while (iter.hasNext()) {
                    LabelNode inode = iter.read();
                    if (inode.getFirstVisit() > node.getFirstVisit()) {
                        break;
                    } else {
                        if (node.getFirstVisit() <= inode.getFirstVisit() && inode.getLastVisit() <= node.getLastVisit()) {
                            if (node.getLevel() + 1 == inode.getLevel()) {
                                add(inode);
                            }
                        }
                    }
                }
            }
        }};
    }

    public static List<LabelNode> descendents(IndexContext indexContext, List<LabelNode> sortedNodes, String fieldname) {
        return new LinkedList<>() {{
            Iter<LabelNode> iter = indexContext.openObject(new LinkedList<String>(){{add(fieldname); }});
            for (LabelNode node: sortedNodes) {
                while (iter.hasNext()) {
                    LabelNode inode = iter.read();
                    if (inode.getLastVisit() < node.getFirstVisit()) {
                        iter.next();
                    } else {
                        break;
                    }
                }
                while (iter.hasNext()) {
                    LabelNode inode = iter.read();
                    if (inode.getFirstVisit() > node.getFirstVisit()) {
                        break;
                    } else {
                        if (node.getFirstVisit() <= inode.getFirstVisit() && inode.getLastVisit() <= node.getLastVisit()) {
                            add(inode);
                        }
                    }
                }
            }
        }};
    }
}