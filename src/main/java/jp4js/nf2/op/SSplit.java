package jp4js.nf2.op;

import java.util.LinkedList;
import java.util.List;

import jp4js.nf2.op.structure.StructureList;
import jp4js.nf2.op.structure.StructureRelation;
import jp4js.nf2.op.structure.StructureSteps;
import jp4js.storage.IndexContext;
import jp4js.storage.node.IndexNode;
import jp4js.nf2.tpl.DBody;
import jp4js.nf2.Match;
import jp4js.utils.nf2.Trans;
import jp4js.utils.Utils;
import jp4js.nf2.op.structure.RepeatableSL;
import jp4js.nf2.op.structure.SingularSL;
import jp4js.nf2.tpl.DRepeatableBody;
import jp4js.nf2.tpl.DSingularBody;


public class SSplit extends BaseSplit {
    private IndexContext indexContext;
    private StructureList lst;

    public SSplit(IndexContext indexContext, StructureList lst) {
        this.indexContext = indexContext;
        this.lst = lst;
    }

    public Match open() throws MatchException {
        DBody dBody = this.findMatch(
            this.indexContext.rootNode(), this.lst
        );
        return new Match(Trans.fromSL(this.lst), dBody);
    }

    public DBody findMatch(IndexNode u, StructureList lst) throws MatchException {
        if (lst instanceof RepeatableSL) 
            return findRepeatable(u, (RepeatableSL)lst);
        if (lst instanceof SingularSL)
            return findSingular(u, (SingularSL)lst);
        Utils.CanNotBeHere("unkown StructureList type");
        return null;
    }

    public DRepeatableBody findRepeatable(IndexNode u, RepeatableSL lst) throws MatchException {
        List<DBody> bodyData = new LinkedList<>();
        List<IndexNode> elems = new LinkedList<>(){{ add(u);}};
        if (lst.isNested()) {
            for (IndexNode childnode: elems) {
                bodyData.add(findRepeatable(childnode, lst.elemType()));
            }
        } else {
            for (IndexNode childnode: elems) {
                bodyData.addAll(find(childnode, lst));
            }
        }
        return new DRepeatableBody(bodyData);
    }

    public DSingularBody findSingular(IndexNode u, SingularSL lst) throws MatchException {
        List<DSingularBody> ret = find(u, lst);
        if (ret.size() > 0) return ret.get(0);
        return null;
    }

    public List<DSingularBody> find(IndexNode u, StructureList lst) throws MatchException {
        if (lst.size() == 0) {
            return new LinkedList<>() {{
                add(new DSingularBody(u.value));
            }};
        }

        List<DSingularBody> ret = new LinkedList<>();
        ret.add(new DSingularBody(lst.size()));
        int index = 0;
        for (StructureList.StructureItem item: lst) {
            List<IndexNode> candidates = iterateNode(
                new LinkedList<>(){{add(u);}}, item.steps, 0);
            List<DSingularBody> update = new LinkedList<>();
            for (DSingularBody row: ret) {
                for (IndexNode candidate: candidates) {
                    DBody cell = findMatch(candidate, item.lst);
                    if (cell == null) continue;
                    DSingularBody newRow = new DSingularBody(lst.size());
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
        return ret;
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
                            SSplit.this.indexContext, sortedNodes);
                    } else {
                        current = IndexScan.children(
                            SSplit.this.indexContext, sortedNodes, pstep);
                    }
                    return iterateNode(current, steps, currentStep + 1);
                }

                if (step instanceof StructureSteps.IndexStep) {
                    StructureSteps.IndexStep istep = (StructureSteps.IndexStep)step;
                    current = IndexScan.children(SSplit.this.indexContext, sortedNodes, istep);
                    return iterateNode(current, steps, currentStep + 1);
                }
                break;
            case AD:
                assert(step instanceof StructureSteps.PropertyStep);
                current = IndexScan.descendents(
                    SSplit.this.indexContext, sortedNodes, (StructureSteps.PropertyStep)step);
                return iterateNode(current, steps, currentStep + 1);
            default:
                Utils.CanNotBeHere("Unkown Structure Relation Type");
        }

        return null;
    }
}