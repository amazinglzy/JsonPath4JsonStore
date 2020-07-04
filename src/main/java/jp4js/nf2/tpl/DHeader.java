package jp4js.nf2.tpl;

import jp4js.utils.Utils;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public abstract class DHeader implements Iterable<String> {
    private TreeMap<String, DHeader> mapping;
    private TreeMap<String, Integer> index;

    public DHeader() {
        this.mapping = new TreeMap<>();
        this.index = new TreeMap<>();
    }

    public DHeader get(String fieldname) {
        Utils.notNull(this.mapping, "Mapping must not be null to be called");
        return this.mapping.get(fieldname);
    }
    
    public boolean contains(String fieldname) {
        Utils.notNull(this.mapping, "Mapping must not be null to be called");
        return this.mapping.containsKey(fieldname);
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

    public abstract DHeaderType headerType();

    @Override
    public String toString() {
        String ret = "";
        for (Map.Entry<String, DHeader> entry: this.mapping.entrySet()) {
            if (ret.length() != 0) ret += ", ";
            ret += entry.getKey();
            if (entry.getValue() != null)
                ret += entry.getValue().toString();
        }

        return ret;
    }
}