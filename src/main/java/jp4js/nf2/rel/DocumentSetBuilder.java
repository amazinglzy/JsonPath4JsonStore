package jp4js.nf2.rel;

public class DocumentSetBuilder {
    private Object[] data;
    private NestedRelation relation;

    public DocumentSetBuilder(NestedRelation relation) {
        this.data = new Object[relation.mapping().size()];
        this.relation = relation;
    }

    public DocumentSetBuilder put(String fieldname, Object value) {
        this.data[this.relation.index(fieldname)] = value;
        return this;
    }

    public DocumentSet build() {
        return new DocumentSet(data);
    }
}