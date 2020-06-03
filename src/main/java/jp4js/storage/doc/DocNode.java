package jp4js.storage.doc;

public abstract class DocNode {
    private final NodeID id;
    public DocNode(NodeID id) {
        this.id = id;
    }

    public NodeID id() {
        return this.id;
    }
}