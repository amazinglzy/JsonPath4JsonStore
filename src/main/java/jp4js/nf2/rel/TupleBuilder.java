package jp4js.nf2.rel;

public class TupleBuilder {
    private Object[] data;
    private NestedRelation relation;

    public TupleBuilder(NestedRelation relation) {
        this.data = new Object[relation.mapping().size()];
        this.relation = relation;
    }

    public TupleBuilder put(String fieldname, Object value) {
        this.data[this.relation.index(fieldname)] = value;
        return this;
    }

    public DocumentSet build() {
        return new DocumentSet(data);
    }
}