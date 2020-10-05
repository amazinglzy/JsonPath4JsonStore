package jp4js.storage.region.node;

import jp4js.algebra.DType;

public class RepeatableNode extends IndexNode {
    public long index;
    public RepeatableNode(String name, long index, DType.Instance value) {
        super(name, -1, -1, -1, value);
        this.index = index;
    }

    public RepeatableNode(String name, long index, long firstVisit, long lastVisit, int level, DType.Instance value) {
        super(name, firstVisit, lastVisit, level, value);
        this.index = index;
    }

    @Override
    public boolean isRepeatable() {
        return true;
    }
}
