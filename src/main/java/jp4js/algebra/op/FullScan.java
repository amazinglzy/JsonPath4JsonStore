package jp4js.algebra.op;

import java.util.LinkedList;
import java.util.List;

import jp4js.algebra.DType;
import jp4js.algebra.Match;
import jp4js.algebra.Scalar.DList;
import jp4js.algebra.Scalar.DMapping;
import jp4js.algebra.op.structure.StructureList;
import jp4js.algebra.op.structure.StructureRelation;
import jp4js.algebra.op.structure.StructureSteps;
import jp4js.algebra.tpl.AtomicValue;
import jp4js.algebra.tpl.DBody;
import jp4js.algebra.tpl.ListTuple;
import jp4js.algebra.tpl.Tuple;
import jp4js.utils.algebra.Trans;

public class FullScan extends BaseScan {

    private DType.Instance data;
    private StructureList lst;
    public FullScan(DType.Instance data, StructureList lst) {
        this.data = data;
        this.lst = lst;
    }

    public Match open() throws MatchException {
        List<DBody> dBody = this.findMatch(data, lst);
        return new Match(Trans.fromSL(this.lst), dBody);
    }

    public List<DBody> findMatch(DType.Instance ins, StructureList lst) throws MatchException  {
        if (lst == null) {
            return new LinkedList<>() {{ add(new AtomicValue(ins.type(), ins));}};
        }

        if (lst.isSingular()) {
            return find(ins, lst);
        } else {
            return findRepeatable(ins, lst);
        } 
    }

    public List<DBody> findRepeatable(DType.Instance ins, StructureList lst) throws MatchException {
        List<DBody> bodyData = new LinkedList<>();
        List<DType.Instance> elems = iterateInstance(ins, lst.steps(), 0);
        for (DType.Instance subins: elems) {
            List<DBody> item = findMatch(subins, lst.elemType());
            if (item != null) {
                bodyData.addAll(item);
            }
        }
        return new LinkedList<>(){{ add(new ListTuple(bodyData)); }};
    }

    public List<DBody> find(DType.Instance ins, StructureList lst) throws MatchException {
        if (lst.size() == 0) {
            return new LinkedList<DBody>() {{
                add(new AtomicValue(ins.type(), ins));
            }};
        }
        
        List<Tuple> ret = new LinkedList<>();
        ret.add(new Tuple(lst.size()));
        int index = 0;
        for (StructureList.StructureItem item: lst) {
            List<DType.Instance> candidates = iterateInstance(ins, item.steps, 0);
            List<Tuple> update = new LinkedList<>();
            List<DBody> childRet = new LinkedList<>();
            for (DType.Instance candidate: candidates) {
                List<DBody> cells = findMatch(candidate, item.lst);
                childRet.addAll(cells);
            }
            for (Tuple row : ret) {
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