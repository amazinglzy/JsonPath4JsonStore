package jp4js.nf2.op.structure;

import jp4js.utils.Utils;

import java.util.TreeMap;
import java.util.Iterator;


public abstract class StructureList implements Iterable<String> {
    private TreeMap<String, StructureList> fieldname2structure;
    private TreeMap<String, StructureRelation> fieldname2rel;

    public StructureList() {
        this.fieldname2structure = new TreeMap<>();
        this.fieldname2rel = new TreeMap<>();
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

    public abstract StructureType type();

    public Iterator<String> iterator() {
        return this.fieldname2structure.keySet().iterator();
    }

    public void mergeIn(StructureList lst) {
        Utils.isTrue(this.type() == lst.type(), "StructureType must be same when merge");
        for (String fieldname: lst) {
            this.put(fieldname, lst.structure(fieldname), lst.rel(fieldname));
        }
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
        return ret;
    }
}