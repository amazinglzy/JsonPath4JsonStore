package jp4js.index.node;

import jp4js.index.node.Node;

public class ObjectNode extends Node {
    private String nodeName;

    public ObjectNode(String nodeName, Object value, Object rootDocument) {
        super(-1, -1, -1, value, rootDocument);
        this.nodeName = nodeName;
    }

    public ObjectNode(String nodeName, long firstVisit, long lastVisit, int level, Object value, Object rootDocument) {
        super(firstVisit, lastVisit, level, value, rootDocument);
        this.nodeName = nodeName;
    }

    public String getNodeName() {
        return this.nodeName;
    }
}
