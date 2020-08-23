package jp4js.nf2.op;

import java.util.LinkedList;
import java.util.List;

import jp4js.nf2.DType;
import jp4js.nf2.Match;
import jp4js.nf2.Scalar.DList;
import jp4js.nf2.Scalar.DMapping;
import jp4js.nf2.op.structure.RepeatableSL;
import jp4js.nf2.op.structure.SingularSL;
import jp4js.nf2.op.structure.StructureList;
import jp4js.nf2.op.structure.StructureRelation;
import jp4js.nf2.op.structure.StructureSteps;
import jp4js.nf2.tpl.DBody;
import jp4js.nf2.tpl.DRepeatableBody;
import jp4js.nf2.tpl.DSingularBody;
import jp4js.utils.Utils;
import jp4js.utils.nf2.Trans;

public class Split extends BaseSplit {

    private DType.Instance data;
    private StructureList lst;
    public Split(DType.Instance data, StructureList lst) {
        this.data = data;
        this.lst = lst;
    }

    public Match open() throws MatchException {
        DBody dBody = this.findMatch(data, lst);
        return new Match(Trans.fromSL(this.lst), dBody);
    }

    public DBody findMatch(DType.Instance ins, StructureList lst) throws MatchException  {
        if (lst instanceof RepeatableSL) 
            return findRepeatable(ins, (RepeatableSL)lst);
        if (lst instanceof SingularSL)
            return findSingular(ins, (SingularSL)lst);
        Utils.CanNotBeHere("unkown StructureList type");
        return null;
    }

    public DRepeatableBody findRepeatable(DType.Instance ins, RepeatableSL lst) throws MatchException {
        List<DBody> bodyData = new LinkedList<>();
        List<DType.Instance> elems = new LinkedList<DType.Instance>() {{ add(ins); }};
        if (lst.isNested()) {
            for (DType.Instance subIns: elems) 
                bodyData.add(findRepeatable(subIns, lst.elemType()));
        } else {
            for (DType.Instance subIns: elems)
                bodyData.addAll(find(subIns, lst));
        }
        return new DRepeatableBody(bodyData);
    }

    public DSingularBody findSingular(DType.Instance ins, SingularSL lst) throws MatchException {
        List<DSingularBody> ret = find(ins, lst);
        if (ret.size() > 0) return ret.get(0);
        return null;
    }

    public List<DSingularBody> find(DType.Instance ins, StructureList lst) throws MatchException {
        if (lst.size() == 0) {
            return new LinkedList<DSingularBody>() {{
                add(new DSingularBody(ins));
            }};
        }
        
        List<DSingularBody> ret = new LinkedList<>();
        ret.add(new DSingularBody(lst.size()));
        int index = 0;
        for (StructureList.StructureItem item: lst) {
            List<DType.Instance> candidates = iterateInstance(ins, item.steps, 0);
            List<DSingularBody> update = new LinkedList<>();
            for (DSingularBody row: ret) {
                for (DType.Instance candidate: candidates) {
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

    public List<DType.Instance> iterateInstance(DType.Instance ins, StructureSteps steps, int currentStep) {
        if (currentStep >= steps.size()) {
            return new LinkedList<>() {{
                add(ins);
            }};
        }

        StructureSteps.Step step = steps.step(currentStep);
        StructureRelation rel = step.rel;

        return new LinkedList<>() {{
            if (ins instanceof DMapping.Instance) {
                DMapping.Instance mapping = (DMapping.Instance)ins;
                if (step instanceof StructureSteps.PropertyStep) {
                    StructureSteps.PropertyStep pstep = (StructureSteps.PropertyStep)step;
                    String fieldname = pstep.fieldname;
                    if (fieldname == "*") {
                        for (String field: mapping) {
                            addAll(iterateInstance(mapping.get(field), steps, currentStep+1));
                        }
                    } else {
                        if (mapping.contains(fieldname)) {
                            addAll(iterateInstance(mapping.get(fieldname), steps, currentStep+1));
                        }
                    }
                    if (rel == StructureRelation.AD) {
                        for (String field: mapping) {
                            addAll(iterateInstance(mapping.get(field), steps, currentStep));
                        }
                    }
                }
            }

            if (ins instanceof DList.Instance) {
                DList.Instance lstIns = (DList.Instance)ins;
                if (step instanceof StructureSteps.PropertyStep) {
                    StructureSteps.PropertyStep pstep = (StructureSteps.PropertyStep)step;
                    String fieldname = pstep.fieldname;
                    if (fieldname == "*") {
                        for (DType.Instance insElem: lstIns) {
                            addAll(iterateInstance(insElem, steps, currentStep+1));
                        }
                    }

                    if (rel == StructureRelation.AD) {
                        for (DType.Instance insElem: lstIns) {
                            addAll(iterateInstance(insElem, steps, currentStep));
                        }
                    }
                } 
                if (step instanceof StructureSteps.IndexStep) {
                    StructureSteps.IndexStep istep = (StructureSteps.IndexStep)step;
                    for (int i = istep.from; i < istep.to && i < lstIns.size(); i++) {
                        addAll(iterateInstance(lstIns.get(i), steps, currentStep + 1));
                    }
                }
            }
        }};
    }
}