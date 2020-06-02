package jp4js.nf2.rel;

import java.util.TreeMap;

import java.util.Deque;
import java.util.ArrayDeque;

public class NestedRelationBuilder {
    private Deque<TreeMap<String, DType>> mappingPath;
    private Deque<String> fieldPath;


    public NestedRelationBuilder() {
        this.mappingPath = new ArrayDeque<>();
        this.fieldPath = new ArrayDeque<>();
        this.mappingPath.push(new TreeMap<>());
    }

    public NestedRelationBuilder put(String fieldname, DType type) {
        this.mappingPath.peek().put(fieldname, type);
        return this;
    }

    public NestedRelationBuilder enter(String fieldname) {
        if (this.mappingPath.peek().containsKey(fieldname)) {
            if (this.mappingPath.peek().get(fieldname) instanceof NestedRelation) {
                NestedRelation rel = (NestedRelation) this.mappingPath.peek().get(fieldname);
                this.mappingPath.push(rel.mapping());
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            TreeMap<String, DType> newMapping = new TreeMap<>();
            this.mappingPath.push(newMapping);
        }
        this.fieldPath.push(fieldname);

        return this;
    }

    public NestedRelationBuilder exit() {
        if (this.mappingPath.size() <= 1) 
            throw new IllegalArgumentException();
        TreeMap<String, DType> mapping = this.mappingPath.pop();
        TreeMap<String, Integer> index = buildIndex(mapping);
        NestedRelation rel = new NestedRelation(mapping, index);
        this.mappingPath.peek().put(this.fieldPath.pop(), rel);
        return this;
    }

    public NestedRelation build() {
        if (this.mappingPath.size() != 1) 
            throw new IllegalArgumentException();
        TreeMap<String, DType> mapping = this.mappingPath.pop();
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