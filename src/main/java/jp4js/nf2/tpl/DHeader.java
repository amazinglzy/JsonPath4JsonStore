package jp4js.nf2.tpl;

import jp4js.nf2.DType;
import jp4js.utils.Utils;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class DHeader implements Iterable<String> {
    private DHeaderType headerType;
    private DType dType;
    private TreeMap<String, DHeader> mapping;
    private TreeMap<String, Integer> index;

    public DHeader(DHeaderType headerType) {
        this.headerType = headerType;
        this.mapping = new TreeMap<>();
        this.index = new TreeMap<>();
    }

    public DHeader(DHeaderType headerType, DType dType) {
        this.headerType = headerType;
        this.dType = dType;
    }

    public DHeader get(String fieldname) {
        Utils.notNull(this.mapping, "Mapping must not be null to be called");
        return this.mapping.get(fieldname);
    }

    public void put(String fieldname, DHeader type) {
        Utils.notNull(this.mapping, "Mapping must not be null to be called");
        this.mapping.put(fieldname, type);
        if (!this.index.containsKey(fieldname)) {
            this.index.put(fieldname, this.index.size());
        }
    }

    public int index(String fieldname) {
        Utils.notNull(this.mapping, "Mapping must not be null to be called");
        return this.index.get(fieldname);
    }

    public Iterator<String> iterator() {
        Utils.notNull(this.mapping, "Mapping must not be null to be called");
        return this.mapping.keySet().iterator();
    }

    public boolean isAtomic() {
        return this.dType != null;
    }

    public DHeaderType headerType() {
        return this.headerType;
    }

    @Override
    public String toString() {
        String ret = "";
        if (this.mapping != null) {
            for (Map.Entry<String, DHeader> entry: this.mapping.entrySet()) {
                if (ret.length() != 0) ret += ", ";
                ret += entry.getKey() + entry.getValue().toString();
            }
        } else {
            ret = this.dType.toString();
        }

        switch(this.headerType) {
            case SINGULAR:
                ret = "(" + ret + ")";
                break;
            case REPEATABLE:
                ret = "[" + ret + "]";
                break;
            default:
                throw new IllegalArgumentException();
        }

        return ret;
    }
}