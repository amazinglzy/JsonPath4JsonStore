package jp4js.storage.region.node;

import jp4js.algebra.DType;

public class SingularNode extends IndexNode {

    public SingularNode(String name, long firstVisit, long lastVisit, int level, DType.Instance value) {
        super(name, firstVisit, lastVisit, level, value);
    }

    @Override
    public boolean isRepeatable() {
        return false;
    }
}
