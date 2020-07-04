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
import jp4js.nf2.tpl.DBody;
import jp4js.nf2.tpl.DRepeatableBody;
import jp4js.nf2.tpl.DSingularBody;
import jp4js.utils.Utils;
import jp4js.utils.nf2.Trans;

public class Split {
    public class MatchException extends Exception {
        public MatchException() {
            super();
        }

        public MatchException(String msg) {
            super(msg);
        }
    }

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
        List<DType.Instance> elems;
        if (ins instanceof DList.Instance) {
            elems = ((DList.Instance)ins).data();
        } else {
            elems = new LinkedList<DType.Instance>() {{ add(ins); }};
        }
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
        throw new MatchException("Match None");
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
        for (String fieldname: lst) {
            StructureRelation rel = lst.rel(fieldname);
            if (rel == StructureRelation.PC) {
                if (!(ins instanceof DMapping.Instance))
                    throw new MatchException("can not be matchec");
                DMapping.Instance mapping = (DMapping.Instance)ins;
                if (!mapping.contains(fieldname)) {
                    ret.clear();
                } else {
                    for (DSingularBody body: ret)
                        body.put(
                            index, 
                            findMatch(mapping.get(fieldname), lst.structure(fieldname)));
                }
            } else {
                List<DBody> candidates = iterateInstanceAndMatch(ins, fieldname, lst.structure(fieldname));
                List<DSingularBody> update = new LinkedList<>();
                for (DSingularBody row: ret) {
                    for (DBody cell: candidates) {
                        DSingularBody newRow = new DSingularBody(lst.size());
                        for (int i = 0; i < index; i++) {
                            newRow.put(i, row.get(i));
                        }
                        newRow.put(index, cell);
                        update.add(newRow);
                    }
                }
                ret = update;
            }
            index ++;
        }
        return ret;
    }

    public List<DBody> iterateInstanceAndMatch(DType.Instance ins, String fieldname, StructureList lst) throws MatchException {
        return new LinkedList<>() {{
            if (ins instanceof DMapping.Instance) {
                DMapping.Instance mapping = (DMapping.Instance)ins;
                if (mapping.contains(fieldname)) {
                    add(findMatch(mapping.get(fieldname), lst));
                }
                for (String field: mapping) {
                    addAll(iterateInstanceAndMatch(mapping.get(field), fieldname, lst));
                }
            }
            if (ins instanceof DList.Instance) {
                DList.Instance lstIns = (DList.Instance)ins;
                for (DType.Instance insElem: lstIns) 
                    addAll(iterateInstanceAndMatch(insElem, fieldname, lst));
            }
        }};
    }
}