package jp4js.nf2.tpl;

import jp4js.nf2.DType;

public class DRepeatableHeader implements DHeader {
    private final DHeader elemType;

    public DRepeatableHeader(DHeader elemType) {
        this.elemType = elemType;
    }

    public DRepeatableHeader(DType elemType) {
        this.elemType = new DSingularHeader(elemType);
    }

    public DHeader elemType() {
        return this.elemType;
    }

    @Override
    public String toString() {
        return "[" + this.elemType.toString() + "]";
    }
}