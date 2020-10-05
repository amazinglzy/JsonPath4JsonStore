package jp4js.algebra.tpl;

import jp4js.algebra.DType;

public class AtomicTemplate implements DHeader {
    private DType elemType;

    public AtomicTemplate(DType elemType) {
        this.elemType = elemType;
    }

    public DType elemType() {
        return this.elemType;
    }

    @Override
    public String toString() {
        return "";
        // return elemType.toString();
    }
}
