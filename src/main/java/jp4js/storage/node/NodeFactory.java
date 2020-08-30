package jp4js.storage.node;

import jp4js.nf2.DType;

public class NodeFactory {
    public static LabelObject create(String nodeName, long firstVisit, long lastVisit, int level, DType.Instance value) {
        return new LabelObject(nodeName, firstVisit, lastVisit, level, value);
    }

    public static LabelArray create(long index, long firstVisit, long lastVisit, int level, DType.Instance value) {
        return new LabelArray(index, firstVisit, lastVisit, level, value);
    }
}
