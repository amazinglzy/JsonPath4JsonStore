package jp4js.nf2.rel.doc;

public abstract class DocNode {
    private final NodeID id;
    public DocNode(NodeID id) {
        this.id = id;
    }

    public NodeID id() {
        return this.id;
    }
}