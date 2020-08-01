package jp4js.nf2.op;

import java.util.LinkedList;
import java.util.List;

import jp4js.nf2.op.structure.StructureList;
import jp4js.nf2.op.structure.StructureRelation;
import jp4js.nf2.op.structure.StructureSteps;
import jp4js.storage.IndexContext;
import jp4js.storage.node.LabelNode;
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

    public DBody findMatch(LabelNode u, StructureList lst) throws MatchException {
        if (lst instanceof RepeatableSL) 
            return findRepeatable(u, (RepeatableSL)lst);
        if (lst instanceof SingularSL)
            return findSingular(u, (SingularSL)lst);
        Utils.CanNotBeHere("unkown StructureList type");
        return null;
    }

    public DRepeatableBody findRepeatable(LabelNode u, RepeatableSL lst) throws MatchException {
        List<DBody> bodyData = new LinkedList<>();
        List<LabelNode> elems = IndexScan.children(
            this.indexContext, new LinkedList<>(){{ add(u); }});
        if (lst.isNested()) {
            for (LabelNode childnode: elems) {
                bodyData.add(findRepeatable(childnode, lst.elemType()));
            }
        } else {
            for (LabelNode childnode: elems) {
                bodyData.addAll(find(childnode, lst));
            }
        }
        return null;
    }

    public DSingularBody findSingular(LabelNode u, SingularSL lst) throws MatchException {
        List<DSingularBody> ret = find(u, lst);
        if (ret.size() > 0) return ret.get(0);
        return null;
    }

    public List<DSingularBody> find(LabelNode u, StructureList lst) throws MatchException {
        if (lst.size() == 0) {
            return new LinkedList<>() {{
                add(new DSingularBody(u.getValue()));
            }};
        }

        List<DSingularBody> ret = new LinkedList<>();
        ret.add(new DSingularBody(lst.size()));
        int index = 0;
        for (StructureList.StructureItem item: lst) {
            List<LabelNode> candidates = iterateNode(
                new LinkedList<>(){{add(u);}}, item.steps, 0);
            List<DSingularBody> update = new LinkedList<>();
            for (DSingularBody row: ret) {
                for (LabelNode candidate: candidates) {
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

    List<LabelNode> iterateNode(
        List<LabelNode> sortedNodes, StructureSteps steps, int currentStep) {
        if (currentStep >= steps.size()) {
            return sortedNodes;
        }

        StructureRelation rel = steps.type(currentStep);
        String fieldname = steps.fieldname(currentStep);

        List<LabelNode> current;
        switch(rel) {
            case PC:
                current = IndexScan.children(
                    SSplit.this.indexContext, sortedNodes, fieldname);
                return iterateNode(current, steps, currentStep + 1);
            case AD:
                current = IndexScan.descendents(
                    SSplit.this.indexContext, sortedNodes, fieldname);
                return iterateNode(current, steps, currentStep + 1);
            default:
                Utils.CanNotBeHere("Unkown Structure Relation Type");
        }

        return null;
    }
}