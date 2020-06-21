package jp4js.nf2.rel;

import java.util.List;
import java.util.Deque;
import java.util.ArrayDeque;
import java.util.LinkedList;

public class DocumentSetListBuilder {
    private Deque<NestedRelation> typePath;
    private Deque<List<DocumentSet>> tuplePath;
    private Deque<DocumentSetBuilder> tupleBuilderPath;
    private Deque<String> fieldNamePath;

    public DocumentSetListBuilder(NestedRelation relation) {
        this.typePath = new ArrayDeque<>() {{
            push(relation);
        }};
        this.tuplePath = new ArrayDeque<>() {{
            push(new LinkedList<>());
        }};
        this.tupleBuilderPath = new ArrayDeque<>();
        this.fieldNamePath = new ArrayDeque<>();
    }
    
    public DocumentSetListBuilder begin() {
        if (this.typePath.size() == 0) 
            throw new IllegalArgumentException();
        if (this.hasCurrentTupleBuilder()) 
            throw new IllegalArgumentException();
        this.tupleBuilderPath.push(this.typePath.peek().tupleBuilder());
        return this;
    }
    
    public DocumentSetListBuilder put(String fieldname, Object value) {
        if (!this.hasCurrentTupleBuilder())
            throw new IllegalArgumentException();
        this.tupleBuilderPath.peek().put(fieldname, value);
        return this;
    }

    public DocumentSetListBuilder end() {
        this.tuplePath.peek().add(this.tupleBuilderPath.peek().build());
        this.tupleBuilderPath.pop();
        return this;
    }

    public DocumentSetListBuilder enter(String fieldname) {
        DType nestedType = this.typePath.peek().get(fieldname);
        if (!(nestedType instanceof NestedRelation)) 
            throw new IllegalArgumentException();
        NestedRelation nestedRelation = (NestedRelation)nestedType;
        this.typePath.push(nestedRelation);
        this.tuplePath.push(new LinkedList<>());
        this.fieldNamePath.push(fieldname);
        return this;
    }

    public DocumentSetListBuilder exit() {
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