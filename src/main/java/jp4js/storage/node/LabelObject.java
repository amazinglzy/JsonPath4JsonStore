package jp4js.storage.node;

import jp4js.nf2.DType;

public class LabelObject extends LabelNode {
    public String nodeName;

    public LabelObject(String nodeName, DType.Instance value) {
        super(-1, -1, -1, value);
        this.nodeName = nodeName;
    }

    public LabelObject(String nodeName, long firstVisit, long lastVisit, int level, DType.Instance value) {
        super(firstVisit, lastVisit, level, value);
        this.nodeName = nodeName;
    }
}
