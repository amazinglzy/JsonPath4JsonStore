package jp4js.nf2.tpl;

import jp4js.nf2.DType;

public class DRepeatableHeader extends DHeader {
    public DRepeatableHeader() {
    }
    public DRepeatableHeader(DType dType) {
        super(dType);
    }

    @Override
    public DHeaderType headerType() {
        return DHeaderType.REPEATABLE;
    }

    @Override
    public String toString() {
        return "[" + super.toString() + "]";
    }
}