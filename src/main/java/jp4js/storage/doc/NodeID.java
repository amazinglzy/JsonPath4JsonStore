package jp4js.storage.doc;

public class NodeID {
    private final int docId, nodeId;
    
    public NodeID(int docId, int nodeId) {
        this.docId = docId;
        this.nodeId = nodeId;
    }

    public int docId() {
        return this.docId;
    }

    public int nodeId() {
        return this.nodeId;
    }

    @Override
    public String toString() {
        return "(" + String.valueOf(this.docId) + ", " + String.valueOf(this.nodeId) + ")";
    }
}