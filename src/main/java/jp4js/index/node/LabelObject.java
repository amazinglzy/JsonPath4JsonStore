package jp4js.index.node;

import jp4js.index.node.LabelNode;

public class LabelObject extends LabelNode {
    private String nodeName;

    public LabelObject(String path, String nodeName, Object value, Object rootDocument) {
        super(path, -1, -1, -1, value, rootDocument);
        this.nodeName = nodeName;
    }

    public LabelObject(String path, String nodeName, long firstVisit, long lastVisit, int level, Object value, Object rootDocument) {
        super(path, firstVisit, lastVisit, level, value, rootDocument);
        this.nodeName = nodeName;
    }

    public String getLabelNodeName() {
        return this.nodeName;
    }
}
