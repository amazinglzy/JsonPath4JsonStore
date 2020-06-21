/*
NestedRelation: includes determinstic and undeterminstic
    SingleRelation
    RepeatableRelation

*/
package jp4js.nf2.rel;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
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

    public TupleBuilder tupleBuilder() {
        return new TupleBuilder(this);
    }

    public InstanceBuilder builder() {
        return new InstanceBuilder(this);
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

    public class InstanceBuilder {
        private Deque<NestedRelation> typePath;
        private Deque<List<DocumentSet>> tuplePath;
        private Deque<TupleBuilder> tupleBuilderPath;
        private Deque<String> fieldNamePath;

        public InstanceBuilder(NestedRelation relation) {
            this.typePath = new ArrayDeque<>() {{
                push(relation);
            }};
            this.tuplePath = new ArrayDeque<>() {{
                push(new LinkedList<>());
            }};
            this.tupleBuilderPath = new ArrayDeque<>();
            this.fieldNamePath = new ArrayDeque<>();
        }
        
        public InstanceBuilder begin() {
            if (this.typePath.size() == 0) 
                throw new IllegalArgumentException();
            if (this.hasCurrentTupleBuilder()) 
                throw new IllegalArgumentException();
            this.tupleBuilderPath.push(this.typePath.peek().tupleBuilder());
            return this;
        }
        
        public InstanceBuilder put(String fieldname, Object value) {
            if (!this.hasCurrentTupleBuilder())
                throw new IllegalArgumentException();
            this.tupleBuilderPath.peek().put(fieldname, value);
            return this;
        }

        public InstanceBuilder end() {
            this.tuplePath.peek().add(this.tupleBuilderPath.peek().build());
            this.tupleBuilderPath.pop();
            return this;
        }

        public InstanceBuilder enter(String fieldname) {
            DType nestedType = this.typePath.peek().get(fieldname);
            if (!(nestedType instanceof NestedRelation)) 
                throw new IllegalArgumentException();
            NestedRelation nestedRelation = (NestedRelation)nestedType;
            this.typePath.push(nestedRelation);
            this.tuplePath.push(new LinkedList<>());
            this.fieldNamePath.push(fieldname);
            return this;
        }

        public InstanceBuilder exit() {
            if (this.hasCurrentTupleBuilder())
                throw new IllegalArgumentException();
            this.typePath.pop();
            DocumentSetList instance = new DocumentSetList(this.tuplePath.pop());
            String fieldName = this.fieldNamePath.pop();
            this.tupleBuilderPath.peek().put(fieldName, instance);
            return this;
        }

        public DocumentSetList build() {
            if (this.tuplePath.size() != 1) 
                throw new IllegalArgumentException();
            return new DocumentSetList(this.tuplePath.pop());
        }

        private boolean hasCurrentTupleBuilder() {
            return this.tupleBuilderPath.size() == this.typePath.size();
        }
    }
}