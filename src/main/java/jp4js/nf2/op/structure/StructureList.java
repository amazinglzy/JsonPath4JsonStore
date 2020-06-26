package jp4js.nf2.op.structure;

import java.util.HashMap;

public class StructureList{
    private StructureType type;
    private HashMap<String, StructureList> fieldname2structure;
    private HashMap<String, StructureRelation> fieldname2rel;

    public StructureList(StructureType type) {
        this.type = type;
        this.fieldname2structure = new HashMap<>();
        this.fieldname2rel = new HashMap<>();
    }

    public void put(String fieldname, StructureList s, StructureRelation rel) {
        this.fieldname2structure.put(fieldname, s);
        this.fieldname2rel.put(fieldname, rel);
    }

    public StructureList structure(String fieldname) {
        return this.fieldname2structure.get(fieldname);
    }

    public StructureRelation rel(String fieldname) {
        return this.fieldname2rel.get(fieldname);
    }

    public StructureType type() {
        return this.type;
    }

    @Override
    public String toString() {
        String ret = "";
        for (String fieldname: this.fieldname2structure.keySet()) {
            if (ret.length() != 0) ret += ", ";
            StructureList subList = this.fieldname2structure.get(fieldname);
            StructureRelation rel = this.fieldname2rel.get(fieldname);
            ret += (rel == StructureRelation.PC) ? ".": "..";
            ret += fieldname;
            if (subList != null)
                ret += subList.toString();
        }
        if (this.type == StructureType.SINGULAR) ret = "(" + ret + ")";
        else ret = "[" + ret + "]";
        return ret;
    }
}