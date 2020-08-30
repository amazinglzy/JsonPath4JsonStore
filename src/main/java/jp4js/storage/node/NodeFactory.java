package jp4js.storage.node;

import jp4js.nf2.DType;

public class NodeFactory {
    public static SingularNode create(String name, long firstVisit, long lastVisit, int level, DType.Instance value) {
        return new SingularNode(name, firstVisit, lastVisit, level, value);
    }

    public static RepeatableNode create(String name, long index, long firstVisit, long lastVisit, int level, DType.Instance value) {
        return new RepeatableNode(name, index, firstVisit, lastVisit, level, value);
    }
}
