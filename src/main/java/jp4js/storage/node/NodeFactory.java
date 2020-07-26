package jp4js.storage.node;

import jp4js.nf2.DType;

public class NodeFactory {
    public static LabelObject create(String path, String nodeName, long firstVisit, long lastVisit, int level, DType.Instance value) {
        return new LabelObject(path, nodeName, firstVisit, lastVisit, level, value);
    }

    public static LabelArray create(String path, long index, long firstVisit, long lastVisit, int level, DType.Instance value) {
        return new LabelArray(path, index, firstVisit, lastVisit, level, value);
    }
}
