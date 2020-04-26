package jp4js.index.node;

public class NodeFactory {
    public static LabelObject create(String nodeName, long firstVisit, long lastVisit, int level, Object value, Object rootDocument) {
        return new LabelObject(nodeName, firstVisit, lastVisit, level, value, rootDocument);
    }

    public static LabelArray create(long index, long firstVisit, long lastVisit, int level, Object value, Object rootDocument) {
        return new LabelArray(index, firstVisit, lastVisit, level, value, rootDocument);
    }
}
