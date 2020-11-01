package jp4js.storage.region;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import jp4js.storage.region.node.RepeatableNode;
import jp4js.storage.region.node.SingularNode;
import jp4js.algebra.operator.structure.StructureSteps;
import jp4js.storage.region.node.IndexNode;


public class IndexScan {
    public static List<IndexNode> children(IndexContext indexContext, List<IndexNode> sortedNodes) {
        LinkedList<IndexNode> ret = new LinkedList<>();
        ArrayList<RegionIterator<IndexNode>> iters = new ArrayList<>();
        for (IndexNode node: sortedNodes) {
            while (node.level+1 >= iters.size()) iters.add(null);
            if (iters.get(node.level+1) == null) {
                iters.set(node.level+1, indexContext.new LevelAllIterator(node.level+1));
            }
            RegionIterator<IndexNode> iter = iters.get(node.level + 1);
            iter.seek(node.first_visit);
            while (iter.valid()) {
                IndexNode inode = iter.read();
                iter.next();
                if (inode.first_visit > node.last_visit) {
                    break;
                } else {
                    if (node.first_visit <= inode.first_visit && inode.last_visit <= node.last_visit) {
                        ret.add(inode);
                    }
                }
            }
        }
        return ret;
    }

    public static List<IndexNode> children(IndexContext indexContext, List<IndexNode> sortedNodes, StructureSteps.PropertyStep step) {
        ArrayList<RegionIterator<SingularNode>> iters = new ArrayList<>();
        return new LinkedList<>() {{
            for (IndexNode node: sortedNodes) {
                while (node.level+1 >= iters.size()) iters.add(null);
                if (iters.get(node.level+1) == null) {
                    iters.set(node.level+1, indexContext.new LevelTagIterator(node.level+1, step.fieldname));
                }
                RegionIterator<SingularNode> iter = iters.get(node.level + 1);
                iter.seek(node.first_visit);
                while (iter.valid()) {
                    IndexNode inode = iter.read();
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

    public static List<IndexNode> children(IndexContext indexContext, List<IndexNode> sortedNodes, StructureSteps.IndexStep step) {
        ArrayList<IndexContext.RepeatableIterator> iters = new ArrayList<>();
        return new LinkedList<>() {{
            for (IndexNode node: sortedNodes) {
                while (node.level+1 >= iters.size()) iters.add(null);
                if (iters.get(node.level+1) == null) {
                    iters.set(node.level+1, indexContext.new RepeatableIterator(node.level+1));
                }
                IndexContext.RepeatableIterator iter = iters.get(node.level+1);
                iter.seek(node.first_visit);
                iter.next(step.from);
                while (iter.valid()) {
                    RepeatableNode inode = iter.read();
                    iter.next();
                    if (inode.first_visit > node.last_visit) {
                        break;
                    }
                    if (inode.index >= step.to) {
                        break;
                    }
                    if (node.first_visit <= inode.first_visit && inode.last_visit <= node.last_visit) {
                        if (step.from <= inode.index && inode.index < step.to) {
                            add(inode);
                        }
                    }
                }
            }
        }};
    }

    public static List<IndexNode> descendents(IndexContext indexContext, List<IndexNode> sortedNodes, StructureSteps.PropertyStep step) {
        return new LinkedList<>() {{
            RegionIterator<SingularNode> iter = indexContext.new SingularIterator(step.fieldname);
            for (IndexNode node: sortedNodes) {
                if (iter.valid()) {
                    IndexNode inode = iter.read();
                    if (node.last_visit < inode.first_visit) {
                        continue;
                    }
                } else {
                    break;
                }

                iter.seek(node.first_visit);
                while (iter.valid()) {
                    IndexNode inode = iter.read();
                    iter.next();
                    if (inode.first_visit > node.last_visit) {
                        break;
                    } else {
                        if (node.first_visit <= inode.first_visit &&
                            inode.last_visit<= node.last_visit &&
                            node.level < inode.level) {
                            add(inode);
                        }
                    }
                }
            }
        }};
    }
}