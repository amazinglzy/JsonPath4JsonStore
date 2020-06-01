package jp4js.nf2.rel;

import java.util.TreeMap;
import java.util.Stack;

public class NestedRelationBuilder {
    private Stack<TreeMap<String, DType>> path;


    public NestedRelationBuilder() {
        this.path = new Stack<>();
        this.path.push(new TreeMap<>());
    }

    public NestedRelationBuilder put(String fieldname, DType type) {
        this.path.peek().put(fieldname, type);
        return this;
    }

    public NestedRelationBuilder enter() {
        TreeMap<String, DType> newMapping = new TreeMap<>();
        this.path.push(newMapping);

        return this;
    }

    public NestedRelationBuilder exit(String fieldname) {
        if (this.path.size() <= 1) 
            throw new IllegalArgumentException();
        TreeMap<String, DType> mapping = this.path.pop();
        TreeMap<String, Integer> index = buildIndex(mapping);
        NestedRelation rel = new NestedRelation(mapping, index);
        this.path.peek().put(fieldname, rel);
        return this;
    }

    public NestedRelation build() {
        if (this.path.size() != 1) 
            throw new IllegalArgumentException();
        TreeMap<String, DType> mapping = this.path.pop();
        TreeMap<String, Integer> index = buildIndex(mapping);
        NestedRelation rel = new NestedRelation(mapping, index);
        return rel;
    }

    private TreeMap<String, Integer> buildIndex(TreeMap<String, DType> mapping) {
        TreeMap<String, Integer> ret = new TreeMap<>();
        int idx = 0;
        for (String key: mapping.keySet()) {
            ret.put(key, idx);
            idx ++;
        }

        return ret;
    }
}