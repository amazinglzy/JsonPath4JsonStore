package jp4js.storage.region;

import java.util.LinkedList;
import java.util.List;

import jp4js.algebra.op.structure.StructureList;
import jp4js.algebra.op.structure.StructureRelation;
import jp4js.algebra.op.structure.StructureSteps;
import jp4js.storage.region.node.IndexNode;
import jp4js.algebra.tpl.AtomicValue;
import jp4js.algebra.tpl.DBody;
import jp4js.algebra.TplValidator;
import jp4js.utils.algebra.Trans;
import jp4js.utils.Utils;
import jp4js.algebra.op.BaseScan;
import jp4js.algebra.tpl.ListTuple;
import jp4js.algebra.tpl.Tuple;


public class RegionScan extends BaseScan {
    private IndexContext indexContext;
    private StructureList lst;

    public RegionScan(IndexContext indexContext, StructureList lst) {
        this.indexContext = indexContext;
        this.lst = lst;
    }

    public TplValidator open() throws MatchException {
        List<DBody> dBody = this.findMatch(
            this.indexContext.rootNode(), this.lst
        );
        return new TplValidator(Trans.fromSL(this.lst), dBody);
    }

    public List<DBody> findMatch(IndexNode u, StructureList lst) throws MatchException {
        if (lst == null) {
            return new LinkedList<>() {{ add(new AtomicValue(u.value.type(), u.value));}};
        }
        if (lst.isSingular()) {
            return find(u, lst);
        } else {
            return findRepeatable(u, (StructureList)lst);
        }
    }

    public List<DBody> findRepeatable(IndexNode u, StructureList lst) throws MatchException {
        List<DBody> bodyData = new LinkedList<>();
        List<IndexNode> elems = iterateNode(
            new LinkedList<>(){{add(u);}}, 
            lst.steps(), 0);
        for (IndexNode node: elems) {
            List<DBody> item = findMatch(node, lst.elemType());
            if (item != null) {
                bodyData.addAll(item);
            }
        }
        return new LinkedList<>(){{ add(new ListTuple(bodyData)); }};
    }

    public List<DBody> find(IndexNode u, StructureList lst) throws MatchException {
        if (lst.size() == 0) {
            return new LinkedList<>() {{
                add(new AtomicValue(u.value.type(), u.value));
            }};
        }

        List<Tuple> ret = new LinkedList<>();
        ret.add(new Tuple(lst.size()));
        int index = 0;
        for (StructureList.StructureItem item: lst) {
            List<IndexNode> candidates = iterateNode(
                new LinkedList<>(){{add(u);}}, item.steps, 0);
            List<Tuple> update = new LinkedList<>();
            List<DBody> childRet = new LinkedList<>();
            for (IndexNode candidate: candidates) {
                List<DBody> cells = findMatch(candidate, item.lst);
                childRet.addAll(cells);
            }
            for (Tuple row: ret) {
                for (DBody cell: childRet) {
                    Tuple newRow = new Tuple(lst.size());
                    for (int i = 0; i < index; i++) {
                        newRow.put(i, row.get(i));
                    }
                    newRow.put(index, cell);
                    update.add(newRow);
                }
            }
            ret = update;
            index ++;
        }

        LinkedList<DBody> fret = new LinkedList<>();
        fret.addAll(ret);
        return fret;
    }

    List<IndexNode> iterateNode(
        List<IndexNode> sortedNodes, StructureSteps steps, int currentStep) {
        if (currentStep >= steps.size()) {
            return sortedNodes;
        }

        StructureSteps.Step step = steps.step(currentStep);
        StructureRelation rel = step.rel;

        List<IndexNode> current;
        switch(rel) {
            case PC:
                if (step instanceof StructureSteps.PropertyStep) {
                    StructureSteps.PropertyStep pstep = (StructureSteps.PropertyStep)step;
                    if (pstep.fieldname == "*") {
                        current = IndexScan.children(
                            RegionScan.this.indexContext, sortedNodes);
                    } else {
                        current = IndexScan.children(
                            RegionScan.this.indexContext, sortedNodes, pstep);
                    }
                    return iterateNode(current, steps, currentStep + 1);
                }

                if (step instanceof StructureSteps.IndexStep) {
                    StructureSteps.IndexStep istep = (StructureSteps.IndexStep)step;
                    current = IndexScan.children(RegionScan.this.indexContext, sortedNodes, istep);
                    return iterateNode(current, steps, currentStep + 1);
                }
                break;
            case AD:
                assert(step instanceof StructureSteps.PropertyStep);
                current = IndexScan.descendents(
                    RegionScan.this.indexContext, sortedNodes, (StructureSteps.PropertyStep)step);
                return iterateNode(current, steps, currentStep + 1);
            default:
                Utils.CanNotBeHere("Unkown Structure Relation Type");
        }

        return null;
    }
}