package jp4js.index.node;

public class NodeFactory {
    public static LabelObject create(String path, String nodeName, long firstVisit, long lastVisit, int level, Object value, Object rootDocument) {
        return new LabelObject(path, nodeName, firstVisit, lastVisit, level, value, rootDocument);
    }

    public static LabelArray create(String path, long index, long firstVisit, long lastVisit, int level, Object value, Object rootDocument) {
        return new LabelArray(path, index, firstVisit, lastVisit, level, value, rootDocument);
    }
}
