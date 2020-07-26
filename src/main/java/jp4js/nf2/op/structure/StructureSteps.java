package jp4js.nf2.op.structure;

import java.util.List;
import java.util.ArrayList;

public class StructureSteps {
    private List<StructureRelation> types;
    private List<String> fieldnames;

    public StructureSteps() {
        types = new ArrayList<>();
        fieldnames = new ArrayList<>();
    }

    public void addStep(StructureRelation type, String fieldname) {
        this.types.add(type);
        this.fieldnames.add(fieldname);
    }

    public StructureRelation type(int index) {
        return this.types.get(index);
    }

    public String fieldname(int index) {
        return this.fieldnames.get(index);
    }

    public int size() {
        return this.types.size();
    }

    @Override
    public String toString() {
        String ret = "";
        for (int i = 0; i < types.size(); i++) {
            ret += types.get(i) == StructureRelation.PC ? "." : "..";
            ret += fieldnames.get(i);
        }
        return ret;
    }
}