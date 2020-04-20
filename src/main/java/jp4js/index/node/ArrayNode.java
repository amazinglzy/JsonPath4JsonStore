package jp4js.index.node;

public class ArrayNode extends Node {
    private long index;
    public ArrayNode(long index, Object value, Object rootDocument) {
        super(-1, -1, -1, value, rootDocument);
        this.index = index;
    }

    public ArrayNode(long index, long firstVisit, long lastVisit, int level, Object value, Object rootDocument) {
        super(firstVisit, lastVisit, level, value, rootDocument);
        this.index = index;
    }

    public long getIndex() {
        return this.index;
    }
}
