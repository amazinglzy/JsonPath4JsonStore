package jp4js.index.node;

public class NodeFactory {
    public static ObjectNode create(String nodeName, long firstVisit, long lastVisit, int level, Object value, Object rootDocument) {
        return new ObjectNode(nodeName, firstVisit, lastVisit, level, value, rootDocument);
    }

    public static ArrayNode create(long index, long firstVisit, long lastVisit, int level, Object value, Object rootDocument) {
        return new ArrayNode(index, firstVisit, lastVisit, level, value, rootDocument);
    }
}
