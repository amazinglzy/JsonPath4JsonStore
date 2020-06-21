/*
NestedRelation: includes determinstic and undeterminstic
    SingleRelation
    RepeatableRelation

*/
package jp4js.nf2.rel;

import java.util.Map;
import java.util.Iterator;
import java.util.TreeMap;

public class NestedRelation implements DType, Iterable<String> { 
    private final TreeMap<String, DType> mapping;
    private final TreeMap<String, Integer> index;

    public NestedRelation(TreeMap<String, DType> mapping, TreeMap<String, Integer> index) {
        this.mapping = mapping;
        this.index = index;
    }

    public DType get(String fieldname) {
        return this.mapping.get(fieldname);
    }

    public int index(String fieldname) {
        return this.index.get(fieldname);
    }

    public Iterator<String> iterator() {
        return this.mapping.keySet().iterator();
    }

    public TreeMap<String, DType> mapping() {
        return this.mapping;
    }

    public DocumentSetBuilder tupleBuilder() {
        return new DocumentSetBuilder(this);
    }

    public DocumentSetListBuilder builder() {
        return new DocumentSetListBuilder(this);
    }
    
    @Override
    public String toString() {
        String ret = "";
        for (Map.Entry<String, DType> entry: this.mapping.entrySet()) {
            if (ret.length() != 0) ret += ", ";
            ret += entry.getKey() + "(" + entry.getValue().toString() + ")";
        }

        return ret;
    }
}